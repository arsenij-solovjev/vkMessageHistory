organization := "jinesra"

name := "vkMessageHistory"

version := "0.0.1"

scalaVersion := "2.10.3"

libraryDependencies += "org.rogach" %% "scallop" % "0.6.3"

seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

mainClass in oneJar := Some("jinesra.vkmessagehistory.sonarReview")

libraryDependencies += "commons-lang" % "commons-lang" % "2.6"

libraryDependencies += "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.3"

libraryDependencies += "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.3"

libraryDependencies += "com.jsuereth" %% "scala-arm" % "1.3"      

libraryDependencies += "org.scalaj" %% "scalaj-http" % "0.3.16" 

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

libraryDependencies += "net.liftweb" %% "lift-json" % "2.5"