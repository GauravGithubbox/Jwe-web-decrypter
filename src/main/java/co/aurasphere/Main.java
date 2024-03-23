package co.aurasphere;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectDecrypter;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.HKDFBytesGenerator;
import org.bouncycastle.crypto.params.HKDFParameters;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.text.ParseException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Main {

    public static void main(String[] args) throws GeneralSecurityException, IOException, JOSEException, ParseException {

        // Add Bouncy Castle provider for cryptographic operations
        Security.addProvider(new BouncyCastleProvider());

        String rawJwe = "eyJraWQiOiJEd01LZFdNbWo3UFdpbnZvcWZReVhWenlaNlEiLCJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBMV81In0.Y6U9uBtH0dSCJNhq8bzhPgq36Cglsd8t0NHA1CLlu5p2JOPuvlBVsNBCwzXnvqbvbgoPsmLtyni_0wKZxvbnvb4LSWN1hD1bQtK_gPqCYHoZ5IRyDd8BBV6HnwLte7blTUdYZkAo-mli7KdMwFYwDvMPJXq65ztZMMK1-j6hf5bZxJwcvMgzbcu7oN2WR1dOgJIntHI3K-1KRVaZe-2K0v4yFNpHuK9MQ10v7bx8BFicpWlLdqQjynObk3rD6Aqx-kgPUNVDk8EWmFvDcluyf_ZSTuWmPTyLBXFNEOFhZiXO0_Hx3ilX1qT7ep99ZXH3ZrMwMZJxhO4vi344MU9p2Q.nKrqAu8YwXk173ba.V0YmaZRJYUb6otvkCPJfrYjLmGgi6MCGcdrMaJ4yJsO188SjY3DUO462SKYRFISM4WGYIM1R5MnIieCuRecUbUMrEQx66oYJNOq8ddJPqapoBIMcXS-8ZuksfT0h4Sri9V6koJGOgLszA2JEHE98Ev9sVnqyRxve5bhICVlIZVHVGsBIGtmF5jkmS8z0TyRqoG2MYOqIBxQEJJQQlnKDPP_-JAvjdGGMDT_mnqUx4Slo5M2SBmUWBnBUirPbm2aXW9EkFMhlpiNoyF_uwqdo-cy1KG3rVWDv2d4oCEPOCHBnXdu3F9I2lxJB8Kiz3mhtCtdv73d3O_1l1gf5raGTyedAixhRKWo_bBCONky7U6xr_VtZUDHDvoeQRosrmqaZjmV9IOc8Uv37XV_Y3oN7Il6cXtGdlWkdmB-9tVzn3WME8XprTcyu1oc-B3ePl9Q1NFvJNkBv5bxC9plvdFP5nrl3HAprGfVhj_tStbUqBA0wtFbHALqnzdhBkBaLNas.hj137sFHpvEdsaaSVXbJnQ";
        String nextAuthSecret = "5ikloyewzLzS34/g0D7WxCxFEMPTAckvFfaNw4/VdNE=";
        String info = "NextAuth.js Generated Encryption Key";

        // Step 1: Generate the decryption key with an HKDF library
        Digest hash = new SHA256Digest();
        HKDFBytesGenerator kdf = new HKDFBytesGenerator(hash);
        HKDFParameters params = new HKDFParameters(nextAuthSecret.getBytes(), null, info.getBytes());
        kdf.init(params);
        byte[] key = new byte[32];
        kdf.generateBytes(key, 0, key.length);

        // Step 2: Decrypt with a JWE library (Nimbus JOSE + JWT)
        JWEObject jweObject = JWEObject.parse(rawJwe);
        DirectDecrypter decrypter = new DirectDecrypter(key);
        jweObject.decrypt(decrypter);

        // Extract the decrypted payload
        String decryptedPayload = jweObject.getPayload().toString();
        System.out.println(decryptedPayload);
    }
}

