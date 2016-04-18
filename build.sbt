name := """helios-logreader"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"
libraryDependencies ++= Seq("com.github.davidmoten" % "rxjava-file" % "0.4",
      "com.typesafe.play" %% "play-ws" % "2.5.2",
      "io.reactivex" %% "rxscala" % "0.26.1"
)

