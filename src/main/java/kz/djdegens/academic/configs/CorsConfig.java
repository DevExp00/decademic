package kz.djdegens.academic.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // Allow sending requests with credentials from any origin
        config.setAllowCredentials(true);
        // Allow all methods and headers
        /*config.addAllowedOriginPattern("https://cab-dashboard.vercel.app");*/
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        // Add CORS configuration for all URLs
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
