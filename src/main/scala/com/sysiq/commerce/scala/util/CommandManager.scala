package com.sysiq.commerce.scala.util

import com.ibm.commerce.registry._
import scala.collection.JavaConverters._
import com.sysiq.commerce.akka.api.RegistriesAPI._

object CommandManager {

  lazy val cmdReg = CommandRegistry.singleton()

  def init = {
    StoreRegistry.singleton().initialize()
    cmdReg.initialize()
  }

  def cache: scala.collection.mutable.Map[String, CommandRegistryEntryV] = {
    val f = classOf[CommandRegistry].getDeclaredField("cache")
    f.setAccessible(true)
    f.get(null).asInstanceOf[java.util.Map[String, CommandRegistryEntry]].asScala.map { t =>
      val e = t._2
      (t._1, CommandRegistryEntryV(e.getKey(), e.getInterfaceName(), e.getClassName(), e.getStoreEntityId()))
    }
  }

  def changeCmdReg(interfaceName: String, storeId: Integer, newClassName: String) = {
    val entry = cmdReg.find(interfaceName, storeId)
    entry.setClassName(newClassName)
    cmdReg.addElement(entry)
    entry
  }

  def main(args: Array[String]): Unit = {
    init
    changeCmdReg("com.ibm.commerce.account.commands.AccountSaveCmd", 10651, "new command implementation")
  }
}