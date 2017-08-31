
name := "scalaTest"

version := "1.0"

scalaVersion := "2.11.8"


lazy val root = Project("TestBed", new File("."))
.settings(
  libraryDependencies ++= Seq(
    "com.typesafe.akka" % "akka-actor_2.11" % "2.3.4"
  )

)
