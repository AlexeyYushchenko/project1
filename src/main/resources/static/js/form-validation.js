document.addEventListener("DOMContentLoaded", function() {
    var form = document.querySelector("form"); // Adjust the selector as needed
    form.addEventListener(function(event) {
        var isValid = true;
        var select = document.getElementById("countryOfDestination");

        // Remove existing error styles
        select.classList.remove("error");

        if (!select.value) {
            select.classList.add("error");
            isValid = false;
        }

        if (!isValid) {
            event.preventDefault(); // Prevent form from submitting
            alert("Please fill all required fields."); // Provide feedback to the user
        }
    }, "submit");
});
