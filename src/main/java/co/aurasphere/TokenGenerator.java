package co.aurasphere;



import java.security.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenGenerator {

	public static String generateToken() {
		// Replace this key with your actual key
		String secretKey = "3SxJr3oUe9X30g0Jf+D8dFV8OGBaCgfWOTcNXE4HZT8=";

		// Create a Key object from the secret key
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

		// Build the JWT token
		String jwtToken = Jwts.builder()
				.setSubject("user123")  // Set the subject (can be any string)
				.signWith(key, SignatureAlgorithm.HS256)  // Sign with the key using HMAC SHA-256
				.compact();

		return jwtToken;
	}

	// Example usage
	public static void main(String[] args) {
		String generatedToken = generateToken();
		System.out.println("Generated Token: " + generatedToken);
	}
}
