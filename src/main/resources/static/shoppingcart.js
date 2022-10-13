const sum = document.getElementById("cartsum")

let cartData = [{productId: " ", productName: " ", productPrice: 0}]
/*  Testdata
let cartData = [
    {productId: '00', productName: "first", productPrice: 199},
    {productId: '01', productName: "second", productPrice: 199},    
    {productId: '02', productName: "third", productPrice: 199},
    {productId: '03', productName: "fourth", productPrice: 199}
]*/

initCart()
updateSum()
printCart()
function initCart()
{
    if(sessionStorage.getItem("cartD") != null)
    {
        cartData = JSON.parse(sessionStorage.getItem("cartD"))
    } 
}

function updateSum()
{
    let cartsum = cartData.length
    sum.innerText = cartData.length
}

function addProduct(pid,pname,pprice)
{
    let newData = {productId: pid, productName: pname, productPrice: pprice}
    if(sessionStorage.getItem("cartD") === null)
    {
        cartData.push(newData)
        sessionStorage.setItem("cartD",JSON.stringify(newData))
    } else
    {
        cartData = JSON.parse(sessionStorage.getItem("cartD"))
        cartData.push(newData)
        sessionStorage.setItem("cartD",JSON.stringify(cartData))
    }

    if(sum != null)
    {
        updateSum()
    }
    printCart()
}

// För att printa ut information till cart.html
function printCart()
{
    productlist = document.querySelector(".listofproducts")
    let htmltxt = `<tr>
                        <th><h4>Produkt</h4></th>
                        <th><h4>Pris</h4></th>
                   </tr>`
    pricesum = 0

    for(let c of cartData)
    {
        pricesum += c.productPrice

        htmltxt += `<tr>
                        <td>${c.productName}</td>
                        <td>${c.productPrice}</td>
                     </tr>`
    }
    htmltxt += `<tr class="spaceOver">
                <td>Totalpris: </td>
                <td>${pricesum}</td>
              </tr>`

    productlist.innerHTML = htmltxt
}

function emptyCart()
{
    sessionStorage.clear()
    cartData = null;
    printCart();
}
