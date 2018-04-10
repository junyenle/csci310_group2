package classes;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.Vector;

import javax.imageio.ImageIO;

public class CollageBuilder {
	private int browserHeight;
	private int browserWidth;
	
	private int collageWidth;
	private int collageHeight;
	private Color photoBorderColor;
	private Color collageBorderColor;
	
	private CollageOptions options;
	
	// Given constant values from requirements
	private static int imagePadding = 3; // Each image has 3px white padding
	private static double imageRatio = 1/20.0; // Each image is 1/20 of the image size
	private static int numImages = 30;

	public CollageBuilder(CollageOptions options, int inBrowserWidth, int inBrowserHeight)
	{
		this.options = options;
		browserWidth = inBrowserWidth;
		browserHeight = inBrowserHeight;
	
		/* Collage width must be between 40 and 70% of the browser viewport width
		 * Collage height must be between 35 and 50% of the browser viewport height
		 * Set collage dimensions to the maximum possible percentage given in requirements
		 */
		this.collageWidth = (int) (0.7*browserWidth);
		this.collageHeight = (int) (0.5*browserHeight);
		
		/* Collage width must be at least 800px and height must be at least 600px
		 * Ensure that collage meets minimum size requirements
		 */
		this.collageWidth = Math.max(this.collageWidth, 800);
		this.collageHeight = Math.max(this.collageHeight, 600);
		
		this.collageWidth = options.getCollageWidth();
		this.collageHeight = options.getCollageHeight();
		
		// set photo border color
		try {
		    Field field = Class.forName("java.awt.Color").getField(options.getPhotoBorderColor());
		    this.photoBorderColor = (Color)field.get(null);
		} catch (Exception e) {
		    this.photoBorderColor = new Color(0,0,0,0); // default color is transparent
		}
		
		// set collage border color
		try {
		    Field field = Class.forName("java.awt.Color").getField(options.getCollageBorderColor());
		    this.collageBorderColor = (Color)field.get(null);
		} catch (Exception e) {
		    this.collageBorderColor = new Color(0,0,0,0); // default color is transparent
		}
	}
	
	// builds a collage in the shape of the "shape" string
		public BufferedImage buildCollage(ImageSourcer s, String shape) {
			// determine whether height or width is limiting factor
			int gridSideLength = 0;
			if(collageWidth / shape.length() > collageHeight) {
				// height is limiting factor
				gridSideLength = collageHeight;
			}
			else {
				// width is limiting factor
				gridSideLength = collageWidth / shape.length();
			}
			
			// determine number of images per character
			int letterCount = 0;
			for(int i = 0; i < shape.length(); i++) {
				if(shape.charAt(i)==' ') {
					continue;
				}
				else {
					letterCount = letterCount + 1;
				}
			}
			
			// each grid square is a ratio of total grid side
			int gridSquareSideLength = (int)(gridSideLength/4.5 * (Math.pow(letterCount, (1/2.5))));
			
			// array of character bufferedimages
			Vector<BufferedImage> characterImages = new Vector<BufferedImage>();

			
			int imagesPerLetter = (30 + (letterCount-1)) / letterCount;
			System.out.println("number of letters: " + letterCount);
			System.out.println("images per letter: " + imagesPerLetter);
			
			// for each character
			for(int i = 0; i < shape.length(); i++) {
				
				// create the character image
				BufferedImage characterImage = new BufferedImage(gridSideLength, gridSideLength, BufferedImage.TYPE_INT_ARGB);		
				Graphics2D g2d = characterImage.createGraphics();
				g2d.setColor(Color.white);
				g2d.fill(new Rectangle2D.Float(0,0,gridSideLength,gridSideLength));
				

				// construct a character collage for the character UNLESS char is ' '
				if(shape.charAt(i) != ' ') {
					// apply background image
					BufferedImage backgroundImage = s.getImage();
					backgroundImage = getScaledImage(backgroundImage, gridSideLength, gridSideLength);
					g2d.drawImage(backgroundImage, 0, 0, null);
					
					// variables to hold coordinates for next image
					int xCoord = gridSideLength/8;
					int yCoord = gridSideLength/2;
					
					// apply the rest of the images
					for(int j = 0; j < imagesPerLetter - 1; j++) {
						
						// get image
						BufferedImage nextImage = s.getImage();
						
						// resize image
						// check whether height or width is larger.. we will scale this down
						int sourceWidth = nextImage.getWidth();
						int sourceHeight = nextImage.getHeight();
						int scaledWidth = 0;
						int scaledHeight = 0;
						if(sourceWidth > sourceHeight) {
							// most likely case.. we need to determine the appropriate height scale
							scaledHeight = (int)(((float)gridSquareSideLength / sourceWidth) * sourceHeight);
							// and desired width is just gridlength
							scaledWidth = gridSquareSideLength;
						}
						else {
							// unlikely case.. determine appropriate width scale
							scaledWidth = (int)(((float)gridSquareSideLength / sourceHeight) * sourceWidth);
							// and desired height is just gridlength
							scaledHeight = gridSquareSideLength;
						}
						// actual resize now
						double bloatModifier = 1.2;
						nextImage = getScaledImage(nextImage, (int)(scaledWidth * bloatModifier), (int)(scaledHeight * bloatModifier));
						
						// add padding
						nextImage = addPadding(nextImage, options.getPhotoBorderWidth(), this.photoBorderColor); 
						
						// rotate image
						int min = options.getMinRotation();
						int max = options.getMaxRotation();
						Random randomRotator = new Random();
						int randNum = randomRotator.nextInt((max - min)+1) + min;
						nextImage = rotateImage(nextImage, randNum);
						
						
						// prepare location
						xCoord += scaledWidth;
						if(xCoord > gridSideLength - scaledWidth - gridSideLength/16) {
							xCoord = gridSideLength/8;
							yCoord += scaledHeight;
						}
						if(yCoord > gridSideLength - scaledHeight - gridSideLength/16) {
							yCoord = gridSideLength/8;
							xCoord = gridSideLength/8;
						}

						// place image
						g2d.drawImage(nextImage, xCoord, yCoord, null);
//						System.out.println("coords: " + xCoord + ", " + yCoord);
						
						
					} // end for each image
				} // end "if ' '"
				
				// get overlay character
				BufferedImage characterOverlay = null;
				
				if(shape.charAt(i)!=' ') { // if letter is not blank
					String filename = "C:\\Users\\Jun\\eclipse-workspace\\CS310-ProjectTwo\\letters\\" + shape.charAt(i) + ".png";
					try {
						characterOverlay = ImageIO.read(new File(filename));
					} catch (IOException e) {
						System.out.println("failed to open overlay file: " + filename);
					       System.out.println("Working Directory = " +
					               System.getProperty("user.dir"));
					}
				}
				else { // if letter is blank
					characterOverlay = new BufferedImage(500,500, BufferedImage.TYPE_INT_ARGB);
					Graphics2D colorFill = characterOverlay.createGraphics();
					Color color = Color.white;
					colorFill.setColor(color);
					colorFill.fill(new Rectangle2D.Float(0,0,500,500));
				}
				
				// resize overlay character
				characterOverlay = getScaledImage(characterOverlay, gridSideLength, gridSideLength);
				
				// apply overlay character
				g2d.drawImage(characterOverlay, 0, 0, null);
				
				
				// add new character image to vector
				characterImages.add(characterImage);
										
			} // end for each character
			
			// create large bufferedimage for entire shape
			BufferedImage collage = new BufferedImage(collageWidth, collageHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = collage.createGraphics();
			g2d.setColor(Color.white);
			g2d.fill(new Rectangle2D.Float(0,0,collageWidth,collageHeight));
			
			// determine y offset to centre images 
			int yOffset = (int)((collageHeight - gridSideLength)/2.0);
			// determine x offset to centre images
			int xOffset = (int)(collageWidth - (gridSideLength * shape.length()))/2;
			// draw all the character images
			for(int i = 0; i < characterImages.size(); i++) {
				g2d.drawImage(characterImages.get(i), xOffset + i * gridSideLength, yOffset, null);
			}
			
			// add border to collage
			collage = addPadding(collage, options.getCollageBorderWidth(), this.collageBorderColor);
			
			// apply filter, if exists
			// TODO
				
			
			return collage;
		} // buildCollage end
		
	    // Rotates the given image by the given number of degrees and returns BufferedImage
	    public BufferedImage rotateImage(BufferedImage src,int inDegrees) {
	        // Calculate the width and height of the rotated image
	        double rad = Math.toRadians(inDegrees);
	        double sin = Math.abs(Math.sin(rad)), cos = Math.abs(Math.cos(rad));
	        int srcWidth = src.getWidth(), srcHeight = src.getHeight();
	        int rotWidth = (int)Math.floor(srcWidth*cos+srcHeight*sin), rotHeight = (int) Math.floor(srcHeight*cos + srcWidth*sin);
	       
	        // Rotate the image using Graphics2D
	        BufferedImage rotatedImage = new BufferedImage(rotWidth, rotHeight,
	                BufferedImage.TRANSLUCENT);
	        Graphics2D g2d = rotatedImage.createGraphics();
	        g2d.rotate(Math.toRadians(inDegrees), rotWidth/2, rotHeight/2);
	        g2d.drawImage(src, (rotWidth-srcWidth)/2, (rotHeight-srcHeight)/2, null);
	        g2d.dispose();
	        return rotatedImage;
	    }
		
	    // scales the image based on desired width and height
	    private BufferedImage getScaledImage(BufferedImage src, int width, int height){
	        int srcWidth = src.getWidth();
	        int srcHeight = src.getHeight();
	        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	       
	        // Scale image according to the ratio of the original image to new scaled image
	        AffineTransform scaleOp = new AffineTransform();
	        scaleOp.scale(width/(double)srcWidth, height/(double)srcHeight);
	   
	        // Use Graphics2D to complete the scaling
	        Graphics2D g2d = scaledImage.createGraphics();
	        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2d.setTransform(scaleOp);
	        g2d.drawImage(src, 0, 0, null);
	        g2d.dispose();
	       
	        return scaledImage;
	    }
	    
	    // Add white padding of imagePadding pixels around given image
	    private BufferedImage addPadding(BufferedImage src, int width, Color color) {
	    	int imagePadding = width;
	        int srcWidth = src.getWidth(), srcHeight = src.getHeight();
	        int paddedWidth = srcWidth+imagePadding*2, paddedHeight = srcHeight+imagePadding*2;
	       
	        // Create a white rectangle based on calculated width and height and draw image on top
	        BufferedImage paddedImage = new BufferedImage(paddedWidth, paddedHeight, src.getType());
	        Graphics2D g2d = paddedImage.createGraphics();
	        g2d.setColor(color);
	        g2d.fillRect(0, 0, paddedWidth, paddedHeight);
	        g2d.drawImage(src, imagePadding, imagePadding, null);
	        g2d.dispose();
	        return paddedImage;
	    }
	
	
}