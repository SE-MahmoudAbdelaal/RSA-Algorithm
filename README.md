# RSA Algorithm Implementation

## 📌 Introduction
The **RSA Algorithm (Rivest-Shamir-Adleman)** is a widely used public-key cryptosystem for secure data transmission. It is based on the mathematical difficulty of factoring large prime numbers. This implementation demonstrates how RSA works, including **key generation, encryption, and decryption**.

## 🚀 Features
- Generates RSA key pairs (public & private keys)
- Encrypts messages using the public key
- Decrypts messages using the private key
- Secure and efficient modular arithmetic operations

## 🛠️ Technologies Used
- **Programming Language**: Java
- **Cryptographic Libraries**: (If applicable, mention any libraries used)

## 📖 How It Works
1. **Key Generation**
   - Select two large prime numbers, `p` and `q`.
   - Compute `n = p × q`.
   - Compute Euler's totient function: `ϕ(n) = (p-1) × (q-1)`.
   - Choose an exponent `e` such that `1 < e < ϕ(n)` and `gcd(e, ϕ(n)) = 1`.
   - Compute the private exponent `d ≡ e⁻¹ mod ϕ(n)`.
   - Public Key: `(e, n)`, Private Key: `(d, n)`.

2. **Encryption**
   - Convert the message `M` into an integer `m` such that `0 ≤ m < n`.
   - Compute ciphertext: `c ≡ m^e mod n`.

3. **Decryption**
   - Compute original message: `m ≡ c^d mod n`.
   - Convert `m` back into the original message `M`.

## 🔧 Installation & Usage
```bash
# Clone the repository
git clone https://github.com/SE-MahmoudAbdelaal/RSA-Algorithm.git
cd rsa-algorithm

# Compile and run the program
javac RSA.java
java RSA
```

## 📂 Project Structure
```
📁 rsa-algorithm
│── 📄 RSA.java
│── 📄 README.md
│── 📄 LICENSE (if applicable)
```

## 📜 License
This project is licensed under the **MIT License** – see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing
Contributions are welcome! Feel free to submit a pull request or open an issue to improve the implementation.

## 📬 Contact
For any inquiries, contact me at **mahmoud.abdelaal.dev@gmail.com**.

---
**⭐ Don't forget to star this repository if you find it useful! ⭐**
