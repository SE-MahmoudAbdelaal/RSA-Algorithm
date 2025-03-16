import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private final BigInteger privateKey;
    private BigInteger publicKey;
    private final BigInteger modulus;

    // Constructor to generate keys
    public RSA(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = new BigInteger(bitLength / 2, 100, random);
        BigInteger q = new BigInteger(bitLength / 2, 100, random);
        modulus = p.multiply(q); // n = p * q
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)); // φ(n) = (p-1)*(q-1)

        // Choose public key (e) such that 1 < e < φ(n) and gcd(e, φ(n)) = 1
        publicKey = new BigInteger("65537"); // Common value for e
        while (phi.gcd(publicKey).compareTo(BigInteger.ONE) != 0) {
            publicKey = publicKey.add(BigInteger.ONE);
        }

        // Compute private key (d) as the modular inverse of e modulo φ(n)
        privateKey = publicKey.modInverse(phi);
    }

    // Encrypt a message
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    // Decrypt a message
    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(privateKey, modulus);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA(1024); // 1024-bit key length

        // Example message
        String originalMessage = "Hello, RSA Secure World!";
        BigInteger message = new BigInteger(originalMessage.getBytes());

        // Encrypt the message
        BigInteger encryptedMessage = rsa.encrypt(message);
        System.out.println("Encrypted Message: " + encryptedMessage);

        // Decrypt the message
        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        String recoveredMessage = new String(decryptedMessage.toByteArray());
        System.out.println("Decrypted Message: " + recoveredMessage);
    }
}