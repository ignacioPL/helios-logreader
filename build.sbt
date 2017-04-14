import ReleaseTransformations._

name := """helios-logreader"""

scalaVersion := "2.12.1"

mainClass in Compile := Some("logreader.Run")

enablePlugins(sbtdocker.DockerPlugin, JavaAppPackaging)

val namespace = "helios-monitor"

libraryDependencies ++= {
  Seq(
    "ch.qos.logback"             % "logback-classic"              % "1.1.7",
    "com.github.davidmoten"      % "rxjava-file"                  % "0.4.2",
    "com.iheart"                 %% "ficus"                       % "1.4.0",
    "com.softwaremill.macwire"   %% "macros"                      % "2.3.0" % Provided,
    "com.typesafe.scala-logging" %% "scala-logging"               % "3.5.0",
    "com.typesafe.play"          %% "play-ahc-ws-standalone"      % "1.0.0-M6",
    "io.reactivex"               %% "rxscala"                     % "0.26.5",
    "org.scalatest"              %% "scalatest"                   % "3.0.1" % Test,
    "org.scalamock"              %% "scalamock-scalatest-support" % "3.5.0" % Test
  )
}

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommand("dockerBuildAndPush"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)

dockerfile in docker := {

  val appDir: File = stage.value
  val targetDir = "/app"

  new Dockerfile {
    from("anapsix/alpine-java:jdk8")
    entryPoint(s"$targetDir/bin/${executableScriptName.value}")
    copy(appDir, targetDir)
  }
}

imageNames in docker := Seq(
  ImageName(
    namespace = Some(namespace),
    repository = name.value,
    tag = Some(version.value)
  ),
  ImageName(s"$namespace/${name.value}:latest")
)