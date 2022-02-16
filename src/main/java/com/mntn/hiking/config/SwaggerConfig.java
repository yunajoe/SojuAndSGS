package com.mntn.hiking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2  
public class SwaggerConfig {
	
    @Bean
    public Docket swaggerApi() {
    	
    	//Docket : Swagger 설정의 핵심이 되는 Bean
    	//ignoredParameterTypes(ApiIgnore.class) : 특정 parameter만 무시하고자 할 경우 적용
    	//예시 : (@ApiIgnore HttpSession session)
    	//apiInfo() : 제목, 설명 등 문서에 대한 정보들을 Swagger UI 로 노출할 정보
    	//apis() : api 스펙이 작성되어 있는 패키지 및 서브패키지 (Controller) 를 지정하여, 애노테이션이 선언된 API를 문서화
    	//useDefaultResponseMessages(false) : false로 설정한 경우 컨트롤러에 
    	//@ApiResponse로 선언된 status에 한해서만 문서에 표현, true로 설정한 경우 401/403 등의 status 값도 문서에 표현됨
        return new Docket(DocumentationType.SWAGGER_2).ignoredParameterTypes(ApiIgnore.class)
        		.apiInfo(swaggerInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.mntn.hiking.controller"))
                .build();
                //.useDefaultResponseMessages(false); 
    }
    

    /* 1. 메소드명은 자유롭게 단, swagger doc intro 부분의 값들을 설정
     * 2. ApiInfo 객체에 필요한 정보 저장해서 반환
     * 3. ApiInfo 객체는 ApiInfoBuilder() 통해서 다양한 설정이 개별 메소드 호출로 가능
     * 	- builder pattern 적용
     */
    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("API Doc 입니다")
                .description("Swagger Doc 학습을 위한 기본 문서 작성중 ")
                .license("license : playdata").licenseUrl("http://playdata.io/").build();
    }
    
}
