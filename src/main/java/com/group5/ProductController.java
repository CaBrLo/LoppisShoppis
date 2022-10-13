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
}
