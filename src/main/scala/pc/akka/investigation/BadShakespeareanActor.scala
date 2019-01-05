package pc.akka.investigation

import akka.actor.{Actor, ActorSystem, Props}

class BadShakespeareanActor extends Actor {
  override def receive: Receive = {
    case "Good Morning" =>
      println("PC: Good morning.")
    case "You're terrible" =>
      println("PC: Ok. Bye bye. See you never")
  }
}

object BadShakespeareanActor {
  val system = ActorSystem("BadShakespearean")
  val actor = system.actorOf(Props[BadShakespeareanActor])

  def send(msg: String) {
    println(s"Me: $msg")
    actor ! msg
  }

  def main(args: Array[String]): Unit = {
    send("Good Morning")
    send("You're terrible")
    system.terminate()
  }
}
