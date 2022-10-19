updateStuff()

function updateLoginForm() {
    console.log("updateStuff körs!")

    const nameInput = document.querySelector(".usernameinput")
    const pwInput = document.querySelector(".passwordinput")
    const nameResult = document.querySelector(".nameresult")
    const pwResult = document.querySelector(".passwordresult")

    nameInput.classList.remove("error")
    pwInput.classList.remove("error")

    if(nameInput.value.length <= 3) {
        nameResult.value = "Användarnamnet måste vara minst tre tecken!"
        nameInput.classList.add("error")
    } else {
        nameInput.classList.add("success")
    }

    if(pwResult.value.length <= 5) {
        pwResult.value = "Lösenordet måste vara minst fem tecken"
        pwInput.classList.add("error")
    } else
    {
        nameInput.classList.add("success")
    }
}