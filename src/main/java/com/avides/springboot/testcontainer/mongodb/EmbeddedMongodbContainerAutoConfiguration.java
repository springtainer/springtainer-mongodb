package com.avides.springboot.testcontainer.mongodb;

import static com.avides.springboot.testcontainer.mongodb.MongodbProperties.BEAN_NAME;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import com.avides.springboot.testcontainer.common.container.AbstractBuildingEmbeddedContainer;
import com.avides.springboot.testcontainer.common.container.EmbeddedContainer;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

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

    public class MongodbContainer extends AbstractBuildingEmbeddedContainer<MongodbProperties>
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

        @Override
        protected boolean isContainerReady(MongodbProperties properties)
        {
            try (MongoClient mongoClient = new MongoClient(new ServerAddress(getContainerHost(), getContainerPort(properties.getPort()))))
            {
                return mongoClient.getDatabase("admin") != null;
            }
        }
    }
}
