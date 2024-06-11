document.addEventListener("DOMContentLoaded", () => {
    const commentsLinks = document.querySelectorAll(".comments-link");

    commentsLinks.forEach(link => {
        link.addEventListener("click", (event) => {
            event.preventDefault();
            const commentsSection = link.nextElementSibling;

            if (commentsSection) {
                if (commentsSection.style.maxHeight) {
                    commentsSection.style.maxHeight = null;
                } else {
                    commentsSection.style.maxHeight = commentsSection.scrollHeight + "px";
                }
            }
        });
    });
});
