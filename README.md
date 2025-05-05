# 🔐 Enhanced AES with Increased MixColumns Execution

This repository contains a **Java-based implementation** of a modified AES algorithm in which the **MixColumns phase is executed in all rounds**, including the final round. This change aims to balance all transformation phases (SubBytes, ShiftRows, MixColumns, AddRoundKey) across the AES process for **AES-128** and **AES-192** variants.

📌 The goal is to enhance **security metrics** such as:
- Avalanche Effect
- Entropy
- Key Sensitivity
- Bit-level Diffusion

---

## 📄 Research Paper

**Title:** *Assessing the Impact of Increased MixColumns on AES Encryption Security and Performance*  
**Authors:** Prathmesh Deshkar, Ritesh Pandey  
**Published in:** *International Journal for Scientific Research and Development (IJSRD)*  
**Volume 12, Issue 4, July 2024*

📄 Full paper available in `/report/AES_MixColumns_Research_Paper.pdf`

---

## 🧠 Motivation

The standard AES skips the MixColumns step in the final round, which slightly compromises diffusion. By executing MixColumns uniformly across **all rounds**, this implementation improves the cryptographic strength of the algorithm — at the cost of a marginal increase in execution time.

---

## 📁 Project Structure

```plaintext
.
├── src/                      # AES Implementations
│   ├── CustomAES128.java     # Modified AES-128 with full MixColumns
│   └── CustomAES192.java     # Modified AES-192 with full MixColumns
│
├── tester/                   # Testing Modules
│   ├── AvalancheEffect.java
│   ├── BitFlipper.java
│   ├── CipherBitFlipper.java
│   ├── EntropyCalculator.java
│   └── HammingDistanceCalculator.java
│
├── Result/                   # Result Analysis
│   ├── output_analysis.txt
│   ├── performance_metrics.xlsx
│   ├── graphs/
│   │   ├── entropy_comparison.png
│   │   ├── avalanche_effect.png
│   │   ├── execution_time.png
│   │   └── key_sensitivity.png
│   └── README.md             # Explanation of results
│
├── report/                   # Research Document
│   └── AES_MixColumns_Research_Paper.pdf
│
├── requirements.txt          # Optional Python libraries (for plotting)
└── README.md                 # You’re reading it!

```

---

## 🧪Results Summary

```plaintext

| **Metric**         | **Standard AES** | **Modified AES** |
|--------------------|------------------|------------------|
| Avalanche Effect   | 48.26%           | 52.12%           |
| Entropy            | 7.83             | 7.98             |
| Execution Time     | 1.32 ms          | 1.44 ms          |
| Key Variation      | Moderate         | High             |

```

✅ All major security indicators improved <br>
⚠️ Minor increase in time (~9%) — acceptable trade-off for stronger diffusion.

---

## 🚀 Running the Project

**1. Compile**

```plaintext

javac src/CustomAES128.java
javac src/CustomAES192.java
javac tester/*.java

```

**2. Run Example**

```plaintext

java src.CustomAES128

```

**3. Run Security Test Modules**

```plaintext

java tester.EntropyCalculator
java tester.AvalancheEffect
# etc.

```

---

## 🛠 Technologies Used

- Java 8+

- Excel (for tabulation)

---

## 📬 Citation

If you use this work in your research or projects, please cite the following paper:

***bibtex***

```plaintext

@article{deshkar2024mixcolumns,
  title     = {Assessing the Impact of Increased MixColumns on AES Encryption Security and Performance},
  author    = {Prathmesh Deshkar and Ritesh Pandey},
  journal   = {International Journal for Scientific Research and Development (IJSRD)},
  volume    = {12},
  number    = {4},
  year      = {2024},
  month     = {July}
}

```

---