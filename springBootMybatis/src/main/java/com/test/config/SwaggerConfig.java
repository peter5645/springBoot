package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {


  /**
   * Custom implementation.
   * 
   * @return the docket
   */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.controller")) // controller 目錄
                .paths(PathSelectors.any())
                .build();
    }
  private ApiInfo apiInfo(){
//      ApiInfo apiInfo = new ApiInfo(
//              "設定title",
//              "description",
//              "version 1.0",
//              null,
//              "Hong",
//              "Apache 2.0",
//              "http://www.apache.org/licenses/LICENSE-2.0"
//              );
//      return apiInfo;
	  return new ApiInfoBuilder()
			  .title("設定title")
			  .description("description")
			  .contact("Hong")
			  .version("1.0")
			  .build();
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }
}
