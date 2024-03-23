package co.aurasphere;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Decoder {

    public static void main(String[] args) {
        String encodedToken = "eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..tzxXMey-xw2cp7vP.2D0i6cunavM6r6qYtCo-R9sCbYoz_nclsJrADsF5InTDkNqWkT3gvTrQhFTZRVcWCfcM3DHDC5UvTA9PLSOdpRRuAtkKUxjHnEQohFY10d7Xzqw8w4dfahGjEcIpMb5fQyhHh8DiZvY81pzh58R7EakrkdxAnaO7qLYTbCME3YiqlgF6cr2aziolCQAZMgk2aUgRrOUFcSAj797TX-9a83LFzZ9vGzGBnV80yv7J0uJ5HfGWEgt-4NblBSQl5EYMaVawccshN8HpVTYlirma-RwcBHh_UHpTr74zPgWO4QkEMIUdVHNFCDeN5ero53r5c7EYT8in4oJL9Xmrwf7knlJ-5ceU93wMgqK8ucc-t0-S-xBuswOpTi_LprU23LQulg.ClHFlK8cnSC6BXEJ1XryLw";
        String jwtSecret = "mySecretKey123!@#"; // Retrieve this from environment variables

        Map<String, Object> payload = getToken(encodedToken, jwtSecret);
        System.out.println(payload);
    }

    public static Map<String, Object> getToken(String token, String jwtSecret) {
        byte[] encryptionKey = getDerivedEncryptionKey(jwtSecret);
        byte[] decodedToken = Base64.getUrlDecoder().decode(token);

        // Decrypt the token
        byte[] decryptedToken = decryptJWE(decodedToken, encryptionKey);

        // Convert decrypted token to JSON object
        String payloadStr = new String(decryptedToken);
        Map<String, Object> payload = new HashMap<>();
        payload.put("payload", payloadStr); // You might need to parse payloadStr into JSON if needed
        return payload;
    }

    public static byte[] getDerivedEncryptionKey(String jwtSecret) {
        return jwtSecret.getBytes();
    }

    public static byte[] decryptJWE(byte[] token, byte[] encryptionKey) {
        // Your decryption logic goes here
        return null;
    }
}

