package line.robot.demo;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LineBotService {
    Map<String, String> valueMap;

    public void setValue () {
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
        String result = "";
        if(valueMap.containsKey(key)){
            result = valueMap.get(key);
        }
        return result;
    }

    public String getKeySet(){
        return valueMap.keySet().toString();
    }

}
