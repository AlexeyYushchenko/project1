document.addEventListener("DOMContentLoaded", function() {
    var removeButton = document.querySelector("#removeButton");
    removeButton.addEventListener("click", function(event) {
        var confirmMessage = removeButton.getAttribute("data-confirm-message");
        var confirmation = confirm(confirmMessage);
        if (!confirmation) {
            event.preventDefault();
        }
    });
});