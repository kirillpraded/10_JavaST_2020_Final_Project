const category = document.getElementById("nameCategory")
const form = document.getElementById("categoryAddForm")

form.addEventListener("submit", (event) => {
    const categoryNameIsValid = validateCategoryName(category.value)
    if (!categoryNameIsValid) {
        event.preventDefault()
        category.classList.add("is-invalid")
    }
})



