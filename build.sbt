
name := "scalaTest"

version := "1.0"

scalaVersion := "2.11.8"

val sprayVersion = "1.3.4"


resolvers += "spray repo" at "http://repo.spray.io"


lazy val root = Project("TestBed", new File("."))
.settings(
  libraryDependencies ++= Seq(
    "com.typesafe.akka" % "akka-actor_2.11" % "2.3.4",
    "io.spray" %% "spray-io" % sprayVersion,
    "io.spray" %% "spray-http" % sprayVersion,
    "io.spray" %% "spray-util" % sprayVersion,
    "io.spray" %% "spray-client" % sprayVersion
//    "io.grpc" % "grpc-netty" % com.trueaccord.scalapb.compiler.Version.grpcJavaVersion,
//    "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % com.trueaccord.scalapb.compiler.Version.scalapbVersion
  )


)
