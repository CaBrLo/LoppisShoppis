/*
updateProductSummation()
updatePriceSummation()
updateFullSummation()
updateFullTableSummation()*/

updateValue()
function updateValue()
{
    if(document.getElementsByClassName("productsummation") != null)
    {
        updateProductSummation()
    }

    if(document.getElementsByClassName("pricesummation") != null)
    {
        updatePriceSummation();
    }

    if(document.querySelector(".listofproducts") != null)
    {
        updateFullSummation();
    }

    if(document.querySelector(".fulllistofproducts") != null)
    {
        updateFullTableSummation();
    }
}

/*  Skriv ut hela kundkorgen med produkter och priser.*/
function addProduct(pid,pname,pprice)
{
    // Todo: knapp för att ta bort
    let newData = {productId: pid, productName: pname, productPrice: pprice}
    let cartData = getStorage()

    cartData.push(newData)
    localStorage.setItem("data",JSON.stringify(cartData))

    updateValue()
    /*
    updateBuyModal()
    updateProductSummation()
    updatePriceSummation()
    updateFullSummation()*/

}

/*  Uppdatera summerad köpbekräftelse
    För att använda, lägg till klassen cartsummation */
function updateBuyModal()
{
    let modalItem = document.querySelector(".cartsummation")
    
    if(modalItem != null)
    {
        numOfProducts = sumProducts()
        numOfPrice = sumPrice()
        
        if(modalItem != null)
        {
            modalItem.innerHTML =   `<ul>
                                        <li>Du har ${numOfProducts} produkter i kundkorgen till en totalsumma av ${numOfPrice} </li>
                                    </ul>`
        }
    }
}

/*  Uppdatera summering av priset för alla produkter i varukorgen
    För att använda, lägg till klassen pricesummation */
function updatePriceSummation()
{
    let sumtxt = document.getElementsByClassName("pricesummation")

    for(let s of sumtxt)
    {
        s.innerText = sumPrice()
    }
}

/*  Uppdatera summering av antalet produkter
    För att använda, lägg till klassen productsummation */
function updateProductSummation()
{
    //let sumtxt = document.getElementById("cartsum")
    let sumtxt = document.getElementsByClassName("productsummation")

    for(let s of sumtxt)
    {
        s.innerText = sumProducts()
    }
}

/*  Uppdatera utförlig köpbekräftelse
    För att använda, lägg till klassen listofproducts */
function updateFullSummation()
{
    let alist = document.querySelector(".listofproducts")

    if(alist === null)
    {
        return;
    }

    let htmltxt = ""
    let cartData = new Array()
    cartData = getStorage()

    for(let c of cartData)
    {
        htmltxt += `<p><a href="/product/${c.productId}">${c.productName}</a> <span class="price">${c.productPrice} kr</span></p>`
    }
    alist.innerHTML = htmltxt
}

/*  Uppdatera utförlig köpbekräftelse (med ta bort funktion, anpassad för cart.html)
    För att använda, lägg till klassen listofproducts */
function updateFullTableSummation()
{
    let alist = document.querySelector(".fulllistofproducts")

    if(alist === null)
    {
        return;
    }

    alist.innerHTML = ""
    let cartData = new Array()
    cartData = getStorage()

    for(let c of cartData)
    {
        alist.innerHTML += `<p><img src="/images/trash0.png" height=20px alt="..."><a href="/product/${c.productId}">${c.productName}</a> <span class="price">${c.productPrice} kr</span></p>`
    }
}

// Hjälpfunktion för att summera antalet varor i korgen
function sumProducts()
{
    let cartData = getStorage()

    let productsum = 0
    for(let c of cartData)
    {
        productsum += 1
    }

    return productsum
}

// Hjälpfunktion som summerar priset på varorna i korgen
function sumPrice()
{
    let cartData = getStorage()

    let pricesum = 0
    for(let c of cartData)
    {
        pricesum += c.productPrice
    }

    return pricesum
}

// Töm varukorgen, används på cart.html
function emptyCart()
{
    localStorage.removeItem("data")

    updateProductSummation()
    updatePriceSummation()
    updateFullSummation()
}

// Hjälpfunktion för att initiera/hämta produktlista i session
function getStorage()
{
    let cartD = new Array()

    if(localStorage.getItem("data") === null)
    {
        localStorage.setItem("data",JSON.stringify(cartD))
    } else
    {
        cartD = JSON.parse(localStorage.getItem("data"))
    }
    updateFullSummation()
    updateFullTableSummation()

    return cartD
}