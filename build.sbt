name := """trampoline-EC"""


lazy val commonSettings = Seq(
  version := "0.1.0",
  scalaVersion := "2.11.7",
  javaOptions in run += "-Xss2M"
)


lazy val code = (project in file("code")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= {
      val scalazVersion = "7.2.0"

      "org.scalaz"        %% "scalaz-core"        % scalazVersion ::
      "org.scalaz"        %% "scalaz-concurrent"  % scalazVersion ::
      "com.typesafe.akka" %% "akka-http-core"     % "2.4.2" ::
      Nil
    }
  )

lazy val benchmarks = (project in file("benchmarks")).
  settings(commonSettings: _*).
  dependsOn(code).
  enablePlugins(JmhPlugin)
