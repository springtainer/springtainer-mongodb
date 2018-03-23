package com.avides.springboot.testcontainer.mongodb;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MongodbPropertiesTest
{
    @Test
    public void testDefaults()
    {
        MongodbProperties properties = new MongodbProperties();
        assertTrue(properties.isEnabled());
        assertEquals(30, properties.getStartupTimeout());
        assertEquals("mongo:3.4.13", properties.getDockerImage());

        assertEquals(27017, properties.getPort());
    }
}
