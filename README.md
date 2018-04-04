springboot-testcontainer-mongodb
================================

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.avides.springboot.testcontainer/springboot-testcontainer-mongodb/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.avides.springboot.testcontainer/springboot-testcontainer-mongodb)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/xxx)](https://www.codacy.com/app/springboot-testcontainer/springboot-testcontainer-mongodb)
[![Coverage Status](https://coveralls.io/repos/springboot-testcontainer/springboot-testcontainer-mongodb/badge.svg)](https://coveralls.io/r/springboot-testcontainer/springboot-testcontainer-mongodb)
[![Build Status](https://travis-ci.org/springboot-testcontainer/springboot-testcontainer-mongodb.svg?branch=master)](https://travis-ci.org/springboot-testcontainer/springboot-testcontainer-mongodb)

### Dependency
```xml
<dependency>
	<groupId>com.avides.springboot.testcontainer</groupId>
	<artifactId>springboot-testcontainer-mongodb</artifactId>
	<version>0.1.0-RC2</version>
	<scope>test</scope>
</dependency>
```

### Configuration
Properties consumed (in `bootstrap.properties`):
- `embedded.container.mongodb.enabled` (default is `true`)
- `embedded.container.mongodb.startup-timeout` (default is `30`)
- `embedded.container.mongodb.docker-image` (default is `mongo:3.4.13`)
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
