let sum = document.getElementById("cartsum")
let productlist = document.querySelector(".listofproducts")

/*  Testdata */
let cartData = [
    {productId: '00', productName: "Tale of Sushi", productPrice: 199},
    {productId: '03', productName: "Illiaden", productPrice: 199}
]

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
    sum = document.getElementById("cartsum")

    if(cartData === null || cartData.length === undefined)
    {
        sum.innerText = "0"
    } else
    {
        sum.innerText = cartData.length
    }
}

function addProduct(pid,pname,pprice)
{
    /*
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
    }*/
    let newData = {productId: pid, productName: pname, productPrice: pprice}

    cartData.push(newData)
    cartData = JSON.parse(sessionStorage.getItem("cartD"))
    sessionStorage.setItem("cartD",JSON.stringify(cartData))

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
