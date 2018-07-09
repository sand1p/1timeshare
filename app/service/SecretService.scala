package service

import java.util.UUID

import javax.inject.{Inject, Singleton}
import model.Secret
import repository.SecretRepository
import utils.Encryption

@Singleton
class SecretService @Inject()(secretRepository: SecretRepository, encryption: Encryption) {

  def save(secret: Secret): String = {
    val key = UUID.randomUUID.toString
    secretRepository.save(key, new Secret(encryption.encrypt(secret.value), secret.expireAt))
    key
  }

  def get(key: String): Option[Secret] = {
    //secretRepository.getAndRemove(key)
    secretRepository.getAndRemove(key) match {
      case Some(secret) => Some(new Secret(encryption.decrypt(secret.value), secret.expireAt))
      case _ => None
    }
  }

}