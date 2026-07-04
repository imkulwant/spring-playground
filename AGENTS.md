# Project agent memory

This file is the project's committed home for project-intrinsic agent knowledge: build, test, release, architecture, and sharp-edge notes that should travel with the code.

- Add durable project-specific notes here as they are discovered through real work.

## Build prerequisites

- **Java 17** required (Spring Boot 3.2.5 / `maven.compiler.source=17`).
- **`protoc` binary** must be installed at `/opt/homebrew/bin/protoc` for the `spring-data-mysql-example-api` module to generate sources.
- Do not run tests from the root; several modules require external services (MySQL, Elasticsearch, etc.). Use `mvn compile` to verify the build.

## Protobuf version alignment

The `spring-data/spring-data-mysql-example` parent pom pins the protobuf runtime via `protobuf-java.version`.
This version **must match** the system `protoc` binary (the `protobuf-maven-plugin` calls it directly).

| `protoc` version (`protoc --version`) | Required Java runtime version |
|---------------------------------------|-------------------------------|
| `libprotoc 35.0`                      | `4.35.1`                      |

Misalignment causes a compile error on generated sources: `cannot find symbol: method getMessageType(int) on class com.google.protobuf.Descriptors.FileDescriptor`.

If the system `protoc` is upgraded, update `protobuf-java.version` in `spring-data/spring-data-mysql-example/pom.xml` to the matching `4.X.Y` release.
The major version maps directly: `libprotoc N.0` -> Java runtime `4.N.Y`.
