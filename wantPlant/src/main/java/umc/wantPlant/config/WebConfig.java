package umc.wantPlant.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final List<String> allowOriginUrlPatterns;

    public WebConfig(@Value("${cors.allow-origin.urls}") String allowOriginUrlPatterns) {
        this.allowOriginUrlPatterns = Stream.of(allowOriginUrlPatterns.split(","))
                .map(String::strip)
                .collect(Collectors.toList());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .exposedHeaders(HttpHeaders.LOCATION)
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true)
                .allowedOriginPatterns(allowOriginUrlPatterns.toArray(new String[0]))
                .maxAge(3600);
    }
}
