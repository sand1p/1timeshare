package stubs

import java.util.Date

import model.Secret
import repository.SecretRepository

class StubSecreteRepository extends SecretRepository {

  override def save(key: String, secret: Secret): Unit.type = Unit

  override def getAndRemove(key: String): Option[Secret] = None

  override def removeKeys(expiredKeys: Seq[String]): Unit = Unit

  override def getExpiredKeys(now: Date): Seq[String] = null

}
