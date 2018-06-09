package line.robot.demo;

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@SpringBootApplication
@LineMessageHandler
public class DemoApplication {

    static final String PATH = "https://triple-bon.herokuapp.com/post";
    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        LineBotService lineBotService = applicationContext.getBean(LineBotService.class);

        lineBotService.setValue(PATH);

    }


}
