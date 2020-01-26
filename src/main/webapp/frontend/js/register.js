function checkPassword() {
    var password = document.getElementById('password').value;
    var passwordLengthError = document.getElementById('passwordLengthError');
    var passwordContainUpperCaseLetterError = document.getElementById('passwordContainUpperCaseLetterError');
    var passwordContainNumberError = document.getElementById('passwordContainNumberError');
    var confirmPassword = document.getElementById('confirmPassword');
    var cond1 = false;
    var cond2 = false;
    var cond3 = false;
    console.log(password);
    if(password.length < 6) {
        passwordLengthError.style.color = "red";
        cond1 = false;
    } else {
        passwordLengthError.style.color = "green";
        cond1 = true;
    }
    if(password.toString().search(/[A-ZА-Я]/) === -1) {
        passwordContainUpperCaseLetterError.style.color = "red";
        cond2 = false;
    } else {
        passwordContainUpperCaseLetterError.style.color = "green";
        cond2 = true;
    }
    if(password.toString().search(/\d/) === -1) {
        passwordContainNumberError.style.color = "red";
        cond3 = false;
    } else {
        passwordContainNumberError.style.color = "green";
        cond3 = true;
    }
    confirmPassword.disabled = !(cond1 && cond2 && cond3);
}

function checkConfirmPassword() {
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirmPassword').value;
    var registerButton = document.getElementById('registerButton');
    registerButton.disabled = !(password === confirmPassword);
}