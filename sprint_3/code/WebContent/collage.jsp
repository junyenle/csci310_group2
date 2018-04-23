<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector"%>
<%@ page import="classes.*"%>
<%@ page import="java.awt.image.BufferedImage"%>
<%@ page import="java.io.*"%>
<%@ page import="java.io.IOException"%>
<%@ page import="javax.imageio.ImageIO"%>
<%@ page import="java.util.Base64"%>

<!DOCTYPE html>
<html>
	<head>
		<title>SCollage</title>

		<!-- VEX -->
		<script src="js/vex.combined.min.js"></script>
		<script>vex.defaultOptions.className = 'vex-theme-os'</script>
		<link rel="stylesheet" href="styles/vex.css" />
		<link rel="stylesheet" href="styles/vex-theme-os.css" />

		<!-- BOOTSTRAP AND JQUERY -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		
		<!-- FRONTEND -->
		<script src="js/collage.js"></script>
		<link rel="stylesheet" type="text/css" href="styles/collage.css">
	</head>
	<body>
			
		<%
		// Grabs CollageManager object from session
		CollageManager collageManager = (CollageManager) request.getSession(false).getAttribute("collageManager");
		// Grabs the search string from session
		String searchText = (String) request.getSession(false).getAttribute("currentSearchText");
		// Vector of collages from CollageManager
		Vector<BufferedImage> collages = collageManager.getCollages();
		// Vector of saved collages from CollageManager
		Vector<BufferedImage> savedCollages = collageManager.getSavedCollages();
		// Vector of collage titles from CollageManager
		Vector<String> collageTitle = collageManager.getCollageTitles();
		// Vector of saved collage titles from CollageManager
		Vector<String> savedCollageTitle = collageManager.getSavedCollageTitles();
		// ByteArrayOutputStream to convert the buffered images to base 64 string
		ByteArrayOutputStream byteArrayOS;
		// ByteArray to hold image bytes
		byte[] imageBytes;
		// Resultant base64 String for images
		String base64String;
		%>
		<div id="collage-wrapper">
			<div id="collage-container">
				<!-- TITLE -->
				<div id="title">
					<h1>Collage for topic <%=searchText%></h1>
				</div>
				
				<!-- MAIN COLLAGE -->
				<%
				// If collages is an empty vector, creates tag for error message
				if (collages.size() == 0) {
				%>
					<!-- Error Message Tag -->
					<div id="collage">
						<div id="error">Insufficient number of images found</div>
					</div>
				<%
				}
				// If collages is not empty, converts the last collage to base 64 string for display.
				else {
					byteArrayOS = new ByteArrayOutputStream();
					ImageIO.write(collages.get(collages.size()-1), "png", byteArrayOS);
					imageBytes = byteArrayOS.toByteArray();
					imageBytes = Base64.getEncoder().encode(imageBytes);
					base64String = new String(imageBytes, "UTF-8");
				%>
					<!-- Main Collage Displaying Tag -->
					<div id="collage">
						<img id="main" src="data:image/png;base64,<%=base64String%>" alt="<%=collageTitle.get(collageTitle.size()-1)%>">
					</div>
				<%} %>
			</div>
			<div id="input-container">
				<!-- TEXT BOX AND BUTTON -->
				<div id="inputdiv" class="input-group">
					<input type="text" class="form-control" id="searchtext" placeholder="Enter topic">
					<input type="text" class="form-control" id="shapetext" placeholder="Enter shape">
				</div>
				<div class="loadAnimation"><h1 style="color:rgba(0,0,0,0);" >loading</h1></div>
				<div id="button-container">
					<button id="searchbutton" class="btn btn-primary">Build Collage</button>
					<button id="export" class="btn btn-primary">Export Collage</button>
					<button id="optionbutton" class="btn btn-primary">Options</button>
					<button id="savebutton" class="btn btn-primary">Save Collage</button>
					<button id="deletebutton" class ="btn btn-primary">Delete Collage</button>
				</div>
			</div>
			<div id="previous-container">
				<!-- PREVIOUS COLLAGES -->
				<div id="prev">
					<%
					// Iterates through all collages in session
					for (int i = 0; i < savedCollages.size(); i++) {
					%>
						<%
						byteArrayOS = new ByteArrayOutputStream();
						ImageIO.write(savedCollages.get(i), "png", byteArrayOS);
						imageBytes = byteArrayOS.toByteArray();
						imageBytes = Base64.getEncoder().encode(imageBytes);
						base64String = new String(imageBytes, "UTF-8");
						%>
						<img class="prev-collage" src="data:image/png;base64,<%=base64String%>" alt="<%=savedCollageTitle.get(i)%>">
					<%
					}
					%>
				</div>
			</div>
		</div>
	</body>
</html>