package repository

import java.util.Date

import javax.inject.Singleton
import model.Secrete

import scala.collection.mutable
import scala.collection.mutable.HashMap

@Singleton
class SecreteRepository {
  //as we want to store key value
  val map: mutable.HashMap[String, Secrete] = new HashMap[String, Secrete]
  val predicate: (String, Secrete) => Boolean = (key, value) => true

  def save(key: String, secrete: Secrete) = {
    map.put(key, secrete)
    Unit
  }

  def getAndRemove(key: String): Option[Secrete] = {
    val secrete = map.get(key)
    map.remove(key)
    secrete
  }

  def getExpiredKeys(now: Date): Seq[String] = {
    map.filter(p => p._2.expireAt.before(now)).keySet.toSeq
  }

  def removeKeys(expiredKeys: Seq[String]) = {
    expiredKeys.foreach(key => map.remove(key))
  }
}
