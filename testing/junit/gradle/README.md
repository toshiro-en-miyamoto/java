## [Building Java Applications with Gradle](https://docs.gradle.org/current/samples/sample_building_java_applications.html)

Create a Gradle Java project with the Gradle for Java extension:

- From the command palette, choose *Create a Gradle Java project (advance)*,
- select the directory where the project is placed,
- project type: appication,
- build script DSL: Groovy,
- test framework: JUnit Jupiter,
- project name: (anything), and
- package name: (anthing).

Create your test code in the appropriate directory under `app/src/test/java`. Then *Test task* will be displayed on the left side menu, which allows you to run JUnit tests.

## [Upgrading the Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html#sec:upgrading_wrapper)

Check the latest [release](https://gradle.org/releases/), then:

```bash
$ ./gradlew wrapper --gradle-version 7.5.1
```

