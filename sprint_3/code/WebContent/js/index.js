/**
 * TODO:
 * - Apply requested changes to the options menu
 * - Prevent the user from going straight to other pages without
 *   logging in
 * - Use encryption aka HTTPS
 * - Allow it to not set any options
 */

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

// When document is ready bind the event listeners
$(document).ready( () => {
    // Attach the search button listener
    $('#searchbutton').click(buildCollage);

    // Display the options button
    $('#optionbutton').click(showOptions);

    // Permit the enter key
    $(document).keyup(permitEnterKey);

    // Check if the search box is empty or not
    $('#searchtext').keyup(disableSearchButton);
});

// Permit the Enter key to search and build the collage
let permitEnterKey = (e) => {
    var key = e.keyCode ? e.keyCode : e.which;
    if (key == 13) {
        $('#searchbutton').click();
    }  
}

function hasValue(property) {
    return (typeof property != 'undefined' && property);
}

// Call server to build the collage
let buildCollage = () => {

    // The variables we will use to pass
    const term = $('#searchtext').val();
    const shape = $('#shapetext').val();
    const browserWidth = $(window).width();
    const browserHeight = $(window).height();

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
            console.log('success');
            window.location.href = "collage.jsp";
        }
    });
}

// Disable the search button when there is no text
let disableSearchButton = () => {

    // Check if the term was inputted
    const searchTerm = $('#searchtext').val();
    if (searchTerm.length > 0) {
        // Removes the disabled attribute
        $('#searchbutton').removeAttr("disabled");
    }
    else {
        // Disables the build button
        $('#searchbutton').attr("disabled", "true");
    } 
}

// Create the modal box that prompts the option lists
let showOptions = () => {

    /**
     *  Here are the variables for the options
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
                                  '<input name="collageBorderWidth" type="text" value="5" required />';
    let collageBorderColorInput = '<label>Collage Border Color</label>' + 
                                  '<input name="collageBorderColor" type="text" value="red" required />';
    let photoBorderWidthInput = '<label>Photo Border Width</label>' + 
                                '<input name="photoBorderWidth" type="text" value="5" required />';
    let photoBorderColorInput = '<label>Photo Border Color</label>' + 
                                '<input name="photoBorderColor" type="text" value="white" required />';
    let minRotationInput = '<label>Minimum Rotation</label>' + 
                           '<input name="minRotation" type="text" value="-35" required />';
    let maxRotationInput = '<label>Maxium Rotation</label>' + 
                           '<input name="maxRotation" type="text" value="35" required />';
    let collageWidthInput = '<label>Collage Width</label>' + 
                            '<input name="collageWidth" type="text" value="800" required />';
    let collageHeightInput = '<label>Collage Height</label>' + 
                             '<input name="collageHeight" type="text" value="600" required />';
    let filterInput =   '<label>Filter</label>' + 
                        '<div>' +
                            '<input type="radio" id="bnw" name="filter"  value="blacknwhite">' +
                            '<label id="bnw-label" for="bnw">Black and White</label>' +
                            '<input type="radio" id="greyscale" name="filter"  value="greyscale">' +
                            '<label id="greyscale-label" for="greyscale">Greyscale</label>' +
                            '<input type="radio" id="sepia" name="filter"  value="sepia">' +
                            '<label id="sepia-label" for="sepia">Sepia</label>' +
                        '</div>';

    // Open the vex dialog that will get the users data input 
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


