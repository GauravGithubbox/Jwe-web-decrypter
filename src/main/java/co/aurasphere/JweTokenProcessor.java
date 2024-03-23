package co.aurasphere;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Base64;

public class JweTokenProcessor {

    public static void main(String[] args) throws ParseException, JOSEException {

        // Your secret key
        try {
            String secretKey = "bXlTZWNyZXRLZXkxMjMhQCMK";
            String jwtToken =   "eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..tzxXMey-xw2cp7vP.2D0i6cunavM6r6qYtCo-R9sCbYoz_nclsJrADsF5InTDkNqWkT3gvTrQhFTZRVcWCfcM3DHDC5UvTA9PLSOdpRRuAtkKUxjHnEQohFY10d7Xzqw8w4dfahGjEcIpMb5fQyhHh8DiZvY81pzh58R7EakrkdxAnaO7qLYTbCME3YiqlgF6cr2aziolCQAZMgk2aUgRrOUFcSAj797TX-9a83LFzZ9vGzGBnV80yv7J0uJ5HfGWEgt-4NblBSQl5EYMaVawccshN8HpVTYlirma-RwcBHh_UHpTr74zPgWO4QkEMIUdVHNFCDeN5ero53r5c7EYT8in4oJL9Xmrwf7knlJ-5ceU93wMgqK8ucc-t0-S-xBuswOpTi_LprU23LQulg.ClHFlK8cnSC6BXEJ1XryLw";

            // Decode JWT token
            String decodedToken = decodeJWTToken(jwtToken);
            System.out.println("Decoded Token: " + decodedToken);

            // Check if the token is valid
            boolean isValid = isTokenValid(jwtToken, secretKey);
            System.out.println("Is Token Valid? " + isValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decodeJWTToken(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String[] chunks = token.split("\\.");

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        return header + " " + payload;
    }

    public static boolean isTokenValid(String token, String secretKey) throws Exception {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            // Additional checks if needed
            // For example, check expiration, issuer, etc.

            return true;
        } catch (Exception e) {
            throw new Exception("Secret key used for  might be incorrect", e);
        }
    }
}