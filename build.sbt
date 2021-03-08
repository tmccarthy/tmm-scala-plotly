ThisBuild / sonatypeProfile := "au.id.tmm"
ThisBuild / baseProjectName := "tmm-scala-plotly"

lazy val root = project
  .in(file("."))
  .settings(settingsForRootProject)
  .settings(console := (console in Compile in core).value)
  .aggregate(
    core,
  )

val circeVersion = "0.14.0-M1"

lazy val core = project
  .in(file("core"))
  .settings(settingsForSubprojectCalled("core"))
  .settings(
    libraryDependencies += "io.circe" %% "circe-core" % circeVersion,
    testFrameworks += new TestFramework("munit.Framework"),
  )

lazy val examples = project
  .in(file("examples"))
  .settings(settingsForSubprojectCalled("examples"))
  .settings(publish / skip := true)
  .settings(testFrameworks += new TestFramework("munit.Framework"))
  .dependsOn(core)
