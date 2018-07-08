package jobs

import akka.actor.ActorSystem
import javax.inject.Inject
import repository.SecreteRepository

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._


class ExpireSecreteJob @Inject()(actorSystem: ActorSystem, secreteRepository: SecreteRepository)(implicit executionContext: ExecutionContext) {
  actorSystem.scheduler.schedule(initialDelay = 1.seconds, interval = 10.seconds) {
    println(s"removing expired  keys")
    val expiredKeys: Seq[String] = secreteRepository.getExpiredKeys(new java.util.Date())

    secreteRepository.removeKeys(expiredKeys)
    println(s"removed expired keys: $expiredKeys ")
  }
}
