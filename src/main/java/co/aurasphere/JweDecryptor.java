package co.aurasphere;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class JweDecryptor {
    public static void main(String[] args) throws Exception {
        // Key in Base64 format
        String keyBase64 = "5ikloyewzLzS34/g0D7WxCxFEMPTAckvFfaNw4/VdNE=";
        // Decode the key from Base64
        byte[] keyBytes = Base64.getDecoder().decode(keyBase64);
        SecretKey key = new SecretKeySpec(keyBytes, "AES");

        // JWE token
        String jweToken = "eyJ4NXQiOiJOVEF4Wm1NeE5ETXlaRGczTVRVMVpHTTBNekV6T0RKaFpXSTRORE5sWkRVMU9HRmtOakZp\" +\n" +
                "                \"TVEiLCJraWQiOiJOVEF4Wm1NeE5ETXlaRGczTVRVMVpHTTBNekV6T0RKaFpXSTRORE5sWkRVMU9HRmtOakZpTVEiLCJlbmMiOiJ\" +\n" +
                "                \"BMjU2R0NNIiwiYWxnIjoiUlNBMV81In0.Zwp2xDvYER9lAo43QrYrcaKz-tPLFPYZb2s4RontDDVyvdo-seYl6II2C1Wb4cQhXd\" +\n" +
                "                \"ipcB_Qj093xvLrJyZXWxeavqYhryeuHi2jgcs59MfV1U9hMaKqqjVN1pcZYSrxDzn5leBF5bw7_YKaD_R6cFY8VtpVv5j_U8Woh\" +\n" +
                "                \"tyIjM7_n2CsZ55vY8MUHCAYxzXK9_s75e6Ug8L4MEqpgeoJGQzYCxFrBFgGyDMv1jadLwNl4Y3yLhv4RLtQMU5AM6nODI601UfY\" +\n" +
                "                \"drapObF3mpl_74H_YdRqT28LepGMtkEXbjeRgB-FiFGLvYlrK4wygczLBKrcviVyzyhrIrqz3TYV3g.Lf5lECzAdyAGgP8t.SHB\" +\n" +
                "                \"UZoWkqwW_7u0GElrUqX1tewqRaUMWdGPHxpLRPmpVuc7FwQ27-kdsQ6O1_twhZ7uzjzZaEkatNhMxy9k10733-r4GT1lTGVqidK\" +\n" +
                "                \"iBZq3mRQu7qJpcz7JWUroNFRLxhSoqpLpC8_tAhkohzG-mE42xdEh4tNDy3pBtAG0fe42WrLtWTuyg5lpmOYSppOc2Gb6LcDr4M\" +\n" +
                "                \"mxFNPgoatF0edJSgO-CpFJQTcXn-22lU2g7o22x3RcBx9_KZH0At3g9y9uTuBncExOoBRK_ZweKOl0q76TaLiv5faXINW15xz9h\" +\n" +
                "                \"ILA.RGYIL7FaQqAIMPAiQdkOig";

        // Decode the JWE token
        String[] parts = jweToken.split("\\.");
        String encryptedKey = parts[0];
        String iv = parts[1];
        String cipherText = parts[2];
        String tag = parts[3];

        // Decode each part from Base64
        byte[] encryptedKeyBytes = Base64.getUrlDecoder().decode(encryptedKey);
        byte[] ivBytes = Base64.getUrlDecoder().decode(iv);
        byte[] cipherTextBytes = Base64.getUrlDecoder().decode(cipherText);
        byte[] tagBytes = Base64.getUrlDecoder().decode(tag);

        // Decrypt the JWE token
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, key, gcmParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(cipherTextBytes);

        // Convert decrypted bytes to string
        String decryptedPayload = new String(decryptedBytes);

        System.out.println("Decrypted Payload: " + decryptedPayload);
    }
}



