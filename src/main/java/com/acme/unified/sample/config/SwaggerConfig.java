package com.acme.unified.sample.config;

import static java.util.Objects.requireNonNull;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * The Class SwaggerConfig.
 *
 * @author Jim.DelloStritto
 * @project template-service-java
 * @created Dec 28, 2020
 * @references
 * @credits
 */

@Configuration
@ConditionalOnProperty(value = "springdoc.swagger-ui.enabled", matchIfMissing = false)
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerConfig.class);

  /** The properties. */
  private SwaggerProperties props;

  /**
     * Instantiates a new swagger config.
     * @param swaggerProperties the swagger properties
     */
  @Autowired
  public SwaggerConfig(SwaggerProperties swaggerProperties) {
    this.props = requireNonNull(swaggerProperties);
    LOGGER.info("LOCALE PROPERTIES: {} ", this.props.toString());
  }

  @Bean
  public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
    return new OpenAPI().info(new Info()
      .contact(new Contact().name(props.getContactName())
                            .email(props.getContactEmail())
                            .url(props.getContactUrl())
      )
      .title(props.getTitle())
      .version(props.getVersion())
      .description(props.getDescription())
      .termsOfService(props.getTermsOfServiceUrl())
      .license(new License().name(props.getLicense())
                            .url(props.getLicenseUrl())
      )
    );
}

}

