const category = document.getElementById("categoryName")
const form = document.getElementById("categoryEditForm")

form.addEventListener("submit", (event) => {
    const categoryNameIsValid = validateCategoryName(category.value)
    if (!categoryNameIsValid) {
        event.preventDefault()
        category.classList.add("is-invalid")
    }
})



