name := "purepy"

version := "0.1"

scalaVersion := "2.12.4"

scalacOptions ++= Seq (
  "-Xfatal-warnings",
  "-Xfuture",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ypartial-unification",
  "-Ywarn-macros:after",
  "-Ywarn-unused:explicits,-implicits",
  "-Ywarn-unused-import",
  "-Ywarn-value-discard",
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-unchecked")

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.0.1",
  "org.typelevel" %% "cats-free" % "1.0.1",
  "org.typelevel" %% "cats-effect" % "0.9",
  "org.scalacheck" %% "scalacheck" % "1.13.5",
  "com.chuusai" %% "shapeless" % "2.3.3",
  "org.scalatest" %% "scalatest" % "3.0.1" % Test
)