const title = document.getElementById("title")
const text = document.getElementById("text")

const form = document.getElementById("questionEditForm")

form.addEventListener("submit", (event) => {
    const titleIsValid = validateTitle(title.value)
    const textIsValid = validateText(text.value)
    if (!titleIsValid) {
        event.preventDefault()
        title.classList.add("is-invalid")
    }

    if (!textIsValid) {
        event.preventDefault()
        text.classList.add("is-invalid")
    }
})
