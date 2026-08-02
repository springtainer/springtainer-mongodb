package com.avides.springboot.springtainer.mongodb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MongodbPropertiesTest
{
    @Test
    public void testDefaults()
    {
        var properties = new MongodbProperties();
        assertTrue(properties.isEnabled());
        assertEquals(30, properties.getStartupTimeout());
        assertEquals("mongo:8.2.12", properties.getDockerImage());

        assertEquals(27017, properties.getPort());
    }
}
