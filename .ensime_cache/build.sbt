
import sbt._
import java.io._

scalaVersion := "2.11.5"

resolvers += Resolver.sonatypeRepo("snapshots")

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Akka Repo" at "http://repo.akka.io/repository"

libraryDependencies += "org.ensime" %% "ensime" % "0.9.10-SNAPSHOT"

// guaranteed to exist when started from emacs
val JavaTools = new File(sys.env("JAVA_HOME"), "/lib/tools.jar")

unmanagedClasspath in Runtime += { Attributed.blank(JavaTools) }

mainClass in Compile := Some("org.ensime.server.Server")

fork := true

javaOptions ++= Seq (
  "-Dscala.usejavacp=true",
  "-Densime.config=/home/wohanley/software/dance-suggestions/.ensime",
  "-Densime.cachedir=/home/wohanley/software/dance-suggestions/.ensime_cache/",
  "-Densime.active=IGNORED"
)

javaOptions += "-Xms512M"

javaOptions += "-Xmx1536M"

javaOptions += "-Xss1M"

javaOptions += "-XX:+CMSClassUnloadingEnabled"

javaOptions += "-XX:MaxPermSize=256M"
