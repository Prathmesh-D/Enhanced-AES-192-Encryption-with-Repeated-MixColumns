import java.util.HashMap;

public class EntropyCalculator {

    public static double calculateEntropy(String text) {
        HashMap<Character, Integer> frequency = new HashMap<>();
        int length = text.length();

        // Calculate the frequency of each character
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        // Calculate the probability of each character
        double entropy = 0.0;
        for (int count : frequency.values()) {
            double p = (double) count / length;
            entropy -= p * (Math.log(p) / Math.log(2));
        }

        return entropy;
    }

    public static void main(String[] args) {
        // Define the 16-character long ciphertext
        String cipherText = "xZwFS+9kr5RLQesMdYpq+A==";  // Replace with your actual 16-character long ciphertext

        // Calculate and print the entropy
        double entropy = calculateEntropy(cipherText);
        System.out.println("The entropy of the ciphertext is: " + entropy);
    }
}
