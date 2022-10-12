const cartitem = document.querySelector(".cartitem")
const checkout = document.querySelector(".cartcheckout")
const sum = document.getElementById("cartsum")

const cartData = [
    {productId: '00', productName: "test", productPrice: 199},
    {productId: '00', productName: "test2", productPrice: 199}
]

updateSum();
function saveToCart(id, name, price)
{
    let b = {productId: id, productName: name, productPrice: price}
    cartData.push(b)

    updateSum();
}

function printOutCart()
{
    let html = ""    
    for(let c of cartData)
    {
        const prodId = c.productId
        const prodName = c.productName
        const prodPrice = c.productPrice

        html += `<div value="${prodId}">Produkt: ${prodName}, Pris: ${prodPrice}</div>`
    }
    checkout.innerHTML = html
}

function updateSum()
{
    let numberOfProcuts = cartData.length
    sum.innerText = `${numberOfProcuts}`
}