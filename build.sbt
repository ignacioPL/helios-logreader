name := """helios-logreader"""

scalaVersion := "2.12.1"

libraryDependencies ++= {
  Seq(
    "ch.qos.logback"             % "logback-classic"         % "1.1.7",
    "com.github.davidmoten"      % "rxjava-file"             % "0.4.2",
    "com.iheart"                 %% "ficus"                  % "1.4.0",
    "com.softwaremill.macwire"   %% "macros"                 % "2.3.0" % Provided,
    "com.typesafe.scala-logging" %% "scala-logging"          % "3.5.0",
    "com.typesafe.play"          %% "play-ahc-ws-standalone" % "1.0.0-M6",
    "io.reactivex"               %% "rxscala"                % "0.26.5",
    "org.scalatest"              %% "scalatest"              % "3.0.1" % Test
  )
}

