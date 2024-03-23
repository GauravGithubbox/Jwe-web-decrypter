package co.aurasphere;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PayloadDecryptor {
    public static void main(String[] args) throws Exception {
        byte[] encryptedData = "eyJhbGciOiJkaXIiLCJlbmMiOiJBMjU2R0NNIn0..KXq-AxZVcsR2akAO.CBBKeeRQuF97j5FwLtTM6MTwEObTc9xE8JW8B_mXbOg9Zs-yTQKeeGmQH-riu1jLhB0XrHHoNuLI0vUigNqkIXs9f9-olba17bhPIEwxt-C4nJiUCRlhYnA6GuJoPGf1cYQU7f8gZEcnI1WpiSRJg_KEXzVFTozNd59Kb1BKchU4Dr_CtejGgH6TH5mXnML6kZviM56WVEwyEXW279pXcFc2QdUMsU9thB_zsE6X5CmFZSDp6gUmc2uDvNTrnvtcUkeijXVT4rY6mG-Fi1NWsUrjldkbpswLreslz47ktLS7miIYmaQyi7L7glmciJmPVESCaolREjopL04tsG_ge7HDlC0zR6nImjs533XnDWKkVZTTZKI.GrufHrf_Jf47NWdXBszw6g".getBytes();


        ByteBuffer buffer = ByteBuffer.wrap(encryptedData);
        byte[] iv = new byte[12]; // 96-bit IV
        buffer.get(iv);

        byte[] payloadWithTag = new byte[buffer.remaining()];
        buffer.get(payloadWithTag);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
        SecretKeySpec secretKeySpec = new SecretKeySpec(getKey(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, gcmParameterSpec);

        byte[] decryptedPayload = cipher.doFinal(payloadWithTag);
        String plaintext = new String(decryptedPayload, StandardCharsets.UTF_8);

        System.out.println("Decrypted Payload: " + plaintext);
    }

    private static byte[] getKey() {
        // Provide the key used for encryption (Base64-encoded)
        return Base64.getDecoder().decode("5ikloyewzLzS34/g0D7WxCxFEMPTAckvFfaNw4/VdNE=");
    }
}
