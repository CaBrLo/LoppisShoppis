function updateUsername() {
    updateItem("Användarnamnet", "usernameinput", "nameresult", 3)
}

function updatePassword()
{
    updateItem("Lösenordet", "passwordinput", "passwordresult", 5)
}

function updateItem(itemName, inputclass, resultclass, maxamount)
{
    const thisInput = document.querySelector("." + inputclass)
    const thisResult = document.querySelector("." + resultclass)

    thisResult.innerHTML = ""
    thisInput.classList.remove("error")
    thisInput.classList.remove("success")

    if(thisInput.value.length < maxamount) {
        thisResult.innerHTML = `${itemName} måste vara minst ${maxamount} tecken`
        thisInput.classList.add("error")
    } else
    {
        thisInput.classList.add("success")
    }
}