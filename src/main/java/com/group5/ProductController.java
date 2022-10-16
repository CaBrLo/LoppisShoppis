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
    public String checkout(HttpSession session) {
        return "checkout";
    }

    @GetMapping("/Books/fiction")
    public String categoryFiction(Model model)
    {
        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);

        return "/Books/fiction";
    }

    @GetMapping("/Books/childrensBooks")
    public String categoryChildrensBooks(Model model)
    {
        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);

        return "/Books/childrensBooks";
    }
    @GetMapping("/Books/non-fiction")
    public String categoryNonFiction(Model model)
    {
        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);

        return "/Books/non-fiction";
    }

    @GetMapping ("/Books/allBooks")
    public String categoryAllBooks(Model model) {
        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);

        return "/Books/allBooks";
    }

    @GetMapping("/AboutUs/AboutUs")
    public String categoryAboutUs(Model model) {
        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/AboutUs/AboutUs";
    }

    @GetMapping("/Clothes/NewItems")
    public String categoryNewItems(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Clothes/NewItems";
    }
    @GetMapping("/Clothes/SummerClothes")
    public String categorySummerClothes(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Clothes/SummerClothes";
    }
    @GetMapping("/Clothes/DiscountItems")
    public String categoryDiscountItems(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Clothes/DiscountItems";
    }
    @GetMapping("/Home&style/Furniture")
    public String categoryFurniture(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Home&style/Furniture";
    }
    @GetMapping("/Home&style/Aquarium")
    public String categoryAquarium(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Home&style/Aquarium";
    }
    @GetMapping("/Home&style/Paintings")
    public String categoryPaintings(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Home&style/Paintings";
    }

    @GetMapping("/Hobby/sport")
    public String categorySport(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Hobby/sport";
    }

    @GetMapping("/Hobby/toys")
    public String categoryToys(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Hobby/toys";
    }

    @GetMapping("/Hobby/hobbiesAndGames")
    public String categoryHobbiesAndGames(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Hobby/hobbiesAndGames";
    }

    @GetMapping("/Hobby/moviesAndMusic")
    public String categoryMoviesAndMusic(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Hobby/moviesAndMusic";
    }

    @GetMapping("/Hobby/allHobbies")
    public String categoryAllHobbies(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/Hobby/allHobbies";
    }

    @GetMapping("/AboutUs/contact")
    public String categoryContact(Model model)
    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/AboutUs/contact";
    }
    @GetMapping("/AboutUs/faq")
    public String categoryFaq(Model model)

    {        List<Product> products = repository.getProducts();
        model.addAttribute("products", products);
        return "/AboutUs/faq";
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
