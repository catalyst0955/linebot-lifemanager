package line.robot.demo;

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@LineMessageHandler
public class DemoApplication {




    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext=SpringApplication.run(DemoApplication.class, args);
        LineBotService lineBotService = applicationContext.getBean(LineBotService.class);
        lineBotService.setValue();

    }




}
