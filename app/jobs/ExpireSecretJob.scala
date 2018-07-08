package jobs

import akka.actor.ActorSystem
import javax.inject.Inject
import repository.SecretRepository

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._


class ExpireSecretJob @Inject()(actorSystem: ActorSystem, secretRepository: SecretRepository)(implicit executionContext: ExecutionContext) {
  actorSystem.scheduler.schedule(initialDelay = 10.seconds, interval = 1.minutes) {
    println(s"removing expired  keys")
    val expiredKeys: Seq[String] = secretRepository.getExpiredKeys(new java.util.Date())

    secretRepository.removeKeys(expiredKeys)
    println(s"removed expired keys: $expiredKeys ")
  }
}
