package tomash.project.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RestController
public class MoviesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}

	@GetMapping("/")
	public String apiRoot(){
		return "Hello, World! Shma israel";
	}

	// Global CORS configuration
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Apply to all endpoints
						.allowedOrigins("http://localhost:3000") // Frontend URL (React)
						.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Allow specific HTTP methods
						.allowedHeaders("*") // Allow all headers
						.allowCredentials(true); // Allow cookies (if needed)
			}
		};
	}
}
