package line.robot.demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class LineBotService {
    Map<String, String> valueMap;

    public void setValue () {
        Map<String, String> picMap = new HashMap<>();
        picMap.put("南非召喚", "https://i.imgur.com/BsOco1k.gif");
        picMap.put("羈押", "https://i.imgur.com/ac0BUWS.jpg");
        picMap.put("錢包君1", "https://i.imgur.com/WtNrmCi.jpg");
        picMap.put("賣萌", "https://i.imgur.com/x17YqKi.jpg");
        picMap.put("裝傻", "https://i.imgur.com/cy1Gdq0.jpg");
        picMap.put("什麼都沒有", "https://i.imgur.com/mCRGPgW.jpg");
        picMap.put("萌新三連", "https://i.imgur.com/SAPBVzy.jpg");
        picMap.put("萌新", "https://i.imgur.com/XiPQKCR.jpg");
        picMap.put("絕望", "https://i.imgur.com/M9h7HWV.jpg");
        picMap.put("牛逼", "https://i.imgur.com/NBRRTfl.jpg");
        picMap.put("鹹魚", "https://i.imgur.com/uUMmC5Q.jpg");
        picMap.put("燒", "https://i.imgur.com/5LbIIRv.jpg");
        picMap.put("大佬", "https://i.imgur.com/Ci5qJdB.jpg");
        this.valueMap = picMap;

        Properties prop = new Properties();
        try{
            prop.load(new InputStreamReader(new ClassPathResource("/static/property/img.properties").getInputStream(),"UTF-8") );
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Object key : prop.keySet()){
            Object value = prop.get(key);
            System.out.println("Keys:" + key.toString() + "     value:" + value.toString());
        }

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
