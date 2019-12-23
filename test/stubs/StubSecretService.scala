package stubs

import java.util.Date

import model.Secret
import service.SecretService

class StubSecretService extends SecretService(null,null) {

  override def save(secret: Secret): String = "sdecfgbhjmkdsdfgbhjmk"

  override def get(key: String): Option[Secret] = Option(Secret("password", new Date))

}