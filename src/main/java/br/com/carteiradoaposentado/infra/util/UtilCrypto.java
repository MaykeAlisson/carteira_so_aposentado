package br.com.carteiradoaposentado.infra.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

public class UtilCrypto {

    private static final String IV = "AAAAAAAAAAAAAAAA";
    private static final String chaveencriptacao = "15fdsr124freyfg5";

    public static String encriptar(final String texto) {
        try {
            Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
            SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes(StandardCharsets.UTF_8), "AES");
            encripta.init(1, key, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
            byte[] bytes = encripta.doFinal(texto.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchProviderException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException var4) {
            throw new RuntimeException(var4);
        }
    }

    public static String decriptar(byte[] textoencriptado) {
        try {
            Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
            SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes(StandardCharsets.UTF_8), "AES");
            decripta.init(2, key, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
            return new String(decripta.doFinal(textoencriptado), StandardCharsets.UTF_8);
        } catch (NoSuchProviderException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException var3) {
            throw new RuntimeException(var3);
        }
    }
}
