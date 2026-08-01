package com.avides.springboot.springtainer.mongodb;

import static com.avides.springboot.springtainer.mongodb.MongodbProperties.BEAN_NAME;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import com.avides.springboot.springtainer.common.container.AbstractBuildingEmbeddedContainer;
import com.avides.springboot.springtainer.common.container.EmbeddedContainer;
import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@ConditionalOnProperty(name = "embedded.container.mongodb.enabled", matchIfMissing = true)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(MongodbProperties.class)
public class EmbeddedMongodbContainerAutoConfiguration
{
    @ConditionalOnMissingBean(MongodbContainer.class)
    @Bean(BEAN_NAME)
    public EmbeddedContainer mongodbContainer(ConfigurableEnvironment environment, MongodbProperties properties)
    {
        return new MongodbContainer("mongodb", environment, properties);
    }

    public static class MongodbContainer extends AbstractBuildingEmbeddedContainer<MongodbProperties>
    {
        public MongodbContainer(String service, ConfigurableEnvironment environment, MongodbProperties properties)
        {
            super(service, environment, properties);
        }

        @Override
        protected Map<String, Object> providedProperties()
        {
            Map<String, Object> provided = new HashMap<>();
            provided.put("embedded.container.mongodb.host", getContainerHost());
            provided.put("embedded.container.mongodb.port", Integer.valueOf(getContainerPort(properties.getPort())));
            return provided;
        }

        /**
         * Sends a ping, because everything up to and including {@code getDatabase} is resolved client-side and would report a container ready that never
         * came up. The server selection timeout is kept well below the startup timeout so a failed attempt leaves room to retry.
         */
        @Override
        protected boolean isContainerReady(MongodbProperties properties)
        {
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString("mongodb://" + getContainerHost() + ":" + getContainerPort(properties.getPort())))
                    .applyToClusterSettings(builder -> builder.serverSelectionTimeout(2, TimeUnit.SECONDS))
                    .build();

            try (MongoClient mongoClient = MongoClients.create(settings))
            {
                mongoClient.getDatabase("admin").runCommand(new Document("ping", Integer.valueOf(1)));
                return true;
            }
        }
    }
}
