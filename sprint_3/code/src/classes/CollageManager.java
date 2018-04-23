package classes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Base64;
import java.util.Vector;

import javax.imageio.ImageIO;

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

	/*
	 * inserts collage into collages / collageTitles vectors args: collage title
	 * (string), collage (bufferedimage)
	 */
	public void insertCollage(String title, BufferedImage collage) {
		collageTitles.add(title);
		collages.add(collage);
	}

	/*
	 * inserts collage into savedCollages / savedCollageTitles vectors args: collage
	 * title (string), collage (bufferedimage)
	 */
	public void insertSavedCollage(String title, BufferedImage collage) {
		savedCollageTitles.add(title);
		savedCollages.add(collage);
	}

	/*
	 * checks if two images are equivalent by performing pixel-by-pixel comparison
	 * args: bufferedimages A and B returns: true if A == B, false otherwise
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

	/* inserts topmost collage into savedCollages / savedCollageTitles vectors */
	public boolean saveCollage(String username) {
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

		// throw it into the file system
		byte[] imageBytes;
		ByteArrayOutputStream byteArrayOS;
		byteArrayOS = new ByteArrayOutputStream();
		String base64String;
		try {
			// get base64 rep of image
			ImageIO.write(collages.get(collages.size() - 1), "png", byteArrayOS);
			imageBytes = byteArrayOS.toByteArray();
			imageBytes = Base64.getEncoder().encode(imageBytes);
			base64String = new String(imageBytes, "UTF-8");
			System.out.println(base64String);

			// get image title
			String title = collageTitles.get(collageTitles.size() - 1);

			// get file to write
			String filename = "/home/student/Desktop/eclipse/userdata/" + username;
			try {
				final Path path = Paths.get(filename);
				Files.write(path, Arrays.asList(title + "|" + base64String + "|"), StandardCharsets.UTF_8,
						Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
				System.out.println("SUCCESSFULLY managed file: " + filename);
			} catch (final IOException ioe) {
				// failed to manage file
				System.out.println("FAILED to manage file: " + filename);
			}

		} catch (Exception e) {
			// indicates failure to write to file
		}

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

	public void wipeCollages() {
		collages.clear();
		collageTitles.clear();

	}

	public boolean deleteCollage(String username, int index) {
		// first, delete collage from data structures
		savedCollages.remove(index);
		savedCollageTitles.remove(index);
		
		// second, re-write the save file
		// get file to write
		String filename = "/home/student/Desktop/eclipse/userdata/" + username;
		final Path path = Paths.get(filename);
		// delete it to start anew
		try {
			Files.deleteIfExists(path);
		} catch (IOException e1) {
			// file doesn't exist
			System.out.println("savefile " + filename + " dne");
		}
		// now actually rewrite every image
		for(int i = 0; i < savedCollages.size(); i++) {
			byte[] imageBytes;
			ByteArrayOutputStream byteArrayOS;
			byteArrayOS = new ByteArrayOutputStream();
			String base64String;
			try {
				// get base64 rep of image
				ImageIO.write(savedCollages.get(i), "png", byteArrayOS);
				imageBytes = byteArrayOS.toByteArray();
				imageBytes = Base64.getEncoder().encode(imageBytes);
				base64String = new String(imageBytes, "UTF-8");
				System.out.println(base64String);

				// get image title
				String title = savedCollageTitles.get(i);

				try {
					Files.write(path, Arrays.asList(title + "|" + base64String + "|"), StandardCharsets.UTF_8,
							Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
					System.out.println("SUCCESSFULLY managed file: " + filename);
				} catch (final IOException ioe) {
					// failed to manage file
					System.out.println("FAILED to manage file: " + filename);
				}

			} catch (Exception e) {
				// indicates failure to write to file
				return false;
			}
		}
		
		return true;
		
		
	}
}
