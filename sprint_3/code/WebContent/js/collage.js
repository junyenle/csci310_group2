/**
 * Handles the user interface logic for collage.jsp and enables
 * various buttons to call the appropriate servlets
 */

/**
 * TODO:
 * - Add more functionality to the save collage button
 * - Apply requested changes to the options menu
 * - Prevent the user from going straight to other pages without
 *   logging in
 * - Use encryption aka HTTPS
 * - Allow it to not set any options
 * - Find and add error handling
 * - Get the delete functionality working + AJAX call
 * - Add the dropdown menu with these inputs
 *      - With options: Black and White, Sepia, Greyscale
 *      - Send these inputs: blacknwhite, greyscale, and sepia
 */

// Flag to tell us that the collage could not be generated
let fail = false;

// Option data that the user wants to do 
let optionData = {
    collageBorderWidth: "0",
    collageBorderColor: "",
    photoBorderWidth: "0",
    photoBorderColor: "",
    minRotation: "-45",
    maxRotation: "45",
    collageWidth: "800",
    collageHeight: "600",
    filter: ""
};

// Store the collage we loaded and its index
let clickedCollageIndex = -1;

// Attach the event listener here
$(document).ready( () => {
	
    // Gets the reference to the div for the error message
    let error = $('#error')[0];

    // Disable the export button if necessary
    if (error != null) {
        $('#export').attr("disabled", true);
    }
    else {
        $('#export').attr("disabled", false);
    }
    
    // By default, disable delete button
    $('#deletebutton').attr("disabled", true);

    // Attach the search button listener
    $('#searchbutton').click(buildCollage);

    // Export collage on button click
    $('#export').click(downloadImage);

    // Display the options button
    $('#optionbutton').click(showOptions);

    // Swap the collages when clicking on the previous collage
    $('.prev-collage').on('click', function(e){
        e.preventDefault();
        
        // Swap the collage
        swapCollage(this);
        clickedCollageIndex = $(this).index();
        console.log(clickedCollageIndex);
        // let index = $(this).index();
        // console.log(index);
    });

    // Check if the search box is empty or not
    $('#searchtext').keyup(disableSearchButton);

    // Permit the enter key
    $(document).keyup(permitEnterKey);

    // Save the collage button
    $('#savebutton').click(saveCollage);

    // Delete the collage we loaded into the main collage
    $('#deletebutton').click(deleteCollage); 
});

// Permit the Enter key to search and build the collage
let permitEnterKey = (e) => {
    var key = e.keyCode ? e.keyCode : e.which;
    if (key == 13) {
        $('#searchbutton').click();
    }  
}

// Swap the collage
let swapCollage = function(prev) {

    // Get the fields we need to swap the collage
    let collage = $("#collage")[0];     // Container that holds collage
    let main = $('#main')[0];           // Main collage Image
    let title = $("h1")[0];             // Title of collage
    let error = $('#error')[0];         // Error message
    let prevCollage = prev;             // Selected previous collage

    // Temporary variables
    let prevTemp = $(prevCollage).attr("src");
    let prevAlt = $(prevCollage).attr("alt"); 

    if (error != null) {
        // Remove div that has the insufficient image error message
        collage.removeChild(error); 

        // Move clicked image to main collage space
        let newImage = document.createElement('img');
        $(newImage).attr("id", "main");
        $(newImage).attr("src", prevTemp);
        $(newImage).attr("alt", prevAlt);

        // Replace collage main space inner HTML with newImage
        $(collage).html(""); 
        collage.appendChild(newImage);

        // Remove the event handler and the previous collage since it's not necessary
        $(prevCollage).off('click');
        $(collage).removeChild(prevCollage);

        // Change title
        title.html("Collage for topic " + prevAlt);

        // Reset the value back to the default
        fail = false;    
    }
    else {

        // Only swap the main one, since we're getting rid of previously viewed
        $(main).attr("src", prevTemp);
        $(main).attr("alt", prevAlt);

        // Change title
        $(title).html("Collage for topic " + prevAlt);   
    }
    
    // enable the delete button
    $('#deletebutton').attr("disabled", false);
}

// Disable search button
let disableSearchButton = () => {
    // Disable if empty
    let searchTerm = $('#searchtext').val();
    if (searchTerm.length > 0) {
        // Removes the disabled attribute
        $('#searchbutton').removeAttr("disabled");
    }
    else {
        // Disables the build button
        $('#searchbutton').attr("disabled", "true");
    }  
}

// Download image
let downloadImage = () => {
    let main = $('#main')[0];

    // Create the temporary a tag
    let a = $("<a>").attr("href",main.src).attr("download", main.alt+" collage.png").appendTo("body");
    a[0].click();
    a.remove();
}

// Build the collage
let buildCollage = () => {

    // Data to be sent to the server
    const term = $('#searchtext').val();
    const shape = $('#shapetext').val();
    const browserWidth = $(window).width();
    const browserHeight = $(window).height();

    // Get the fields we need to send data to the servlet
    let collage = $("#collage")[0];
    let title = $("h1")[0];
    let main = $('#main')[0];
    let prev = $("#prev")[0];
    console.log(term);
    console.log(shape);

    // Perform the AJAX call to servlet
    $.ajax({
        type: "GET",
        url: "collageBuilderServlet",
        data: {
            searchText: term,
            shapeText: shape, 
            browserWidth: browserWidth,
            browserHeight: browserHeight,
            collageBorderWidth: optionData.collageBorderWidth,
            collageBorderColor: optionData.collageBorderColor,
            photoBorderWidth: optionData.photoBorderWidth,
            photoBorderColor: optionData.photoBorderColor,
            minRotation: optionData.minRotation,
            maxRotation: optionData.maxRotation,
            collageWidth: optionData.collageWidth,
            collageHeight: optionData.collageHeight,
            filter: optionData.filter
        },
        success: (response) => {
            let res = response.split(" ");
            if (res[0] == "success") {
                //location.reload();
                fail = false;
            }
            else {
                if (fail == false) {
                    // Create a new div and img element
                    let newDiv = document.createElement("div");
                    newDiv.classList.add("previousimgcontainer");
                    let newImage = document.createElement("img");

                    // Set source and alt of new image
                    $(newImage).attr("src", main.src);
                    $(newImage).attr("alt", main.alt);

                    // Append to previous collage picker div
                    newDiv.appendChild(newImage);
                    prev.appendChild(newDiv);

                    // Reset previous collage object
                    previousCollages.prev.getElementsByTagName("img");

                    // Create "Insufficient number of images found" text
                    newDiv = document.createElement("div");
                    $(newDiv).attr("id", "error");
                    $(newDiv).html("Insufficient number of images found");

                    // Set "Insufficient number of images found" text
                    $(collage).html("");
                    collage.appendChild(newDiv);
                    exportButton.disabled = true;

                    // Set the title as error
                    $(title).html("Collage for topic "+ res[1]);
                    fail = true;
                }
                else {
                    $(title).html("Collage for topic "+ res[1]);
                }
            }

            location.reload();
        }
    });
}

// Show the options
let showOptions = () => {

    /**
     *  Varables for dataOptions
     *  - private int collageBorderWidth;
     *  - private String collageBorderColor;
     *  - private int photoBorderWidth;
     *  - private String photoBorderColor;
     *  - private int minRotation;
     *  - private int maxRotation;
     *  - private int collageWidth;
     *  - private int collageHeight;
     *  - private String filter; 
     */
    let collageBorderWidthInput = '<label>Collage Border Width</label>' + 
                                  '<input name="collageBorderWidth" type="text" value="0" />';
    let collageBorderColorInput = '<label>Collage Border Color</label>' + 
                                  '<input name="collageBorderColor" type="text" value="" />';
    let photoBorderWidthInput = '<label>Photo Border Width</label>' + 
                                '<input name="photoBorderWidth" type="text" value="0" />';
    let photoBorderColorInput = '<label>Photo Border Color</label>' + 
                                '<input name="photoBorderColor" type="text" value="" />';
    let minRotationInput = '<label>Minimum Rotation</label>' + 
                           '<input name="minRotation" type="text" value="-45" />';
    let maxRotationInput = '<label>Maxium Rotation</label>' + 
                           '<input name="maxRotation" type="text" value="45" />';
    let collageWidthInput = '<label>Collage Width</label>' + 
                            '<input name="collageWidth" type="text" value="800" />';
    let collageHeightInput = '<label>Collage Height</label>' + 
                             '<input name="collageHeight" type="text" value="600"/>';
    let filterInput =   '<label>Filter</label>' + 
                        '<div>' +
                            '<input type="radio" id="bnw" name="filter"  value="blacknwhite">' +
                            '<label id="bnw-label" for="bnw">Black and White</label>' +
                            '<input type="radio" id="greyscale" name="filter"  value="greyscale">' +
                            '<label id="greyscale-label" for="greyscale">Greyscale</label>' +
                            '<input type="radio" id="sepia" name="filter"  value="sepia">' +
                            '<label id="sepia-label" for="sepia">Sepia</label>' +
                        '</div>';

    vex.dialog.open({
        message: 'Please select all the options for these collages',
        input: [
            collageBorderWidthInput,
            collageBorderColorInput,
            photoBorderWidthInput,
            photoBorderColorInput,
            minRotationInput,
            maxRotationInput,
            collageWidthInput,
            collageHeightInput,
            filterInput
        ].join(''),
        buttons: [
            $.extend({}, vex.dialog.buttons.YES, { text: 'Set Options' }),
            $.extend({}, vex.dialog.buttons.NO, { text: 'Cancel' })
        ],
        callback: (data) => {

            // TODO: Get more information about this option functionality
            if (data) {
                console.log("Got this data: " + data);
                if (!('filter' in data)) {
                    data.filter = "";
                }
                optionData = data;
            }
            else {
                console.log(data);
            }
        }
    });
}

// Save the collages
let saveCollage = () => {

    // AJAX call to the save collage servlet
    $.ajax({
        type: "POST",
        url: "saveCollageServlet",
        success: (response) => {
            let res = response.split(' ');
            if (res[0] == "success") {
                console.log("SUCCESS :)");
                location.reload(true);
            }
            else {
                console.log("FAILURE :(");
                location.reload(true);
            }
        }
    });
}

// Delete the collages
let deleteCollage = () => {
    if (clickedCollageIndex != -1) {
    	console.log("deleting: " + clickedCollageIndex);
        // We'll add the ajax later :)
        $.ajax({
            type: "POST",
            url: "deleteCollageServlet",
            data: {
                clickedIndex: clickedCollageIndex
            },
            success:(response) => {
                let res = response.split(' ');
                if (res[0] == "success") {
                    console.log("SUCCESS :))))");
                    location.reload(true);
                } 
                else {
                    console.log("FAILURE :(");  
                    location.reload(true);
                }
            }
        });
    } 
}
