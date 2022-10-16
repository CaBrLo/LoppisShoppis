package com.group5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController
{
    @Autowired
    private ProductRepository repository;

    @GetMapping("/")
    public String frontpage(Model model, HttpSession session)
    {
        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);

        //region används inte i nuläget
        if(session.getAttribute("cart") != null)
            session.setAttribute("cart", new Shoppincart());
        //endregion

        return "frontpage";
    }

    @GetMapping("/product/{id}")
    public String item(Model model, @PathVariable Integer id) {
        Product product = repository.getProduct(id);
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/cart")
    public String showCart()
    {
        return "cart";
    }

    @PostMapping("/")
    public String addProduct()
    {
        return "frontpage";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }


    @GetMapping("/Books/Sci-Fi")
    public String categorySciFi(Model model)
    {
        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);

        return "/Books/Sci-Fi";
    }

    @GetMapping("/Books/AstridLindgrenCollection")
    public String categoryAstridLindgrenCollection(Model model)
    {
        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);

        return "/Books/AstridLindgrenCollection";
    }
    @GetMapping("/Books/AnticGreece")
    public String categoryAnticGreece(Model model)
    {
        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);

        return "/Books/AnticGreece";
    }


    @GetMapping("/AboutUs/AboutUs")
    public String categoryAboutUs(Model model) {
        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/AboutUs/AboutUs";
    }

    @GetMapping("/Clothes/Women")
    public String categoryNewItems(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Clothes/Women";
    }
    @GetMapping("/Clothes/Men")
    public String categorySummerClothes(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Clothes/Men";
    }
    @GetMapping("/Clothes/Children")
    public String categoryDiscountItems(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Clothes/Children";
    }

    @GetMapping("/Clothes/allClothes")
    public String categoryAllClothes(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Clothes/allClothes";
    }
    @GetMapping("/Home/allHome")
    public String categoryHome(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Home/allHome";
    }
    @GetMapping("/Home/homeDecor")
    public String categoryHomeDecor(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Home/homeDecor";
    }
    @GetMapping("/Home/kitchenSupplies")
    public String categoryKitchenSupllies(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Home/kitchenSupplies";
    }

    @GetMapping("/Home/electronics")
    public String categoryElectronics(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Home/electronics";
    }

    @GetMapping("/Hobby/Stamp")
    public String categoryStamp(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Hobby/Stamp";
    }

    @GetMapping("/Hobby/OpenAir")
    public String categoryOpenAir(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Hobby/OpenAir";
    }

    @GetMapping("/Hobby/Movie")
    public String categoryMovie(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Hobby/Movie";
    }

    @GetMapping("/AboutUs/History")
    public String categoryHistory(Model model)
    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/AboutUs/History";
    }
    @GetMapping("/AboutUs/MoreAboutUs")
    public String categoryMoreAboutUs(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/AboutUs/MoreAboutUs";
    }
    @GetMapping("/Vintage/1920")
    public String category1920(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Vintage/1920";
    }
    @GetMapping("/Vintage/1950")
    public String category1950(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Vintage/1950";
    }
    @GetMapping("/Vintage/1970")
    public String category1970(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Vintage/1970";
    }
}
