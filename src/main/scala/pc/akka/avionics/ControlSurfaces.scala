package pc.akka.avionics

import akka.actor.{Actor, ActorRef}
import pc.akka.avionics.ControlSurfaces.StickBack

object ControlSurfaces {
  case class StickBack(amount: Float)
  case class StickForward(amount: Float)
}

class ControlSurfaces(altimeter:ActorRef) extends Actor {
  override def receive: Receive = {
    case StickBack(amount) =>
      altimeter !
  }
}
