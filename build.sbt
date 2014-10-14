name := "wcs-commons"

version := "1.0"

scalaVersion := "2.10.4"

scalacOptions ++= Seq("-deprecation")

// scala-compiler is declared as an optional dependency by Slick.
// You need to add it explicitly to your own project if you want
// to use the direct embedding (as in SimpleExample.scala here).
libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-compiler" % _)

// Use the right Slick version here:
libraryDependencies += "com.typesafe.slick" %% "slick-extensions" % "2.1.0"

resolvers += "SysIQ artifactory commons " at "http://ci-main-01.ecofabric.com/artifactory/wsc-commons/"

resolvers += "SysIQ artifactory saas" at "http://ci-main-01.ecofabric.com/artifactory/wsc-saas/"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= List(
  "WSC-WC" % "Foundation-Core" % "7.0.0.6",
  "WSC-WC" % "Enablement-BaseComponentsLogic" % "7.0.0.6",
  "WSC-WC" % "Enablement-BaseComponentsData" % "7.0.0.6",
  "WSC-WC" % "Enablement-RelationshipManagementData" % "7.0.0.6",
  "WSC-WC" % "Enablement-RelationshipManagementLogic" % "7.0.0.6",
  "WSC-WC" % "Enablement-BusinessContextEngineAdvancedLogic" % "7.0.0.6",
  "WSC-WC" % "Catalog-ProductManagementData" % "7.0.0.6",
  "WSC-WC" % "Member-MemberManagementData" % "7.0.0.6",
  "WSC-WC" % "Order-OrderCaptureLogic" % "7.0.0.6",
  "WSC-WC" % "Order-OrderManagementData" % "7.0.0.6",
  "WSC-SDP-runtimes" % "j2ee" % "7.0.0.6",
  "WSC-SDP-runtimes" % "com.ibm.ws.prereq.vajava" % "7.0.0.6",
  "WSC-SDP-runtimes" % "com.ibm.ws.runtime" % "7.0.0.6",
  "WSC-SDP-runtimes" % "bootstrap" % "7.0.0.6",
  "WSC-SDP-plugins" % "com.ibm.ws.emf" % "7.0.0.6",
  "WSC-SDP-plugins" % "org.eclipse.emf.ecore" % "7.0.0.6",
  "WSC-SDP-plugins" % "org.eclipse.emf.common" % "7.0.0.6",
  "WSC-SDP-plugins" % "com.ibm.ffdc" % "7.0.0.6",
  "WSC-SDP-jre" % "ibmorb" % "7.0.0.6",
  "com.typesafe.akka" %% "akka-actor" % "2.3.4",
  "com.typesafe.akka" %% "akka-remote" % "2.3.4"
) 



lazy val akka_api = RootProject(file("../wcs-akka-api"))

lazy val root = project.in(file(".")).dependsOn(akka_api)
