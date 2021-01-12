package com.acme.unified.sample.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * The Class SwaggerProperties.
 *
 * @author Jim.DelloStritto
 * @project template-service-java
 * @created Oct 17, 2020
 * @references
 * @credits
 */

@Configuration
@ConditionalOnProperty(value = "springdoc.swagger-ui.enabled", matchIfMissing = false)
@ConfigurationProperties(prefix = "swagger.info")
@PropertySource(value = "classpath:locale-${SPRING_PROFILES_LOCALE:en}.yml", factory = YamlPropertySourceFactory.class)
@Data
public class SwaggerProperties {

  /** The version. */
  private String version;

  /** The title. */
  private String title;

  /** The description. */
  private String description;

  /** The terms of service url. */
  private String termsOfServiceUrl;

  /** The license. */
  private String license;

  /** The license url. */
  private String licenseUrl;

  /** The contact name. */
  private String contactName;

  /** The contact url. */
  private String contactUrl;

  /** The contact email. */
  private String contactEmail;
  
  
}
