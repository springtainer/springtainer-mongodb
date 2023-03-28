package com.avides.springboot.springtainer.mongodb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MongodbPropertiesTest
{
    @Test
    public void testDefaults()
    {
        var properties = new MongodbProperties();
        assertTrue(properties.isEnabled());
        assertEquals(30, properties.getStartupTimeout());
        assertEquals("mongo:4.2.24", properties.getDockerImage());

        assertEquals(27017, properties.getPort());
    }
}
