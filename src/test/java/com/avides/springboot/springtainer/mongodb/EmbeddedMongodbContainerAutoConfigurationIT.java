package com.avides.springboot.springtainer.mongodb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class EmbeddedMongodbContainerAutoConfigurationIT extends AbstractIT
{
    @Test
    public void testGeneratedProperties()
    {
        assertThat(environment.getProperty("embedded.container.mongodb.host")).isNotBlank();
        assertThat(environment.getProperty("embedded.container.mongodb.port")).isNotBlank();

        System.out.println();
        System.out.println("Resolved properties:");
        System.out.println("Host:      " + environment.getProperty("embedded.container.mongodb.host"));
        System.out.println("Port:      " + environment.getProperty("embedded.container.mongodb.port"));
        System.out.println();
    }

    @Test
    public void testCrud()
    {
        mongoTemplate.insert(new DummyDocument("key1", "value1"));
        assertEquals("value1", mongoTemplate.findById("key1", DummyDocument.class).getValue());

        mongoTemplate.save(new DummyDocument("key1", "value2"));
        assertEquals("value2", mongoTemplate.findById("key1", DummyDocument.class).getValue());

        mongoTemplate.remove(new DummyDocument("key1", "value2"));
        assertNull(mongoTemplate.findById("key1", DummyDocument.class));
    }

    @Configuration
    @EnableAutoConfiguration
    static class TestConfiguration
    {
        // nothing
    }

    @AllArgsConstructor
    @Setter
    @Getter
    public static class DummyDocument
    {
        @Id
        private String key;

        private String value;
    }
}
