document.addEventListener('DOMContentLoaded', function () {
    document.querySelector('.dropbtn').addEventListener('click', function() {
        document.getElementById("myDropdown").classList.toggle("show");
    });

    window.onclick = function(event) {
        if (!event.target.matches('.dropbtn') && !event.target.matches('.dropdown-content') && !event.target.closest('.dropdown-content')) {
            let dropdowns = document.getElementsByClassName("dropdown-content");
            for (let i = 0; i < dropdowns.length; i++) {
                let openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }
});
