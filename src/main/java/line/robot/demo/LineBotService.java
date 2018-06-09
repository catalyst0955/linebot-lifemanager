package line.robot.demo;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LineBotService {
    Map<String, String> valueMap;

    LineBotService() {
        Map<String, String> picMap = new HashMap<>();
        picMap.put("南非召喚", "https://i.imgur.com/BsOco1k.gifv");
        picMap.put("羈押", "https://i.imgur.com/ac0BUWS.jpg");
        picMap.put("錢包君1", "https://i.imgur.com/WtNrmCi.jpg");
        picMap.put("賣萌", "https://i.imgur.com/x17YqKi.jpg");
        picMap.put("裝傻", "https://i.imgur.com/cy1Gdq0.jpg");
        picMap.put("什麼都沒有", "https://i.imgur.com/mCRGPgW.jpg");
        picMap.put("萌新三連", "https://i.imgur.com/SAPBVzy.jpg");
        picMap.put("萌新", "https://i.imgur.com/XiPQKCR.jpg");
        this.valueMap = picMap;


    }


    public String getPic(String key) {
        return valueMap.get(key);
    }

}
