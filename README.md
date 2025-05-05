# 🔐 Enhanced AES with Increased MixColumns Execution

This repository presents a research-based implementation and evaluation of a **modified AES algorithm** where the **MixColumns phase is executed in every round**, including the final round, to match the frequency of other core AES operations. The objective is to enhance security metrics such as **entropy**, **avalanche effect**, and **key sensitivity**.

This modification is applied to **AES-128** and **AES-192**, and results are compared against standard AES implementations.

---

## 📄 Research Paper

**Title:** *Assessing the Impact of Increased MixColumns on AES Encryption Security and Performance*  
**Authors:** Prathmesh Deshkar, Ritesh Pandey  
**Published in:** *IJSRD, Volume 12, Issue 4*

---

## 🧠 Motivation

In standard AES, the final round **skips the MixColumns step** to improve performance. However, this can reduce diffusion in the final round. By enforcing **equal execution of MixColumns in all rounds**, this project aims to:

- Increase confusion and diffusion
- Enhance avalanche effect
- Improve entropy and key sensitivity
- Slightly trade off execution time for improved security

---

## 🏗️ Project Structure
.
├── src/ # AES-128 and AES-192 with modified MixColumns
│ ├── CustomAES128.java
│ └── CustomAES192.java
│
├── tester/ # Tester Codes
│ ├── AvalancheEffect.java
│ ├── BitFlipper.java
│ ├── CipherBitFlipper.java
│ ├── EntropyCalculator.java
│ └── HammingDistanceCalculator.java
│
├── Result/
│ ├── output_analysis.txt # Summary of results
│ ├── performance_metrics.xlsx
│ ├── graphs/
│ │ ├── entropy_comparison.png
│ │ ├── avalanche_effect.png
│ │ ├── execution_time.png
│ │ └── key_sensitivity.png
│ └── README.md # Explanation of result contents
│
├── report/
│ └── AES_MixColumns_Research_Paper.pdf
│
├── requirements.txt
└── README.md


@article{Prathmesh Deshkar,
  title={Assessing the Impact of Increased MixColumns on AES Encryption Security and Performance},
  author={Prathmesh Deshkar and Ritesh Pandey},
  journal={International Journal for Scientific Research and Development (IJSRD)},
  volume={12},
  number={4},
  year={2024},
  month={July}
}

