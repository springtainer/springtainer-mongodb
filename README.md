# springtainer-mongodb

[![Maven Central](https://img.shields.io/maven-central/v/com.avides.springboot.springtainer/springtainer-mongodb.svg?label=maven-central)](https://search.maven.org/artifact/com.avides.springboot.springtainer/springtainer-mongodb)
[![Release](https://github.com/springtainer/springtainer-mongodb/actions/workflows/release.yml/badge.svg)](https://github.com/springtainer/springtainer-mongodb/actions/workflows/release.yml)
[![Nightly build](https://github.com/springtainer/springtainer-mongodb/actions/workflows/nightly.yml/badge.svg)](https://github.com/springtainer/springtainer-mongodb/actions/workflows/nightly.yml)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=springtainer_springtainer-mongodb&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=springtainer_springtainer-mongodb)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=springtainer_springtainer-mongodb&metric=coverage)](https://sonarcloud.io/summary/new_code?id=springtainer_springtainer-mongodb)

### Dependency

```xml

<dependency>
  <groupId>com.avides.springboot.springtainer</groupId>
  <artifactId>springtainer-mongodb</artifactId>
  <version>2.0.0-RC1</version>
  <scope>test</scope>
</dependency>
```

### Configuration

Properties consumed (in `bootstrap.properties`):

- `embedded.container.mongodb.enabled` (default is `true`)
- `embedded.container.mongodb.startup-timeout` (default is `30`)
- `embedded.container.mongodb.docker-image` (default is `mongo:4.2.24`)
- `embedded.container.mongodb.port` (default is `27017`)

Properties provided (in `application-it.properties`):

- `embedded.container.mongodb.host`
- `embedded.container.mongodb.port`

Example for minimal configuration in `application-it.properties`:

```
spring.data.mongodb.uri=mongodb://${embedded.container.mongodb.host}:${embedded.container.mongodb.port}/test
```

## Logging

To reduce logging insert this into the logback-configuration:

```xml
<!-- Springtainer -->
<logger name="com.github.dockerjava.jaxrs" level="WARN" />
<logger name="com.github.dockerjava.core.command" level="WARN" />
<logger name="org.apache.http" level="WARN" />
```

## Labels

The container exports multiple labels to analyze running springtainers:

- `SPRINGTAINER_SERVICE=mongodb`
- `SPRINGTAINER_IMAGE=${embedded.container.mongodb.docker-image}`
- `SPRINGTAINER_STARTED=$currentTimestamp`
