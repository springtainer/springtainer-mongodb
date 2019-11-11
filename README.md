# springboot-testcontainer-mongodb

[![Maven Central](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/avides/springboot/testcontainer/springboot-testcontainer-mongodb/maven-metadata.xml.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.avides.springboot.testcontainer%22%20AND%20a%3A%22springboot-testcontainer-mongodb%22)
[![Build](https://github.com/springboot-testcontainer/springboot-testcontainer-mongodb/workflows/release/badge.svg)](https://github.com/springboot-testcontainer/springboot-testcontainer-mongodb/actions)
[![Nightly build](https://github.com/springboot-testcontainer/springboot-testcontainer-mongodb/workflows/nightly/badge.svg)](https://github.com/springboot-testcontainer/springboot-testcontainer-mongodb/actions)
[![Coverage report](https://sonarcloud.io/api/project_badges/measure?project=springboot-testcontainer_springboot-testcontainer-mongodb&metric=coverage)](https://sonarcloud.io/dashboard?id=springboot-testcontainer_springboot-testcontainer-mongodb)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=springboot-testcontainer_springboot-testcontainer-mongodb&metric=alert_status)](https://sonarcloud.io/dashboard?id=springboot-testcontainer_springboot-testcontainer-mongodb)
[![Technical dept](https://sonarcloud.io/api/project_badges/measure?project=springboot-testcontainer_springboot-testcontainer-mongodb&metric=sqale_index)](https://sonarcloud.io/dashboard?id=springboot-testcontainer_springboot-testcontainer-mongodb)

### Dependency
```xml
<dependency>
	<groupId>com.avides.springboot.testcontainer</groupId>
	<artifactId>springboot-testcontainer-mongodb</artifactId>
	<version>1.0.0-RC1</version>
	<scope>test</scope>
</dependency>
```

### Configuration
Properties consumed (in `bootstrap.properties`):
- `embedded.container.mongodb.enabled` (default is `true`)
- `embedded.container.mongodb.startup-timeout` (default is `30`)
- `embedded.container.mongodb.docker-image` (default is `mongo:4.0.13`)
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
<!-- Testcontainers -->
<logger name="com.github.dockerjava.jaxrs" level="WARN" />
<logger name="com.github.dockerjava.core.command" level="WARN" />
<logger name="org.apache.http" level="WARN" />
```

## Labels
The container exports multiple labels to analyze running testcontainers:
- `TESTCONTAINER_SERVICE=mongodb`
- `TESTCONTAINER_IMAGE=${embedded.container.mongodb.docker-image}`
- `TESTCONTAINER_STARTED=$currentTimestamp`
