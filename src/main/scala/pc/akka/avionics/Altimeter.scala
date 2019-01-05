package pc.akka.avionics

import akka.actor.{Actor, ActorLogging}
import pc.akka.avionics.Altimeter.{RateChange, Tick}

import scala.concurrent.duration._

class Altimeter extends Actor with ActorLogging {
  val ceiling = 43000
  val maxRateOfClimb = 5000
  var rateOfClimb:Float = 0
  var altitude:Double = 0
  var lastTick = System.currentTimeMillis()
  val ticker = context.system.scheduler.schedule(100.millis, 100.millis, self, Tick)

  override def receive: Receive = {
    case Tick =>
      val tick = System.currentTimeMillis()
      altitude = altitude + ((tick-lastTick)/60000.0) * rateOfClimb
      lastTick = tick
    case RateChange(amount) => {
      rateOfClimb = amount.min(1.0f).max(-1.0f) * maxRateOfClimb
      log.info(s"Altimeter changed rate of climb to $rateOfClimb.")
    }
  }

  override def postStop(): Unit = ticker.cancel()
}

object Altimeter {
  case class RateChange(amount: Float)
  case object Tick
}
