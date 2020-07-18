val settingsHelper = ProjectSettingsHelper("au.id.tmm", "plotly-scala-facade")(
  githubProjectName = "plotly-scala-facade",
)

settingsHelper.settingsForBuild

lazy val root = project
  .in(file("."))
  .settings(settingsHelper.settingsForRootProject)
  .settings(console := (console in Compile in core).value)
  .aggregate(
    core,
  )

val circeVersion = "0.14.0-M1"

lazy val core = project
  .in(file("core"))
  .settings(settingsHelper.settingsForSubprojectCalled("core"))
  .settings(
    libraryDependencies += "io.circe" %% "circe-core" % circeVersion,
  )

lazy val examples = project
  .in(file("examples"))
  .settings(settingsHelper.settingsForSubprojectCalled("examples"))
  .settings(publish / skip := true)
  .dependsOn(core)

addCommandAlias("check", ";+test;scalafmtCheckAll")
