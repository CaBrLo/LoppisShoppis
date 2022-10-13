const cartitem = document.querySelector(".cartitem")
const checkout = document.querySelector(".cartcheckout")
const sum = document.getElementById("cartsum")

let cartData = null
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
        cartData = JSON.parse(localStorage.getItem("cartD"))
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
        sessionStorage.setItem("cartD",JSON.stringify(newData))
    } else
    {
        cartData = JSON.parse(localStorage.getItem("cartD"))
        cartData.push(newData)
        localStorage.setItem("cartD",JSON.stringify(cartData))            
    }
    
    /*localStorage.setItem("cartD",JSON.stringify(cartData))    
    cartData = JSON.parse(localStorage.getItem("cartD"))
    let b = {productId: pid, productName: pname, productPrice: pprice}
    cartData.push(b)
    localStorage.setItem("cartD",JSON.stringify(cartData))*/

    updateSum()
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

    if(cartData === null)
    {
        htmltxt += `<tr>
                        <td>Inga produkter i korgen! :(</td>
                        <td></td>
                    </tr>`
    } else
    {
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
    }
    productlist.innerHTML = htmltxt
}

function emptyCart()
{
    sessionStorage.clear()
    cartData = null;
    printCart();
}
