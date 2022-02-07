package com.avides.springboot.springtainer.mongodb;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.avides.springboot.springtainer.common.container.AbstractEmbeddedContainerProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigurationProperties("embedded.container.mongodb")
@Getter
@Setter
@ToString(callSuper = true)
public class MongodbProperties extends AbstractEmbeddedContainerProperties
{
    public static final String BEAN_NAME = "embeddedMongodbContainer";

    private int port = 27017;

    public MongodbProperties()
    {
        setDockerImage("mongo:4.0.28");
    }
}
