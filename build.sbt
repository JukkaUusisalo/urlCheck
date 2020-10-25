// factor out common settings into a sequence
lazy val commonSettings = Seq(
  organization := "org.jukkauus",
  version := "0.1.0",
  // set the Scala version used for the project
  scalaVersion := "2.13.3"
)



lazy val root = (project in file("."))
  .settings(
    commonSettings,

    // set the name of the project
    name := "urlCheck",

    // set the main Scala source directory to be <base>/src
    scalaSource in Compile := baseDirectory.value / "src",

    // set the Scala test source directory to be <base>/test
    scalaSource in Test := baseDirectory.value / "test",

    // reduce the maximum number of errors shown by the Scala compiler
    maxErrors := 20,

    // increase the time between polling for file changes when using continuous execution
    pollInterval := scala.concurrent.duration.FiniteDuration(1000,"milliseconds"),

    // append several options to the list of options passed to the Java compiler
    javacOptions ++= Seq("-source", "1.5", "-target", "1.5"),

    // append -deprecation to the options passed to the Scala compiler
    scalacOptions += "-deprecation",

    // define the statements initially evaluated when entering 'console', 'consoleQuick', or 'consoleProject'
    initialCommands := """
                         |import System.{currentTimeMillis => now}
                         |def time[T](f: => T): T = {
                         |  val start = now
                         |  try { f } finally { println("Elapsed: " + (now - start)/1000.0 + " s") }
                         |}""".stripMargin,


    // set the main class for packaging the main jar
    // 'run' will still auto-detect and prompt
    // change Compile to Test to set it for the test jar
    mainClass in (Compile, packageBin) := Some("urlCheck"),

    // set the main class for the main 'run' task
    // change Compile to Test to set it for 'test:run'
    mainClass in (Compile, run) := Some("urlCheck"),


    // set the prompt (for the current project) to include the username
    shellPrompt := { state => System.getProperty("user.name") + "> " },

    // disable printing timing information, but still print [success]
    showTiming := false,

    // disable printing a message indicating the success or failure of running a task
    showSuccess := false,

    // change the format used for printing task completion time
    timingFormat := {
      import java.text.DateFormat
      DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
    },

    // disable using the Scala version in output paths and artifacts
    crossPaths := false,

    // fork a new JVM for 'run' and 'test:run'
    fork := true,

    // fork a new JVM for 'test:run', but not 'run'
    fork in Test := true,

    // add a JVM option to use when forking a JVM for 'run'
    javaOptions += "-Xmx2G",

    // only use a single thread for building
    parallelExecution := false,

    // Execute tests in the current project serially
    //   Tests from other projects may still run concurrently.
    parallelExecution in Test := false,



    // Copy all managed dependencies to <build-root>/lib_managed/
    //   This is essentially a project-local cache and is different
    //   from the lib_managed/ in sbt 0.7.x.  There is only one
    //   lib_managed/ in the build root (not per-project).
    retrieveManaged := true

  )