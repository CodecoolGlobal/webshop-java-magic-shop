function categoryDropdown() {
    hideDropdowns();
    document.getElementById("categoryDropdown").classList.toggle("show");
}

function supplierDropdown() {
    hideDropdowns();
    document.getElementById("supplierDropdown").classList.toggle("show");
}

function hideDropdowns() {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
        }
    }

}

window.onclick = function (event) {
    if (!event.target.matches('.dropbtn')) {
        hideDropdowns();
    }
};