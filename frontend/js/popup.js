document.addEventListener("DOMContentLoaded", function() {
    // Form 1 elements
    var modal1 = document.getElementById("formPopup1");
    var btn1 = document.getElementById("openFormButton1");
    var span1 = modal1.getElementsByClassName("close-button")[0];

    btn1.onclick = function() {
        modal1.style.display = "block";
    }

    span1.onclick = function() {
        modal1.style.display = "none";
    }

    // Form 2 elements
    var modal2 = document.getElementById("formPopup2");
    var btn2 = document.getElementById("openFormButton2");
    var span2 = modal2.getElementsByClassName("close-button")[0];

    btn2.onclick = function() {
        modal2.style.display = "block";
    }

    span2.onclick = function() {
        modal2.style.display = "none";
    }

    // Close modal if clicked outside of it
    window.onclick = function(event) {
        if (event.target == modal1) {
            modal1.style.display = "none";
        } else if (event.target == modal2) {
            modal2.style.display = "none";
        }
    }
});
