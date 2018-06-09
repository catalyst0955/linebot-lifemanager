package line.robot.demo;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LineBotService {
     Map<String,String> valueMap;

    public void setValue(Map<String,String>valueMap){
        this.valueMap = valueMap;
    }
    public String getPic(String key){
        return valueMap.get(key);
    }

}
