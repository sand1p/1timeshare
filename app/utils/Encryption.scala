package utils

class Encryption {

  def encrypt(plaintext: String): String = {
    plaintext.reverse
  }

  def decrypt(ciphertext: String): String = {
    ciphertext.reverse
  }
}
