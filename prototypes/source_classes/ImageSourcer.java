package classes;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageSourcer {
    // configuration variables
    private final String API_KEY = "AIzaSyBvtYQTwr27jko4oLGpgtep8OLvvlKkPOA"; // this is an API key provided by Google
    private final String SEARCH_ENGINE_KEY = "004843956391315063069:wnj8zpugysm"; // this is a custom search engine key provided by Google
    private final String SEARCH_FILETYPES = "png,jpg"; // desired file types for search
    private final int GOOGLE_SEARCH_LIMIT = 10; // number of results that Google returns per query
    private boolean valid = true; // is this imagesourcer usable?
    
    // flags
    Boolean useReuse;
    int imageCount = 0;
            
    // data structures
    private Vector<BufferedImage> source = new Vector<BufferedImage>();
    
    // backup
    private Vector<BufferedImage> reuse = new Vector<BufferedImage>();
    
    // constructor fills source vector
    public ImageSourcer(String searchText, int numImages)
    {
    	useReuse = false;
    	
    	Vector<String> sourceURLs = getImages(searchText, numImages);
    	try {
			source = urlsToBufferedImages(sourceURLs);
		} catch (IOException e) {
			valid = false; // catches nullpointer exception from sourceURL, indicating problem getting images
		}
    }
    
    // returns true if imagesourcer is usable (images exist), false otherwise
    public boolean isValid()
    {
    	return valid;
    }
    
    public BufferedImage getImage()
    {
    	System.out.println("Pulling image " + String.valueOf(imageCount));
    	imageCount += 1;
    	
    	if(useReuse == true && !reuse.isEmpty())
    	{
    		System.out.println("Reused something");
    		BufferedImage image = reuse.get(0);
    		source.add(image);
    		reuse.remove(0);
    		return image;
    	}
    	else if(!source.isEmpty())
    	{
    		BufferedImage image = source.get(0);
    		reuse.add(image);
    		source.remove(0);
    		return image;
    	}
    	else if(source.isEmpty())
    	{
    		BufferedImage image = reuse.get(0);
    		source.add(image);
    		reuse.remove(0);
    		useReuse = true; // swap to the reuse vector
    		System.out.println("swapped to reuse");
    		return image;
    	}
    	
		return null;
    	
    }
    
    private Vector<String> getImages(String searchText, int numImages) {
        /* Arguments: String searchText: the thing that we want to search Google images for - provided by user in client
         * 
         * Returns: Vector<String> of URLs. This will either be null (if < REQUIRED_IMAGES images found) or contain REQUIRED_IMAGES image URLs of the
         * top REQUIRED_IMAGES image results for searchText on Google. */
        
        // IMPORTANT NOTE: Google Custom Search API only allows return of GOOGLE_SEARCH_LIMIT items at a time. 
        // SOLUTION: We run the code to fetch GOOGLE_SEARCH_LIMIT items several times, offsetting the start index by GOOGLE_SEARCH_LIMIT each time. 
        // That is, the contents of the loop is the code to generate GOOGLE_SEARCH_LIMIT image URLs. 
        // In the case of the original values - 
        //     GOOGLE_SEARCH_LIMIT = 10, REQUIRED_IMAGES = 30,
        //     The loop itself executes 3 times, grabbing the
        //     first 10, 11-20, 21-30th images.
        
        // setting up data structures that will be populated by data later
        BufferedReader br; // will be used to read from Google API's input stream
        StringBuilder builder = null; // will build a string out of lines from the input stream
        JSONObject json = null; // holds the JSON object form of the data
        Vector<String> imageURLs = new Vector<String>(); // vector to hold all 30 image urls (to be returned later)
        
        // offset / search loop
        /* loop will perform the following duties:
         *     1. grab next 10 images from Google
         *     2. parse data, which is returned as a JSON file, to obtain URLs
         *     3. append URLs to our vector of URLs */ 
        try {
            for (int offset = 0; offset <= (numImages-1)/GOOGLE_SEARCH_LIMIT * GOOGLE_SEARCH_LIMIT /* REQUIRED_IMAGES rounded down to multiple of GOOGLE_SEARCH_LIMIT */; offset += GOOGLE_SEARCH_LIMIT) { // loops, getting GOOGLE_SEARCH_LIMIT images each time, until enough images gathered
                // setting up search parameters
                String qry = "";
                try {
                    qry = URLEncoder.encode(searchText, "UTF-8");  // url encoding the raw search string
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                
                String key = API_KEY; 
                String cx  = SEARCH_ENGINE_KEY;
                String fileType = SEARCH_FILETYPES;
                String searchType = "image"; // type of search to perform
                String startIndex = String.valueOf(offset + 1); // the offset + 1 is the index of the Google results that we want to start returning our GOOGLE_SEARCH_LIMIT results from. Results are 1-indexed.
                                                            // e.g. when offset = 0, start = 1, which means we are returning results 1-GOOGLE_SEARCH_LIMIT.
                
                // creating connection with Google Custom Search API - see API documentation for parameter details
                HttpURLConnection conn = null;
                try {
                    URL url = new URL ("https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=" + cx + "&q=" + qry + "&fileType=" + fileType + "&searchType=" + searchType + "&alt=json&start=" + startIndex);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
                // the next two steps will require the string builder
                // string builder will be used to read data from Google's input stream and be converted into a JSON object for parsing
                builder = new StringBuilder();
                
                // retrieving result from Google connection's input stream
                try {
                    br = new BufferedReader(new InputStreamReader((conn.getInputStream())));        
                    String line; // temporary variable to store lines from buffered reader as we read from input stream
                    
                    // read each line one by one and append to our string builder
                    while((line = br.readLine()) != null) {
                        builder.append(line);
                    }
                    // terminate connection with Google
                    conn.disconnect();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                // Google returns result in JSON format
                // converting result to JSON object and then parsing that JSON object to extract data
                String imageUrl = null; // temporary variable to hold current image url as we iterate through data
                try {
                    json = new JSONObject(builder.toString());
                    for (int i = 0; i < GOOGLE_SEARCH_LIMIT; i++) {    
                        imageUrl = json.getJSONArray("items").getJSONObject(i).getString("link");
                        imageURLs.addElement(imageUrl);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } // end offset and search loop
        } catch (Exception e)
        {
            // if any exception occurs in the image sourcing, assume Google failure 
        }
        // check that REQUIRED_IMAGES image URLs were found
        if (imageURLs.size() >= numImages) {
            // if more than REQUIRED_IMAGES URLs found, return only REQUIRED_IMAGES of them
            Vector<String> imageURLsLimited = new Vector<String>(); // will hold only first REQUIRED_IMAGES URLs
            for (int i = 0; i < numImages; i++) {
                imageURLsLimited.add(imageURLs.get(i));
            }
            return imageURLsLimited;
        }
        else {
            return null; // return value of null indicates that < REQUIRED_IMAGES images were found
        }
    } // end getImages(String)
	

    
    // Creates a vector of BufferedImages from given image URLs
    public Vector<BufferedImage> urlsToBufferedImages(Vector<String> imageSource) throws IOException {
        Vector<BufferedImage> bufferedImageVec = new Vector<BufferedImage>();
        
        // Parallelize getting images from URL string
        imageSource.parallelStream().forEach((urlString) ->
        {
            URL url;
            HttpURLConnection conn = null;
            BufferedImage image = null;
            try {
                url = new URL(urlString);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
                image = ImageIO.read(conn.getInputStream());
                
                if(image != null)
                {
                    bufferedImageVec.add(image);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return bufferedImageVec;
    }

//	public BufferedImage getImage() {
//		
//		BufferedImage image = new BufferedImage(500, 300, BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g2d = image.createGraphics();
//		int randNum = (int) (Math.random() * 4);
//		Color color = Color.red;
//		if(randNum == 0) {
//			color = Color.yellow;
//		}
//		if(randNum == 1) {
//			color = Color.red;
//		}
//		if(randNum == 2) {
//			color = Color.green;
//		}
//		if(randNum == 3) {
//			color = Color.blue;
//		}
//		if(randNum == 4) {
//			color = Color.black;
//		}
//		g2d.setColor(color);
//		g2d.fill(new Rectangle2D.Float(0,0,500,300));
//		
//		return image;
//	}
}
