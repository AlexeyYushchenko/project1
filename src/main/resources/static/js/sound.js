//     document.querySelector('.form-group').addEventListener('mouseenter', function() {
//     document.getElementById('hoverSound').play();
// });

// document.querySelectorAll('.form-group').forEach(function(element) {
//     element.addEventListener('mouseenter', function() {
//         document.getElementById('hoverSound').play();
//     });
// });
document.querySelectorAll('ul li a').forEach(function(element) {
    element.addEventListener('mouseenter', function() {
        document.getElementById('hoverSound').play();
    });
});

