import java.util.Base64;

public class CipherBitFlipper {
    public static void main(String[] args) {
        // Input ciphertext (24 characters)
        String ciphertextBase64 = "VUJTIHN0b3JlcyBkYXRhLg==";

        // Decode Base64 ciphertext to byte array
        byte[] ciphertext = Base64.getDecoder().decode(ciphertextBase64);

        // Choose a random index to flip a bit (within the length of the ciphertext)
        int indexToFlip = (int) (Math.random() * ciphertext.length * 8);

        // Flip the bit at the chosen index
        ciphertext[indexToFlip / 8] ^= (1 << (indexToFlip % 8));

        // Encode the modified ciphertext back to Base64
        String modifiedCiphertextBase64 = Base64.getEncoder().encodeToString(ciphertext);

        // Output the modified ciphertext
        System.out.println("Original Ciphertext: " + ciphertextBase64);
        System.out.println("Modified Ciphertext: " + modifiedCiphertextBase64);
    }
}
