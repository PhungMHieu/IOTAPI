document.getElementById("criteria").addEventListener("change", toggleTimeInput);
function toggleTimeInput() {
    var criteria = document.getElementById("criteria").value;
    var timeInputs = document.getElementById("time-inputs");
    var singleInput = document.getElementById("single-input");

    if (criteria === "time") {
        timeInputs.style.display = "block";
        singleInput.style.display = "none";
    } else {
        timeInputs.style.display = "none";
        singleInput.style.display = "block";
    }
}