const title = document.getElementById("title")
const text = document.getElementById("text")
const tags = document.getElementById("tags")

const form = document.getElementById("askForm")

form.addEventListener("submit", (event) => {
    const titleIsValid = validateTitle(title.value)
    const textIsValid = validateText(text.value)
    const tagsAreValid = validateTags(tags.value)
    if (!titleIsValid) {
        event.preventDefault()
        title.classList.add("is-invalid")
    }

    if (!tagsAreValid) {
        event.preventDefault()
        tags.classList.add("is-invalid")
    }

    if (!textIsValid) {
        event.preventDefault()
        text.classList.add("is-invalid")
    }
})

