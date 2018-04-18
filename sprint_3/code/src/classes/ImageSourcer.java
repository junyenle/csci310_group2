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

/* this class serves to take images from Google and to manage those images for the collage builder */
public class ImageSourcer {

	// TESTING HELPER FUNCTION (that is, required for the white box tests to complete their function)
	public Vector<String> p_getImages(String searchText, int numImages) {
		return getImages(searchText, numImages);
	}

	// configuration variables
	private final String API_KEY = "AIzaSyAiC3kiN4NtdABasdpkFTuaSiwR9x41COE".toString(); // this is an API key provided
																							// by Google
	/* spare keys:
	 * maybe expended?
		AIzaSyDl6T1itQ1cmgBR0dOCnI7KPWbdnwuSGUg
		AIzaSyCMLFYK6VlsPwvUKIwB2MvHvWJ_Pf-QAn4
		AIzaSyARWguCMARs26vivRF2GgFooxtEAchKS7k
		AIzaSyD_pXXe1vmSly_BCFJqY7uT1zSk_x4SXOU
		AIzaSyDivyRLZnzGZaPzeIV829ifsGXWiUlLhaY
		AIzaSyBm4X1F-sZEC25YYwm6GbpWHG7NqGk_O_U
		AIzaSyAiC3kiN4NtdABasdpkFTuaSiwR9x41COE
		
		expended for sure
		AIzaSyB_CnB1Lf9SG3kM0a8CX4Mn-XRcQIjuTQs
		AIzaSyBLF3L9Tg_19Gq2ORSWI0-b82c1M0ksHQ4
		AIzaSyA_lrl3HmVamJI9VHo_6_DPgzZ1QoXlEtc
		
		bbox saved
		AIzaSyAOO99UWBvgnUpZLJtADNIrbefgJjn2PQo
		
		wbox / demo saved
		AIzaSyAfNAqIThCs_H_YuvSL2WeqkYHnYYuZMRk
		
		demo saved
		AIzaSyB-Xal-Ah32dyhBa2tnZ5nZr5mFO4q518g
	*/
	private final String SEARCH_ENGINE_KEY = "004843956391315063069:wnj8zpugysm".toString(); // this is a custom search
																								// engine key provided
																								// by Google
	private final String SEARCH_FILETYPES = "png,jpg".toString(); // desired file types for search
	private final int GOOGLE_SEARCH_LIMIT = Integer.valueOf(10); // number of results that Google returns per query
	private boolean valid = true; // is this imagesourcer usable?

	// flags
	Boolean useReuse; // tells us which vector of images to pull from
	int imageCount = 0; // tells us how many images we've used

	// data structures
	private Vector<BufferedImage> source = new Vector<BufferedImage>(); // main vector of images
	private Vector<BufferedImage> reuse = new Vector<BufferedImage>(); // vector of USED images as backup if we run out of images

	/* constructor
	 * args: search text, number of images
	 * invokes getImages to get the image urls from Google, then turns them into actual buffered images
	 * sets valid to false if we couldn't get enough images
	 */
	public ImageSourcer(String searchText, int numImages) {
		useReuse = false;

		Vector<String> sourceURLs = getImages(searchText, numImages);
		try {
			source = urlsToBufferedImages(sourceURLs);
		} catch (Exception e) {
			valid = false; // catches nullpointer exception from sourceURL, indicating problem getting
							// images
		}
	}

	// returns true if imagesourcer is usable (images exist), false otherwise
	public boolean isValid() {
		return valid;
	}

	/* gets next image
	 * that is, pulls top image from the source vector OR the reuse vector, depending on which one we're currently using
	 * returns buffered image
	 */
	public BufferedImage getImage() {
		imageCount += 1;
		
		// if we are using the reuse vector and the reuse vector is not empty
		if (useReuse == true && !reuse.isEmpty()) {
			// get from reuse vector
			BufferedImage image = reuse.get(0);
			// pass that image to the source (so it becomes reuse's reuse)
			source.add(image);
			// remove image from reuse
			reuse.remove(0);
			// return the image
			return image;
		} else if (!source.isEmpty()) { // or, if we are not using reuse and the source is not empty
			// get from source vector
			BufferedImage image = source.get(0);
			// pass image to reuse so we can reuse later
			reuse.add(image);
			// remove image from source
			source.remove(0);
			// return image
			return image;
		} else if (source.isEmpty()) { // or, if we are not using reuse but the source is empty
			// we need to pull the image from reuse
			BufferedImage image = reuse.get(0);
			// pass iamge to source so we can reuse again later
			source.add(image);
			// remove image from reuse
			reuse.remove(0);
			// swap to the reuse vector as our primary source
			useReuse = true;
			// return image
			return image;
		}

		return null;

	}
	
	/* gets image urls from Google
	 * args: String searchText: the thing that we want to search Google images for - provided by user in client
	 * 		 int numImages: the number of images we need
	 * returns: Vector<String> of URLs. This will either be null 
	 * 										(if < REQUIRED_IMAGES images found) 
	 * 									or contain REQUIRED_IMAGES image URLs of the
	 * 									top REQUIRED_IMAGES image results for searchText on Google.
	 */
	private Vector<String> getImages(String searchText, int numImages) {
		// IMPORTANT NOTE: Google Custom Search API only allows return of
		// GOOGLE_SEARCH_LIMIT items at a time.
		
		// SOLUTION: We run the code to fetch GOOGLE_SEARCH_LIMIT items several times,
		// offsetting the start index by GOOGLE_SEARCH_LIMIT each time.
		// That is, the contents of the loop is the code to generate GOOGLE_SEARCH_LIMIT
		// image URLs.
		// In the case of the example values -
		// GOOGLE_SEARCH_LIMIT = 10, numImages = 30,
		// The loop itself executes 3 times, grabbing the
		// first 10, 11-20, 21-30th images.

		// setting up data structures that will be populated by data later
		BufferedReader br; // will be used to read from Google API's input stream
		StringBuilder builder = null; // will build a string out of lines from the input stream
		JSONObject json = null; // holds the JSON object form of the data
		Vector<String> imageURLs = new Vector<String>(); // vector to hold all 30 image urls (to be returned later)

		// offset / search loop
		/*
		 * loop will perform the following duties: 1. grab next 10 images from Google 2.
		 * parse data, which is returned as a JSON file, to obtain URLs 3. append URLs
		 * to our vector of URLs
		 */
		try {
			for (int offset = 0; offset <= (numImages - 1)
					/ GOOGLE_SEARCH_LIMIT * GOOGLE_SEARCH_LIMIT /*
																 * REQUIRED_IMAGES rounded down to multiple of
																 * GOOGLE_SEARCH_LIMIT
																 */; offset += GOOGLE_SEARCH_LIMIT) { // loops, getting
																										// GOOGLE_SEARCH_LIMIT
																										// images each
																										// time, until
																										// enough images
																										// gathered
				// setting up search parameters
				String qry = "";
				try {
					qry = URLEncoder.encode(searchText, "UTF-8"); // url encoding the raw search string
				} catch (UnsupportedEncodingException e) {
					// should never happen.. unless "UTF-8" is not supported anymore..
				}

				String key = API_KEY;
				String cx = SEARCH_ENGINE_KEY;
				String fileType = SEARCH_FILETYPES;
				String searchType = "image"; // type of search to perform
				String startIndex = String.valueOf(offset + 1); // the offset + 1 is the index of the Google results
																// that we want to start returning our
																// GOOGLE_SEARCH_LIMIT results from. Results are
																// 1-indexed.
				// e.g. when offset = 0, start = 1, which means we are returning results
				// 1-GOOGLE_SEARCH_LIMIT.

				// creating connection with Google Custom Search API - see API documentation for
				// parameter details
				HttpURLConnection conn = null;
				try {
					URL url = new URL("https://www.googleapis.com/customsearch/v1?key=" + key + "&cx=" + cx + "&q="
							+ qry + "&fileType=" + fileType + "&searchType=" + searchType + "&alt=json&start="
							+ startIndex);
					conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
				} catch (IOException e1) {
					// catches exception where we fail to connect to Google API
				}

				// the next two steps will require the string builder
				// string builder will be used to read data from Google's input stream and be
				// converted into a JSON object for parsing
				builder = new StringBuilder();

				// retrieving result from Google connection's input stream
				try {
					br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
					String line; // temporary variable to store lines from buffered reader as we read from input
									// stream

					// read each line one by one and append to our string builder
					while ((line = br.readLine()) != null) {
						builder.append(line);
					}
					// terminate connection with Google
					conn.disconnect();
				} catch (IOException e1) {
					// catches exception where google doesn't have any results for us
				}
				// Google returns result in JSON format
				// converting result to JSON object and then parsing that JSON object to extract
				// data
				String imageUrl = null; // temporary variable to hold current image url as we iterate through data
				try {
					json = new JSONObject(builder.toString());
					for (int i = 0; i < GOOGLE_SEARCH_LIMIT; i++) {
						imageUrl = json.getJSONArray("items").getJSONObject(i).getString("link");
						imageURLs.addElement(imageUrl);
					}
				} catch (JSONException e) {
					// catches exception where the result google gave to us is not valid (perhaps api key bad, etc)
				}
			} // end offset and search loop
		} catch (Exception e) {
			// if any other exception occurs in the image sourcing, assume Google failure
		}
		// check that numImages image URLs were found
		if (imageURLs.size() >= numImages) {
			// if more than numImages URLs found, return only REQUIRED_IMAGES of them
			Vector<String> imageURLsLimited = new Vector<String>(); // will hold only first REQUIRED_IMAGES URLs
			for (int i = 0; i < numImages; i++) {
				imageURLsLimited.add(imageURLs.get(i));
			}
			return imageURLsLimited;
		} else {
			return null; // return value of null indicates that < REQUIRED_IMAGES images were found
		}
	} // end getImages(String)

	/* creates a vector of BufferedImages from given image URLs
		args: vector of strings (urls)
		returns: vector of bufferedimages from those strings
	*/
	public Vector<BufferedImage> urlsToBufferedImages(Vector<String> imageSource) throws Exception {
		Vector<BufferedImage> bufferedImageVec = new Vector<BufferedImage>();

		// Parallelize getting images from URL string
//		for(int k = 0; k < imageSource.size(); k++)
//		{
//			String urlString = imageSource.get(k);
//			URL url;
//			HttpURLConnection conn = null;
//			BufferedImage image = null;
//			try {
//				url = new URL(urlString);
//				conn = (HttpURLConnection) url.openConnection();
//				conn.setRequestProperty("User-Agent",
//						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
//				image = ImageIO.read(conn.getInputStream());
//
//				if (image != null) {
//					bufferedImageVec.add(image);
//				}
//			} catch (Exception e) {
//				// catches any problem with grabbing the image from url
//			}
//		}		
		imageSource.parallelStream().forEach((urlString) -> {
			URL url;
			HttpURLConnection conn = null;
			BufferedImage image = null;
			try {
				url = new URL(urlString);
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty("User-Agent",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
				image = ImageIO.read(conn.getInputStream());

				if (image != null) {
					bufferedImageVec.add(image);
				}
			} catch (Exception e) {
				// catches any problem with grabbing the image from url
			}
		});
		return bufferedImageVec;
	} // end urlsToBufferedImages()

}
