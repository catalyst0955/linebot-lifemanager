package line.robot.demo;

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@SpringBootApplication
@LineMessageHandler
public class DemoApplication {

    static final String PATH = "https://triple-bon.herokuapp.com";
    public static Path deadPoolPath;
    public static void main(String[] args) throws IOException {
        deadPoolPath = Files.createTempDirectory("line-bot");
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        LineBotService lineBotService = applicationContext.getBean(LineBotService.class);



        lineBotService.setValue();

    }


}
