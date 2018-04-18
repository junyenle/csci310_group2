/**
 * Enables the login button to be able to call to the 
 * login servlet that will authenticate the username
 * the password
 */

/**
 * TODO:
 * - Prevent the user from going straight to other pages without
 *   logging in
 * - Use encryption aka HTTPS
 * - Allow it to not set any options
 */

// When document is ready, attach all the event handlers
$(document).ready( () => {

    // Gets a reference to the login button
    let loginButton = $('#loginbutton')[0];

    // Attach the login button eventHandler
    $(loginButton).click(tryToLogin);
});

// Attempt to login via the servlet
let tryToLogin = () => {

    // Get the username and password fields
    let usernameField = $('#username')[0];
    let passwordField = $('#password')[0];

    // Get data to use to send to the login servlet
    const username = $(usernameField).val();
    const password = $(passwordField).val();
    console.log(username);
    console.log(password);

    // AJAX call that communicates to the loginServlet
    $.ajax({
        type: "GET",
        url: "loginServlet",
        data: {
            username: username,
            password: password
        },
        success: (response) => {
            console.log(response);
            if (response == "success") {
                window.location.href = "index.jsp";
            }
            else {
                // TODO: Add code to handle invalid logins 
                console.log("There was a login error.");
            }
        }
    });
}