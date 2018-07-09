package service

import java.util.Date

import model.Secret
import org.scalatestplus.play.PlaySpec
import repository.SecretRepository
import org.scalatest.MustMatchers
import org.scalatest.Assertion
import utils.Encryption
class SecretServiceSpec extends PlaySpec {

  "service.SecretService get None" should {
    "should return None for given key that is not present" in {
      val secretService = new SecretService(new StubServiceRepository, new Encryption)
      secretService.get("dsfsdffsdf") mustBe(None)
    }
  }

  "service.SecretService get secrete" should {
    "should return None for given key that is not present" in {
      val secretService = new SecretService(new StubServiceRepository, new Encryption)
      secretService.get("dsfsdffsdf") mustBe(None)
    }
  }

  "service.SecretService save" should {
    "should return key for given secret" in {
      val secretService = new SecretService(new StubServiceRepository, new Encryption)
      secretService.save( Secret("dsfsdf", new  Date()))
    }
  }

  class StubServiceRepository extends SecretRepository {
    override def save(key: String, secret: Secret): Unit.type = Unit

    override def getAndRemove(key: String): Option[Secret] = None

    override def removeKeys(expiredKeys: Seq[String]): Unit = Unit

    override def getExpiredKeys(now: Date): Seq[String] = null
  }

}
