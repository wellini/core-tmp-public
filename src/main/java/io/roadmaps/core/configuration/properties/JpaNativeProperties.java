package io.roadmaps.core.configuration.properties;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "spring.jpa")
public class JpaNativeProperties {

    @NotNull
    @Setter
    private Map<String, String> properties = new HashMap<>();

    public Properties getProperties() {
        Properties properties = new Properties(this.properties.size());
        properties.putAll(this.properties);
        return properties;
    }
}
