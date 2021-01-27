function validateUsername(username) {
    return !(/\s/.test(username)) && username.length >= 3 && username.length <= 20;
}

function validateName(name) {
    return name.length >= 1 && name.length <= 50;
}

function validatePassword(password) {
    return !(/\s/.test(password)) && password.length >= 6 && password.length <= 20;
}

function validateConfirmation(password, confirmation) {
    return password === confirmation;
}

function validateCategoryName(name) {
    return name.length > 0 && name.length <= 50;
}

function validateTitle(title) {
    return title.length >= 3 && title.length <= 50;
}

function validateText(text) {
    return text.length >= 10 && text.length <= 500;
}

function validateTags(tags) {
    const array = tags.split(",")
    array.map(tag => {
        if (tag.length < 2 || tag.length > 32) {
            return false;
        }
    })
    return true;
}


