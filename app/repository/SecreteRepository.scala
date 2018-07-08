package repository

import javax.inject.Singleton

import scala.collection.mutable
import scala.collection.mutable.HashMap

@Singleton
class SecreteRepository {
  //as we want to store key value
  val map: mutable.HashMap[String, String] = new HashMap[String, String]

  def save(key: String, secrete: String) = {
    map.put(key, secrete)
    Unit
  }


  def getAndRemove(key: String): Option[String] = {
    val secrete = map.get(key)
    map.remove(key)
    secrete
  }

}
