import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSACrypto {

    // Generate RSA key pair
    public static KeyPair generateRSAKeyPair(int bitLength) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(bitLength); // Use 2048 bits for better security
        return keyPairGenerator.generateKeyPair();
    }

    // Encrypt using RSA public key
    public static String encryptRSA(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt using RSA private key
    public static String decryptRSA(String encryptedText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        // Generate RSA Key Pair
        KeyPair keyPair = generateRSAKeyPair(1024);
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String message = "Hello, RSA Secure World!";

        // Encrypt message
        String encrypted = encryptRSA(message, publicKey);

        // Decrypt message
        String decrypted = decryptRSA(encrypted, privateKey);

        System.out.println("Public Key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("Private Key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

    public static PublicKey convertStringToPublicKey(String keyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Remove any headers, footers, and newlines
        keyString = keyString.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");

        // Decode the Base64-encoded string
        byte[] keyBytes = Base64.getDecoder().decode(keyString);

        // Generate the public key
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // Change "RSA" if using a different algorithm

        return keyFactory.generatePublic(spec);
    }
}
