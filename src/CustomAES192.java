import java.util.Arrays;
import java.util.Base64;

public class CustomAES192 {
    private static final int BLOCK_SIZE = 16; // AES block size in bytes
    private static final int KEY_SIZE = 24; // AES key size in bytes (192 bits)
    private static final int NUM_ROUNDS = 12; // Number of rounds for AES-192

    private byte[] key;
    private byte[][] roundKeys;

    private static final byte[] S_BOX = new byte[]{
        (byte) 0x63, (byte) 0x7c, (byte) 0x77, (byte) 0x7b, (byte) 0xf2, (byte) 0x6b, (byte) 0x6f, (byte) 0xc5,
        (byte) 0x30, (byte) 0x01, (byte) 0x67, (byte) 0x2b, (byte) 0xfe, (byte) 0xd7, (byte) 0xab, (byte) 0x76,
        (byte) 0xca, (byte) 0x82, (byte) 0xc9, (byte) 0x7d, (byte) 0xfa, (byte) 0x59, (byte) 0x47, (byte) 0xf0,
        (byte) 0xad, (byte) 0xd4, (byte) 0xa2, (byte) 0xaf, (byte) 0x9c, (byte) 0xa4, (byte) 0x72, (byte) 0xc0,
        (byte) 0xb7, (byte) 0xfd, (byte) 0x93, (byte) 0x26, (byte) 0x36, (byte) 0x3f, (byte) 0xf7, (byte) 0xcc,
        (byte) 0x34, (byte) 0xa5, (byte) 0xe5, (byte) 0xf1, (byte) 0x71, (byte) 0xd8, (byte) 0x31, (byte) 0x15,
        (byte) 0x04, (byte) 0xc7, (byte) 0x23, (byte) 0xc3, (byte) 0x18, (byte) 0x96, (byte) 0x05, (byte) 0x9a,
        (byte) 0x07, (byte) 0x12, (byte) 0x80, (byte) 0xe2, (byte) 0xeb, (byte) 0x27, (byte) 0xb2, (byte) 0x75,
        (byte) 0x09, (byte) 0x83, (byte) 0x2c, (byte) 0x1a, (byte) 0x1b, (byte) 0x6e, (byte) 0x5a, (byte) 0xa0,
        (byte) 0x52, (byte) 0x3b, (byte) 0xd6, (byte) 0xb3, (byte) 0x29, (byte) 0xe3, (byte) 0x2f, (byte) 0x84,
        (byte) 0x53, (byte) 0xd1, (byte) 0x00, (byte) 0xed, (byte) 0x20, (byte) 0xfc, (byte) 0xb1, (byte) 0x5b,
        (byte) 0x6a, (byte) 0xcb, (byte) 0xbe, (byte) 0x39, (byte) 0x4a, (byte) 0x4c, (byte) 0x58, (byte) 0xcf,
        (byte) 0xd0, (byte) 0xef, (byte) 0xaa, (byte) 0xfb, (byte) 0x43, (byte) 0x4d, (byte) 0x33, (byte) 0x85,
        (byte) 0x45, (byte) 0xf9, (byte) 0x02, (byte) 0x7f, (byte) 0x50, (byte) 0x3c, (byte) 0x9f, (byte) 0xa8,
        (byte) 0x51, (byte) 0xa3, (byte) 0x40, (byte) 0x8f, (byte) 0x92, (byte) 0x9d, (byte) 0x38, (byte) 0xf5,
        (byte) 0xbc, (byte) 0xb6, (byte) 0xda, (byte) 0x21, (byte) 0x10, (byte) 0xff, (byte) 0xf3, (byte) 0xd2,
        (byte) 0xcd, (byte) 0x0c, (byte) 0x13, (byte) 0xec, (byte) 0x5f, (byte) 0x97, (byte) 0x44, (byte) 0x17,
        (byte) 0xc4, (byte) 0xa7, (byte) 0x7e, (byte) 0x3d, (byte) 0x64, (byte) 0x5d, (byte) 0x19, (byte) 0x73,
        (byte) 0x60, (byte) 0x81, (byte) 0x4f, (byte) 0xdc, (byte) 0x22, (byte) 0x2a, (byte) 0x90, (byte) 0x88,
        (byte) 0x46, (byte) 0xee, (byte) 0xb8, (byte) 0x14, (byte) 0xde, (byte) 0x5e, (byte) 0x0b, (byte) 0xdb,
        (byte) 0xe0, (byte) 0x32, (byte) 0x3a, (byte) 0x0a, (byte) 0x49, (byte) 0x06, (byte) 0x24, (byte) 0x5c,
        (byte) 0xc2, (byte) 0xd3, (byte) 0xac, (byte) 0x62, (byte) 0x91, (byte) 0x95, (byte) 0xe4, (byte) 0x79,
        (byte) 0xe7, (byte) 0xc8, (byte) 0x37, (byte) 0x6d, (byte) 0x8d, (byte) 0xd5, (byte) 0x4e, (byte) 0xa9,
        (byte) 0x6c, (byte) 0x56, (byte) 0xf4, (byte) 0xea, (byte) 0x65, (byte) 0x7a, (byte) 0xae, (byte) 0x08,
        (byte) 0xba, (byte) 0x78, (byte) 0x25, (byte) 0x2e, (byte) 0x1c, (byte) 0xa6, (byte) 0xb4, (byte) 0xc6,
        (byte) 0xe8, (byte) 0xdd, (byte) 0x74, (byte) 0x1f, (byte) 0x4b, (byte) 0xbd, (byte) 0x8b, (byte) 0x8a,
        (byte) 0x70, (byte) 0x3e, (byte) 0xb5, (byte) 0x66, (byte) 0x48, (byte) 0x03, (byte) 0xf6, (byte) 0x0e,
        (byte) 0x61, (byte) 0x35, (byte) 0x57, (byte) 0xb9, (byte) 0x86, (byte) 0xc1, (byte) 0x1d, (byte) 0x9e,
        (byte) 0xe1, (byte) 0xf8, (byte) 0x98, (byte) 0x11, (byte) 0x69, (byte) 0xd9, (byte) 0x8e, (byte) 0x94,
        (byte) 0x9b, (byte) 0x1e, (byte) 0x87, (byte) 0xe9, (byte) 0xce, (byte) 0x55, (byte) 0x28, (byte) 0xdf,
        (byte) 0x8c, (byte) 0xa1, (byte) 0x89, (byte) 0x0d, (byte) 0xbf, (byte) 0xe6, (byte) 0x42, (byte) 0x68,
        (byte) 0x41, (byte) 0x99, (byte) 0x2d, (byte) 0x0f, (byte) 0xb0, (byte) 0x54, (byte) 0xbb, (byte) 0x16
    };

    private static final byte[] RCON = new byte[]{
        (byte) 0x8d, (byte) 0x01, (byte) 0x02, (byte) 0x04, (byte) 0x08, (byte) 0x10, (byte) 0x20, (byte) 0x40,
        (byte) 0x80, (byte) 0x1b, (byte) 0x36, (byte) 0x6c, (byte) 0xd8, (byte) 0xab, (byte) 0x4d, (byte) 0x9a,
        (byte) 0x2f, (byte) 0x5e, (byte) 0xbc, (byte) 0x63, (byte) 0xc6, (byte) 0x97, (byte) 0x35, (byte) 0x6a,
        (byte) 0xd4, (byte) 0xb3, (byte) 0x7d, (byte) 0xfa, (byte) 0xef, (byte) 0xc5, (byte) 0x91, (byte) 0x39
    };

    public CustomAES192(byte[] key) {
        if (key.length != KEY_SIZE) {
            throw new IllegalArgumentException("Key length must be 192 bits");
        }
        this.key = key;
        this.roundKeys = keyExpansion(key);
    }

    private byte[][] keyExpansion(byte[] key) {
        byte[][] roundKeys = new byte[NUM_ROUNDS + 1][BLOCK_SIZE];
        byte[] temp = new byte[4];
        int i = 0;

        while (i < KEY_SIZE / 4) {
            roundKeys[i / 4][i % 4 * 4] = key[i * 4];
            roundKeys[i / 4][i % 4 * 4 + 1] = key[i * 4 + 1];
            roundKeys[i / 4][i % 4 * 4 + 2] = key[i * 4 + 2];
            roundKeys[i / 4][i % 4 * 4 + 3] = key[i * 4 + 3];
            i++;
        }

        i = KEY_SIZE / 4;

        while (i < BLOCK_SIZE * (NUM_ROUNDS + 1) / 4) {
            for (int k = 0; k < 4; k++) {
                temp[k] = roundKeys[(i - 1) / 4][(i - 1) % 4 * 4 + k];
            }

            if (i % (KEY_SIZE / 4) == 0) {
                temp = subWord(rotWord(temp));
                temp[0] ^= RCON[i / (KEY_SIZE / 4)];
            } else if (KEY_SIZE > 6 && i % (KEY_SIZE / 4) == 4) {
                temp = subWord(temp);
            }

            for (int k = 0; k < 4; k++) {
                roundKeys[i / 4][i % 4 * 4 + k] = (byte) (roundKeys[(i - KEY_SIZE / 4) / 4][(i - KEY_SIZE / 4) % 4 * 4 + k] ^ temp[k]);
            }
            i++;
        }

        return roundKeys;
    }

    private byte[] subWord(byte[] word) {
        byte[] result = new byte[word.length];
        for (int i = 0; i < word.length; i++) {
            result[i] = S_BOX[word[i] & 0xFF];
        }
        return result;
    }

    private byte[] rotWord(byte[] word) {
        byte[] result = new byte[word.length];
        result[0] = word[1];
        result[1] = word[2];
        result[2] = word[3];
        result[3] = word[0];
        return result;
    }

    public byte[] encrypt(byte[] plaintext) {
        if (plaintext.length != BLOCK_SIZE) {
            throw new IllegalArgumentException("Plaintext length must be 16 bytes");
        }

        byte[] state = Arrays.copyOf(plaintext, BLOCK_SIZE);

        addRoundKey(state, roundKeys[0]);

        for (int round = 1; round < NUM_ROUNDS; round++) {
            subBytes(state);
            shiftRows(state);
            mixColumns(state);
            addRoundKey(state, roundKeys[round]);
        }

        subBytes(state);
        shiftRows(state);
        mixColumns(state);
        addRoundKey(state, roundKeys[NUM_ROUNDS]);

        return state;
    }

    private void subBytes(byte[] state) {
        for (int i = 0; i < state.length; i++) {
            state[i] = S_BOX[state[i] & 0xFF];
        }
    }

    private void shiftRows(byte[] state) {
        byte[] temp = new byte[state.length];

        // Row 0 remains unchanged
        temp[0] = state[0];
        temp[1] = state[1];
        temp[2] = state[2];
        temp[3] = state[3];

        // Row 1 shifts left by 1
        temp[4] = state[5];
        temp[5] = state[6];
        temp[6] = state[7];
        temp[7] = state[4];

        // Row 2 shifts left by 2
        temp[8] = state[10];
        temp[9] = state[11];
        temp[10] = state[8];
        temp[11] = state[9];

        // Row 3 shifts left by 3
        temp[12] = state[15];
        temp[13] = state[12];
        temp[14] = state[13];
        temp[15] = state[14];

        System.arraycopy(temp, 0, state, 0, state.length);
    }

    private void mixColumns(byte[] state) {
        byte[] temp = new byte[BLOCK_SIZE];

        for (int i = 0; i < 4; i++) {
            int base = i * 4;
            temp[base] = (byte) (gfMult(state[base], (byte) 0x02) ^ gfMult(state[base + 1], (byte) 0x03) ^ state[base + 2] ^ state[base + 3]);
            temp[base + 1] = (byte) (state[base] ^ gfMult(state[base + 1], (byte) 0x02) ^ gfMult(state[base + 2], (byte) 0x03) ^ state[base + 3]);
            temp[base + 2] = (byte) (state[base] ^ state[base + 1] ^ gfMult(state[base + 2], (byte) 0x02) ^ gfMult(state[base + 3], (byte) 0x03));
            temp[base + 3] = (byte) (gfMult(state[base], (byte) 0x03) ^ state[base + 1] ^ state[base + 2] ^ gfMult(state[base + 3], (byte) 0x02));
        }

        System.arraycopy(temp, 0, state, 0, state.length);
    }

    private byte gfMult(byte a, byte b) {
        byte p = 0;
        byte hiBitSet;
        for (int i = 0; i < 8; i++) {
            if ((b & 1) != 0) {
                p ^= a;
            }
            hiBitSet = (byte) (a & 0x80);
            a <<= 1;
            if (hiBitSet != 0) {
                a ^= 0x1b;
            }
            b >>= 1;
        }
        return p;
    }

    private void addRoundKey(byte[] state, byte[] roundKey) {
        for (int i = 0; i < state.length; i++) {
            state[i] ^= roundKey[i];
        }
    }

    public static void main(String[] args) {
    // Custom key and plaintext inputs
    String customKey = "customkey123456789012345"; // Must be 24 bytes long
    String customPlaintext = "hello!";

    // Convert custom key and plaintext to byte arrays
    byte[] key = customKey.getBytes();
    byte[] plaintext = customPlaintext.getBytes();

    // Ensure the plaintext is padded to be 16 bytes (128 bits) long
    plaintext = Arrays.copyOf(plaintext, 16);

    // Create AES object with the custom key
    CustomAES192 aes = new CustomAES192(key);

    // Encrypt the custom plaintext
    byte[] ciphertext = aes.encrypt(plaintext);

    // Print results
    System.out.println("Custom Key: " + customKey);
    System.out.println("Custom Plaintext: " + new String(plaintext));
    System.out.println("Ciphertext: " + Base64.getEncoder().encodeToString(ciphertext));
}

}
