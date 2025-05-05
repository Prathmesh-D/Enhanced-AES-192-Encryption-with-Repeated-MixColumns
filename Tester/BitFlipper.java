public class BitFlipper {

    public static void main(String[] args) {
        String plaintext = "Cloud save file.";
        int bitPosition = 50
        ; // You can change this to flip a different bit

        String modifiedPlaintext = flipSingleBit(plaintext, bitPosition);

        System.out.println("Original Plaintext: " + plaintext);
        System.out.println("Modified Plaintext: " + modifiedPlaintext);
    }

    /**
     * Flips a single bit in the given plaintext at the specified bit position.
     *
     * @param plaintext  The original plaintext string.
     * @param bitPosition The bit position to flip (0-based index).
     * @return The modified plaintext string with the specified bit flipped.
     */
    public static String flipSingleBit(String plaintext, int bitPosition) {
        byte[] bytes = plaintext.getBytes();

        // Calculate the byte index and the bit index within that byte
        int byteIndex = bitPosition / 8;
        int bitIndex = bitPosition % 8;

        // Flip the specified bit using XOR
        bytes[byteIndex] ^= (1 << bitIndex);

        // Return the modified plaintext
        return new String(bytes);
    }
}
