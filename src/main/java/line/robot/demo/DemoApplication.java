package line.robot.demo;

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@LineMessageHandler
public class DemoApplication {



    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

    }




}
