package com.group5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args)
	{
		//testCart();
		testPwHash();

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

	private static void testPwHash()
	{
		try {
			byte[] encoded = MessageDigest.getInstance("SHA-512").digest("123".getBytes());
			byte[] hash = DatatypeConverter.parseHexBinary("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3");

			System.out.println("Two byte array equals:\t\t" + Arrays.equals(hash, encoded));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
