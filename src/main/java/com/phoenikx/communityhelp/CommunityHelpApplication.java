package com.phoenikx.communityhelp;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class CommunityHelpApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityHelpApplication.class, args);
    }

	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error")))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails(){
		return new ApiInfo(
				"Communitiy Help API",
				"All API collections for community Help",
				"1.0",
				"Free to Use",
				new Contact("Community Help Team","https://github.com/phoenikx/community-help/","nikhil.ranjan@harness.io"),
				"API License",
				"http://phoenikx.github.io/",
				Collections.emptyList());
	}
}
