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

    @Autowired
    private static LineBotService lineBotService;

    public static void main(String[] args) {
        Map<String,String> picMap = new HashMap<>();
        picMap.put("南非召喚","https://i.imgur.com/BsOco1k.gifv");
        picMap.put("羈押","https://i.imgur.com/ac0BUWS.jpg");
        picMap.put("錢包君1","https://i.imgur.com/WtNrmCi.jpg");
        picMap.put("賣萌","https://i.imgur.com/x17YqKi.jpg");
        picMap.put("裝傻","https://i.imgur.com/cy1Gdq0.jpg");
        picMap.put("什麼都沒有","https://i.imgur.com/mCRGPgW.jpg");
        picMap.put("萌新三連","https://i.imgur.com/SAPBVzy.jpg");
        picMap.put("萌新","https://i.imgur.com/XiPQKCR.jpg");

        lineBotService.setValue(picMap);


        SpringApplication.run(DemoApplication.class, args);

    }




}
