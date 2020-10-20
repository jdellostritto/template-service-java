package com.l3harris.unified.sample.config;

import static java.util.Objects.requireNonNull;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * The Class SwaggerConfig.
 *
 * @author Jim.DelloStritto
 * @project template-service-java
 * @created Oct 17, 2020
 * @references
 * @credits
 */

@Configuration
@ConditionalOnProperty(value = "ui.swagger.enabled", matchIfMissing = true)
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerConfig.class);

  /** The properties. */
  private SwaggerProperties properties;

  /**
     * Instantiates a new swagger config.
     *
     * @param swaggerProperties the swagger properties
     */
  @Autowired
  public SwaggerConfig(SwaggerProperties swaggerProperties) {
    this.properties = requireNonNull(swaggerProperties);
    LOGGER.info("LOCALE PROPERTIES: {} ", this.properties.toString());
  }

  /**
   * Api info.
   *
   * @return the api info
   */
  @Bean
  public ApiInfo apiInfo() {
    return toApiInfo(properties);
  }

  /**
   * To api info.
   *
   * @param props the props
   * @return the api info
   */
  private ApiInfo toApiInfo(SwaggerProperties p) {
    String version = Optional.ofNullable(this.getClass()).map(Class::getPackage)
        .map(Package::getImplementationVersion).orElse(p.getVersion());
          
      
    return new ApiInfoBuilder()
   			.title(p.getTitle())
   			.version(version)
   		  .termsOfServiceUrl(p.getTermsOfServiceUrl())
   			.description(p.getDescription())
   			.contact(new Contact(p.getContactName(), p.getContactUrl(), p.getContactEmail()))
        .license(p.getLicense())
        .licenseUrl(p.getLicenseUrl())
        .build();
    }

  /**
   * Builds the api.
   *
   * @return the docket
   */
  @Bean
  public Docket buildApi() {

    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
        .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.l3harris.unified.sample.web"))
        .paths(PathSelectors.any()).build();
    
  }

}

