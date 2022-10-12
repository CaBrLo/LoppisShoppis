package com.group5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args)
	{
		testCart();

		SpringApplication.run(ProjectApplication.class, args);
	}

	private static void testCart()
	{
		Shoppincart cart = new Shoppincart();
		Product testProduct = new Product(0,"Test",20,"testbeskrivning", "book","/images/sushi.png");
		cart.addToCart(testProduct,2);
		cart.addToCart(testProduct,2);
		cart.removeFromCart(testProduct, 1);

		for (Map.Entry<Product, Integer> cartProduct : cart.getCart().entrySet()) {
			System.out.printf("Produkt: %s, antal: %d", cartProduct.getKey().getName(), cartProduct.getValue());
		}
	}
}
