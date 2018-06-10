package line.robot.demo;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LineBotWebMvcConfigurer extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String deadPoolPath = DemoApplication.deadPoolPath.toUri().toASCIIString();
        registry.addResourceHandler("/deadpool/**").addResourceLocations(deadPoolPath);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/prop/**").addResourceLocations("classpath:/static/property/");
    }
}
