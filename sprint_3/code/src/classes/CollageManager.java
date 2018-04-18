package classes;

import java.awt.image.BufferedImage;
import java.util.Vector;


public class CollageManager {
	// Vector holding all titles of Collages in the session
	private Vector<String> collageTitles = new Vector<String>();
	// Vector holding all Collages in the session
	private Vector<BufferedImage> collages = new Vector<BufferedImage>();
	// Vector holding all saved collages
	private Vector<BufferedImage> savedCollages = new Vector<BufferedImage>();
	// Vector holding all saved collage titles
	private Vector<String> savedCollageTitles = new Vector<String>();

	/* uses default constructor */
	
	/* inserts collage into collages / collageTitles vectors
	 * args: collage title (string), collage (bufferedimage)
	*/
	public void insertCollage(String title, BufferedImage collage) {
		collageTitles.add(title);
		collages.add(collage);
	}

	/* checks if two images are equivalent by performing pixel-by-pixel comparison
	 * args: bufferedimages A and B
	 * returns: true if A == B, false otherwise
	 */
	public static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
		// check image sizes
		if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
			return false;
		}

		int width = imgA.getWidth();
		int height = imgA.getHeight();

		// for each pixel
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// compare pixels
				if (imgA.getRGB(j, i) != imgB.getRGB(j, i)) {
					return false;
				}
			}
		}

		return true;
	}

	/* inserts topmost collage into savedCollages / savedCollageTitles vectors	*/
	public boolean saveCollage() {
		if (collages.isEmpty()) {
			return false;
		}
		// first check to make sure the displayed collage is not already saved
		BufferedImage displayedCollage = collages.get(collages.size() - 1);
		for (int i = 0; i < savedCollages.size(); i++) {
			BufferedImage oldCollage = savedCollages.get(i);
			if (CollageManager.compareImages(oldCollage, displayedCollage)) {
				return false;
			}
		}
		// no match found
		savedCollages.add(displayedCollage);
		savedCollageTitles.add(collageTitles.get(collageTitles.size() - 1));
		return true;
	}

	// Getter
	public Vector<String> getCollageTitles() {
		return collageTitles;
	}

	// Getter
	public Vector<BufferedImage> getCollages() {
		return collages;
	}

	// Getter
	public Vector<BufferedImage> getSavedCollages() {
		return savedCollages;
	}

	// Getter
	public Vector<String> getSavedCollageTitles() {
		return savedCollageTitles;
	}
}
