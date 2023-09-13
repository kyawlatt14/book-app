package net.kkl.bookapp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
	private static final String MAPPING_IMAGE="/image/**";
	private static final String MAPPING_PDF="/pdf/**";
	private static final String PREFIX_LOCAL="file:///home/test/";
//	private static final String PREFIX_SERVER="file:/home/test/";
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(MAPPING_IMAGE).addResourceLocations(PREFIX_LOCAL).setCachePeriod(36000);
		registry.addResourceHandler(MAPPING_PDF).addResourceLocations(PREFIX_LOCAL).setCachePeriod(36000);
	}

}
