package service

import java.util.UUID

import javax.inject.{Inject, Singleton}
import model.Secrete
import play.Logger
import repository.SecreteRepository

@Singleton
class SecreteService @Inject()(secreteRepository: SecreteRepository) {
  def save(secrete: Secrete) = {
    val key = UUID.randomUUID.toString
    secreteRepository.save(key,secrete)
    key
  }

  def get(key: String) = {
    secreteRepository.getAndRemove(key)
  }

}
