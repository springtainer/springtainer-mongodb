springboot-testcontainer-mongodb
================================

[![Maven Central](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/avides/springboot/testcontainer/springboot-testcontainer-mongodb/maven-metadata.xml.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.avides.springboot.testcontainer%22%20AND%20a%3A%22springboot-testcontainer-mongodb%22)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5e0661e8bfe04eb28e66015aa0611483)](https://www.codacy.com/app/avides-builds/springboot-testcontainer-mongodb?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=springboot-testcontainer/springboot-testcontainer-mongodb&amp;utm_campaign=Badge_Grade)
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
