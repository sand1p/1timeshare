package service

import java.util.UUID

import javax.inject.{Inject, Singleton}
import model.Secret
import repository.SecretRepository

@Singleton
class SecretService @Inject()(secretRepository: SecretRepository) {
  def save(secret: Secret) = {
    val key = UUID.randomUUID.toString
    secretRepository.save(key, secret)
    key
  }

  def get(key: String): Option[Secret] = {
    secretRepository.getAndRemove(key)
  }
}
