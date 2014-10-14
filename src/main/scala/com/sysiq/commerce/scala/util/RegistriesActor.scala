package com.sysiq.commerce.scala.util

import akka.actor._
import akka.util._
import scala.concurrent.duration._
import com.ibm.commerce.registry._
import scala.collection.JavaConverters._
import akka.pattern.{ ask, pipe }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import com.typesafe.config.ConfigFactory
import com.sysiq.commerce.akka.api.RegistriesAPI._
import akka.dispatch.OnComplete

class WCS {

}

object WCS {  

  def cache = CommandManager.cache

  def registries = {
    RegistryManager.singleton().list().asScala.asInstanceOf[Iterator[String]].toList
  }

}

class RegistriesActor extends Actor with ActorLogging {

  def receive = {
    case Command => sender ! CommandResponse(WCS.cache)
    case RegistriesList => sender ! WCS.registries
  }
}

object Wrapper{
  implicit val timeout = Timeout(5 seconds)
  val system = ActorSystem("WCSSystem")
  val registries = system.actorOf(Props[RegistriesActor], name = "registries")  
}

object TestActor extends App {
  import Wrapper._
  val r = (registries ? Command) onComplete {x=>
    println(x)
  }
}