package co.aurasphere;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Base64;

public class JweTokenDecoder {




        public static void main(String[] args) throws ParseException, JOSEException {
            // Your Base64-encoded decryption key
            String decryptionKeyString = "5ikloyewzLzS34/g0D7WxCxFEMPTAckvFfaNw4/VdNE=";

            // Decode the key
            byte[] decryptionKeyBytes = Base64.getDecoder().decode(decryptionKeyString);
            SecretKey decryptionKey = new SecretKeySpec(decryptionKeyBytes, "RSA1_5");

            // Your JWE token
            String jweToken = "eyJhbGciOiJIUzUxMiJ9.eyJuYW1lIjoiUHVzaHBlbmRyYSBTaGFybWEiLCJlbWFpbCI6InB1c2hwZW5kcmEuc2hhcm1hQGxvYmxhdy5jYSIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NLNXNHUEZzZFlXWGpNZ2N2b3pEWmcwWF93c0tURnVHOFp1WjRZLWVjQmw9czk2LWMiLCJzdWIiOiIxMDI5OTQyMTkwNTA1Mjg4NjQ4NTEiLCJpYXQiOjE3MTEwMzE2MTQsImV4cCI6MTcxMzYyMzYxNH0.zRsf6nQrncJO2zr95kHWXEtr4OK-BzKp8O6RPbOi2Ic2sZ_l7SrWPwpYYQkznxxmSMkXb5wbbALJP-O9yurLhg";

            // Parse and decrypt the JWE token
            EncryptedJWT encryptedJWT = EncryptedJWT.parse(jweToken);
            DirectDecrypter decrypter = new DirectDecrypter(decryptionKey);
            encryptedJWT.decrypt(decrypter);

            // Get the JWT claims
            JWTClaimsSet jwtClaimsSet = encryptedJWT.getJWTClaimsSet();
            System.out.println("Decoded Token: " + jwtClaimsSet.toJSONObject());
        }

}

