<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>SCollage</title>

		<!-- VEX -->
		<script src="js/vex.combined.min.js"></script>
		<script>vex.defaultOptions.className = 'vex-theme-os'</script>
		<link rel="stylesheet" href="styles/vex.css" />
		<link rel="stylesheet" href="styles/vex-theme-os.css" />
		
		<!-- BOOTSTRAP AND JQUERY-->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		
		<!-- FRONTEND -->
		<script src="js/index.js"></script>
		<link rel="stylesheet" type="text/css" href="styles/index.css">
	</head>
	<body>
		<div id="search-wrapper">
			<div id="title">
				<h1>Build Your Collage</h1>
			</div>
			<div id="input-container" class="input-group">
				<input type="text" id="searchtext" class="form-control" placeholder="Enter topic">
				<input type="text" id="shapetext" class="form-control" placeholder="Enter shape">	
			</div>
			<div id="search-button-container">
				<button id="searchbutton" class="btn btn-primary">Build Collage</button>	
				<button id="optionbutton" class="btn btn-primary">Options</button>
			</div>
		</div>
	</body>
</html>




