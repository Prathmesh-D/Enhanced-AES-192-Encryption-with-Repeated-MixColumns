import java.util.Base64;

public class AvalancheEffect {
    
    public static double calculateAvalancheEffect(byte[] oldCipherText, byte[] newCipherText) {
        if (oldCipherText.length != newCipherText.length) {
            throw new IllegalArgumentException("Ciphertexts must have the same length");
        }

        int totalBits = oldCipherText.length * 8;
        int differentBits = 0;

        for (int i = 0; i < oldCipherText.length; i++) {
            byte oldByte = oldCipherText[i];
            byte newByte = newCipherText[i];
            
            // XOR operation to find differing bits
            byte xorResult = (byte) (oldByte ^ newByte);

            // Counting differing bits
            differentBits += Integer.bitCount(xorResult & 0xFF);
        }

        double avalancheRatio = (double) differentBits / totalBits;
        return avalancheRatio;
    }

    public static void main(String[] args) {
        // Define old and new ciphertexts as strings
        String oldCipherTextStr = "BSXVf9MseS3Fj5llsEYWIA==";
        String newCipherTextStr = "9Es8ry232HgViyZgIm2Oag==";

        // Decode Base64 strings to byte arrays
        byte[] oldCipherText = Base64.getDecoder().decode(oldCipherTextStr);
        byte[] newCipherText = Base64.getDecoder().decode(newCipherTextStr);

        double ratio = calculateAvalancheEffect(oldCipherText, newCipherText);
        System.out.println("Avalanche effect ratio: " + ratio);
    }
}
