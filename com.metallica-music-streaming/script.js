function showSection() {
    document.querySelectorAll(".feature-section")
        .forEach(sec => sec.classList.add("hidden"));

    const val = document.getElementById("optionSelect").value;

    if (val !== "0") {
        document.getElementById("sec" + val).classList.remove("hidden");
    }
}
