package service

import java.util.UUID

import javax.inject.{Inject, Singleton}
import repository.SecreteRepository

@Singleton
class SecreteService @Inject()(secreteRepository: SecreteRepository) {
  def save(secrete: Option[String]) = {
    val key = UUID.randomUUID.toString
    secreteRepository.save(key, secrete.get)
    key
  }

  def get(key: String) = {
    secreteRepository.getAndRemove(key)
  }

}
