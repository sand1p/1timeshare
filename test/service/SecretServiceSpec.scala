package service

import java.util.Date

import model.Secret
import org.scalatestplus.play.PlaySpec
import stubs.StubSecreteRepository
import utils.Encryption
class SecretServiceSpec extends PlaySpec{

  "service.SecretService get None" should {
    "should return None for given key that is not present" in {
      val secretService = new SecretService(new StubSecreteRepository, new Encryption)
      secretService.get("dsfsdffsdf") mustBe(None)
    }
  }

  "service.SecretService save" should {
    "should return key for given secret" in {
      val secretService = new SecretService(new StubSecreteRepository, new Encryption)
      secretService.save( Secret("dsfsdf", new  Date()))
    }
  }

}
