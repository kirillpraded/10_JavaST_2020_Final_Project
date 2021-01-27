const password = document.getElementById("newPassword")
const passwordConfirmation = document.getElementById("passwordConfirmation")
const form = document.getElementById("changePasswordForm")

form.addEventListener("submit", (event) => {
    const passwordIsValid = validatePassword(password.value)
    const confirmationIsValid = validateConfirmation(password.value, passwordConfirmation.value)

    if (!passwordIsValid) {
        event.preventDefault()
        password.classList.add("is-invalid")
    }
    if (!confirmationIsValid) {
        event.preventDefault()
        passwordConfirmation.classList.add("is-invalid")
    }
})
