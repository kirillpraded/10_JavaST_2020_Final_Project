const firstName = document.getElementById("firstName")
const lastName = document.getElementById("lastName")

const form = document.getElementById("personalInfoChangeForm")

form.addEventListener("submit", (event) => {

    const firstNameIsValid = validateName(firstName.value)
    const lastNameIsValid = validateName(lastName.value)

    if (!firstNameIsValid) {
        event.preventDefault()
        firstName.classList.add("is-invalid")
    }
    if (!lastNameIsValid) {
        event.preventDefault()
        lastName.classList.add("is-invalid")
    }

})