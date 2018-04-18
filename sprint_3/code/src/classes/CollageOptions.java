package classes;

public class CollageOptions {
	/* class simply holds user options for the collage in a single object for convenient access */
	
	private int collageBorderWidth;
	private String collageBorderColor;
	private int photoBorderWidth;
	private String photoBorderColor;
	private int minRotation;
	private int maxRotation;
	private int collageWidth;
	private int collageHeight;
	private String filter;

	
	/* constructor
	 * sets data elements to user's paramaters
	 * args: all of the settings currently supported
	*/
	public CollageOptions(int cbw, String cbc, int pbw, String pbc, int minr, int maxr, int cw, int ch, String fil) {
		this.collageBorderWidth = cbw;
		this.collageBorderColor = cbc;
		this.photoBorderWidth = pbw;
		this.photoBorderColor = pbc;
		this.minRotation = minr;
		this.maxRotation = maxr;
		this.collageWidth = cw;
		this.collageHeight = ch;
		this.filter = fil;
	}

	public int getCollageBorderWidth() {
		return collageBorderWidth;
	}

	public String getCollageBorderColor() {
		return collageBorderColor;
	}

	public int getPhotoBorderWidth() {
		return photoBorderWidth;
	}

	public String getPhotoBorderColor() {
		return photoBorderColor;
	}

	public int getMinRotation() {
		return minRotation;
	}

	public int getMaxRotation() {
		return maxRotation;
	}

	public int getCollageWidth() {
		return collageWidth;
	}

	public int getCollageHeight() {
		return collageHeight;
	}

	public String getFilter() {
		return filter;
	}
}
