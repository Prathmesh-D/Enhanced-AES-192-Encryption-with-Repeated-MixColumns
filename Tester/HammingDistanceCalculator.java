public class HammingDistanceCalculator {

    // Method to calculate the Hamming distance between two byte arrays
    public static int calculateHammingDistance(byte[] array1, byte[] array2) {
        if (array1.length != array2.length) {
            throw new IllegalArgumentException("Byte arrays must be of the same length");
        }

        int differingBits = 0;
        for (int i = 0; i < array1.length; i++) {
            differingBits += Integer.bitCount(array1[i] ^ array2[i]);
        }
        return differingBits;
    }

    public static void main(String[] args) {
        // Example ciphertexts for comparison (ensure these are the same length)
        byte[] ciphertext1 = "2vjYc/Eh5dys8ZYgLCmV/g==".getBytes();
        byte[] ciphertext2 = "TnqoNNItb3ViENEKXeaTlA==".getBytes();

        // Calculate and print the Hamming distance
        int hammingDistance = calculateHammingDistance(ciphertext1, ciphertext2);
        System.out.println("Hamming Distance: " + hammingDistance);
    }
}
