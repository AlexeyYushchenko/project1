document.addEventListener('DOMContentLoaded', function() {
    // Select all links you want to apply the transition effect to
    document.querySelectorAll('a').forEach(function(link) {
        link.addEventListener('click', function(event) {
            // Filter out links that shouldn't trigger the transition
            // For example, links to external sites, or having certain attributes like 'target="_blank"'
            if (this.host !== window.location.host || this.hasAttribute('target')) {
                return;
            }

            event.preventDefault(); // Prevent default navigation
            const targetUrl = this.getAttribute('href'); // Get the target URL

            // Play the swishing sound
            const swishinSound = document.getElementById('swishin');
            swishinSound.play();

            // Apply your animation, like fading out the current content
            document.body.classList.add('fade-out');

            setTimeout(function() {
                window.location.href = targetUrl; // Navigate to the target URL after the animation
            }, 500); // Adjust timeout duration to match your animation
        });
    });
});