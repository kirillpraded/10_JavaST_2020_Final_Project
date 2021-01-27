const username = document.getElementById("usernameValidation")
const firstName = document.getElementById("firstName")
const lastName = document.getElementById("lastName")
const password = document.getElementById("password")
const passwordConfirmation = document.getElementById("passwordConfirmation")
const form = document.getElementById("registrationForm")

form.addEventListener("submit", (event) => {

    const usernameIsValid = validateUsername(username.value)
    const firstNameIsValid = validateName(firstName.value)
    const lastNameIsValid = validateName(lastName.value)
    const passwordIsValid = validatePassword(password.value)
    const confirmationIsValid = validateConfirmation(password.value, passwordConfirmation.value)
    if (!usernameIsValid) {
        event.preventDefault()
        username.classList.add("is-invalid")
    }
    if (!firstNameIsValid) {
        event.preventDefault()
        firstName.classList.add("is-invalid")
    }
    if (!lastNameIsValid) {
        event.preventDefault()
        lastName.classList.add("is-invalid")
    }
    if (!passwordIsValid) {
        event.preventDefault()
        password.classList.add("is-invalid")
    }
    if (!confirmationIsValid) {
        event.preventDefault()
        passwordConfirmation.classList.add("is-invalid")
    }
})

