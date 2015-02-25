import NativePackagerKeys._

packageArchetype.java_application

name := """dancebot"""
version := "1.0"

scalaVersion := "2.11.5"

scalacOptions += "-deprecation"

// Web stuff
libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "3.0.5"

