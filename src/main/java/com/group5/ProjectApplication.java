package com.group5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Map;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args)
	{
		//testCart();
		testPwHash2("123");

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

	private static void testPwHash2(String password)
	{
		byte[] hashedPw = null;

		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			//md.update(salt);
			hashedPw = md.digest(password.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < hashedPw.length; i++) {
			sb.append(Integer.toString((hashedPw[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		String generatedPassword = sb.toString();

		System.out.println(generatedPassword);
	}
}
