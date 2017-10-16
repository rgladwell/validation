name := "validation"

version := "0.1.0"

scalaVersion := "2.11.8"

libraryDependencies += "com.lihaoyi" %% "utest" % "0.5.4" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")
