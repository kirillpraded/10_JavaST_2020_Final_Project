const text = document.getElementById("text")

const form = document.getElementById("addAnswerForm")

form.addEventListener("submit", (event) => {
    const textIsValid = validateText(text.value)

    if (!textIsValid) {
        event.preventDefault()
        text.classList.add("is-invalid")
    }
})
