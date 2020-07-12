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

lazy val core = project
  .in(file("core"))
  .settings(settingsHelper.settingsForSubprojectCalled("core"))

addCommandAlias("check", ";+test;scalafmtCheckAll")
