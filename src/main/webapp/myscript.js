function validateForm() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const mobno = document.getElementById("mobno").value;
    const country = document.getElementById("country").value;
    const agree = document.getElementById("agree").checked;

    const nameError = document.getElementById("name-error");
    const emailError = document.getElementById("email-error");
    const passwordError = document.getElementById("password-error");
    const mobnoError = document.getElementById("mobno-error");
    const countryError = document.getElementById("country-error");
    const agreeError = document.getElementById("agree-error");

    nameError.textContent = "";
    emailError.textContent = "";
    passwordError.textContent = "";
    mobnoError.textContent = "";
    countryError.textContent = "";
    agreeError.textContent = "";

    let isValid = true;

    if (name === "" || /\d/.test(name)) {
        nameError.textContent = "Please enter your name properly.";
        isValid = false;
    }

    if (email === "" || !email.includes("@")) {
        emailError.textContent = "Please enter a valid email.";
        isValid = false;
    }

    if (password === "" || password.length < 8) {
        passwordError.textContent = "Please enter a password of at least 8 characters.";
        isValid = false;
    }

    if (mobno === "" || !/^\d{10}$/.test(mobno)) {
        mobnoError.textContent = "Please enter a valid 10-digit mobile number.";
        isValid = false;
    }

    if (country === "") {
        countryError.textContent = "Please enter your country.";
        isValid = false;
    }

    if (!agree) {
        agreeError.textContent = "Please agree to the terms.";
        isValid = false;
    }

    return isValid;
}

function loginCheck() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const emailError = document.getElementById("email-error");
    const passwordError = document.getElementById("password-error");

    emailError.textContent = "";
    passwordError.textContent = "";

    let isValid = true;

    if (email === "" || !email.includes("@")) {
        emailError.textContent = "Please provide a valid email.";
        isValid = false;
    }

    if (password === "" || password.length < 8) {
        passwordError.textContent = "Please enter a password of at least 8 characters.";
        isValid = false;
    }

    return isValid;
}
