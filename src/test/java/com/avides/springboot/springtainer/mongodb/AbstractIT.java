package com.avides.springboot.springtainer.mongodb;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.dockerjava.api.DockerClient;
import com.avides.springboot.springtainer.common.util.DockerClients;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = { "spring.data.mongodb.uri=mongodb://${embedded.container.mongodb.host}:${embedded.container.mongodb.port}/test" })
@DirtiesContext
public abstract class AbstractIT
{
    protected DockerClient dockerClient = DockerClients.build();

    @Autowired
    protected ConfigurableEnvironment environment;

    @Autowired
    protected MongoTemplate mongoTemplate;
}
