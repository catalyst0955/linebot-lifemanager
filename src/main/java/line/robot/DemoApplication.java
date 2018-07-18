package line.robot;

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import line.robot.service.LineBotService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@SpringBootApplication
@LineMessageHandler
public class DemoApplication {

    static final String PATH = "https://life-manager-0520.herokuapp.com";
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        LineBotService lineBotService = applicationContext.getBean(LineBotService.class);
    }


}
