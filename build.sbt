name := "validation"

version := "0.1.0"

scalaVersion := "2.11.8"

libraryDependencies += "com.lihaoyi" %% "utest" % "0.5.4" % "test"

libraryDependencies += "org.typelevel" %% "cats-laws" % "1.0.0-MF"

libraryDependencies += "org.typelevel" %% "cats-core" % "1.0.0-MF"

testFrameworks += new TestFramework("utest.runner.Framework")
