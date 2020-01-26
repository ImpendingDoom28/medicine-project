function checkForLength() {
    var text = document.getElementById("textMessage");
    var textValue = text.value;
    var charsNumbers = textValue.length;
    var chars = document.getElementById("chars");
    var submiter = document.getElementById("submiter");
    if(charsNumbers >= 75) {
        chars.style.color = "red";
        submiter.disabled = true;
    } else {
        chars.style.color = "green";
        submiter.disabled = false;
    }
    chars.innerHTML = charsNumbers + "/75";
}