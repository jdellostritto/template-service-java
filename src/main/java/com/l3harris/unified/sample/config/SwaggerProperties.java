package com.l3harris.unified.sample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import springfox.documentation.service.ApiInfo;

import org.springframework.stereotype.Component;

/**
 * The Class SwaggerProperties.
 *
 * @author Jim.DelloStritto
 * @project template-service-java
 * @created Oct 17, 2020
 * @references
 * @credits
 */

@Component
@PropertySource({"classpath:locale-${UI_LOCALE:en}.yml"})
@ConfigurationProperties("ui.swagger.info")
@Data
public class SwaggerProperties {

  /** The Constant DEFAULT. */
  private static final ApiInfo DEFAULT = ApiInfo.DEFAULT;

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
