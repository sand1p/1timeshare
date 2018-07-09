package repository

import java.util.Date

import javax.inject.Singleton
import model.Secret

import scala.collection.mutable
import scala.collection.mutable.HashMap

@Singleton
class SecretRepository {
  //as we want to store key value
  val map: mutable.HashMap[String, Secret] = new HashMap[String, Secret]

  def save(key: String, secret: Secret) = {
    map.put(key, secret)
    Unit
  }

  def getAndRemove(key: String): Option[Secret] = {
    val secret = map.get(key)
    map.remove(key)
    secret
  }

  def getExpiredKeys(now: Date): Seq[String] = {
    map.filter {
      case (key, value) => value.expireAt.before(now)
    }.keySet.toSeq
    //map.filter(p => p._2.expireAt.before(now)).keySet.toSeq
  }

  def removeKeys(expiredKeys: Seq[String]) = {
    expiredKeys.foreach(key => map.remove(key))
  }
}
