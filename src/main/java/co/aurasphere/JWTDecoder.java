package co.aurasphere;

import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class JWTDecoder {

    public static void main(String[] args) throws Exception {
        String encodedToken = "eyJraWQiOiJEd01LZFdNbWo3UFdpbnZvcWZReVhWenlaNlEiLCJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBMV81In0.Y6U9uBtH0dSCJNhq8bzhPgq36Cglsd8t0NHA1CLlu5p2JOPuvlBVsNBCwzXnvqbvbgoPsmLtyni_0wKZxvbnvb4LSWN1hD1bQtK_gPqCYHoZ5IRyDd8BBV6HnwLte7blTUdYZkAo-mli7KdMwFYwDvMPJXq65ztZMMK1-j6hf5bZxJwcvMgzbcu7oN2WR1dOgJIntHI3K-1KRVaZe-2K0v4yFNpHuK9MQ10v7bx8BFicpWlLdqQjynObk3rD6Aqx-kgPUNVDk8EWmFvDcluyf_ZSTuWmPTyLBXFNEOFhZiXO0_Hx3ilX1qT7ep99ZXH3ZrMwMZJxhO4vi344MU9p2Q.nKrqAu8YwXk173ba.V0YmaZRJYUb6otvkCPJfrYjLmGgi6MCGcdrMaJ4yJsO188SjY3DUO462SKYRFISM4WGYIM1R5MnIieCuRecUbUMrEQx66oYJNOq8ddJPqapoBIMcXS-8ZuksfT0h4Sri9V6koJGOgLszA2JEHE98Ev9sVnqyRxve5bhICVlIZVHVGsBIGtmF5jkmS8z0TyRqoG2MYOqIBxQEJJQQlnKDPP_-JAvjdGGMDT_mnqUx4Slo5M2SBmUWBnBUirPbm2aXW9EkFMhlpiNoyF_uwqdo-cy1KG3rVWDv2d4oCEPOCHBnXdu3F9I2lxJB8Kiz3mhtCtdv73d3O_1l1gf5raGTyedAixhRKWo_bBCONky7U6xr_VtZUDHDvoeQRosrmqaZjmV9IOc8Uv37XV_Y3oN7Il6cXtGdlWkdmB-9tVzn3WME8XprTcyu1oc-B3ePl9Q1NFvJNkBv5bxC9plvdFP5nrl3HAprGfVhj_tStbUqBA0wtFbHALqnzdhBkBaLNas.hj137sFHpvEdsaaSVXbJnQ";

        // Provide your decryption secret and salt
        String decryptionKey = "5ikloyewzLzS34/g0D7WxCxFEMPTAckvFfaNw4/VdNE=";
        String salt = "authjs.session-token";

        // Decode the token
        JWTClaimsSet claims = decodeToken(encodedToken, decryptionKey, salt);

        // Print the claims
        System.out.println(claims.toJSONObject());
    }

    public static JWTClaimsSet decodeToken(String encodedToken, String decryptionKey, String salt) throws Exception {
        // Derive key using decryption key and salt
        byte[] secretKey = deriveKey(decryptionKey, salt);

        // Parse the JWE token
        EncryptedJWT jwt = EncryptedJWT.parse(encodedToken);

        // Create a JWE decrypter
        DirectDecrypter decrypter = new DirectDecrypter(secretKey);

        // Decrypt the token
        jwt.decrypt(decrypter);

        // Retrieve the JWT claims
        return jwt.getJWTClaimsSet();
    }

    public static byte[] deriveKey(String decryptionKey, String salt) throws NoSuchAlgorithmException {
        // Concatenate decryption key and salt
        String keyWithSalt = decryptionKey + salt;

        // Use SHA-256 for hashing
        MessageDigest digest = MessageDigest.getInstance("RSA1_5");
        byte[] hash = digest.digest(keyWithSalt.getBytes());

        // Base64 encode the hash
        return Base64.getEncoder().encode(hash);
    }
}