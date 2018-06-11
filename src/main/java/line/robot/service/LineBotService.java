package line.robot.service;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import line.robot.config.DataRepository;
import line.robot.model.LineBotModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.InputStreamReader;
import java.util.*;


@Component
public class LineBotService {

    @Autowired
    DataRepository dataRepository;

    Map<String, String> valueMap;
    Properties props;

    public void setValue () {
        Map<String, String> picMap = new HashMap<>();
        this.valueMap = picMap;

        Properties prop = new Properties();
        try{
            prop.load(new InputStreamReader(new ClassPathResource("/static/property/img.properties").getInputStream(),"UTF-8") );
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.props = prop;
        for(Object key : props.keySet()){
            Object value = props.get(key);
            valueMap.put(String.valueOf(key),String.valueOf(value));
        }

    }

    public String addValue(String key,String value,String userId){
        String result = "";
        if(value.startsWith("https://i.imgur.com")) {
            props.setProperty(key, value);
        }
        return result;
    }

    public String replyKeyList(MessageEvent<TextMessageContent> event){

        List<LineBotModal> modalList = dataRepository.findAll();
        StringBuilder sb  = new StringBuilder();
        sb.append("目前可以用的指令為: ");
        for(LineBotModal modal:modalList){
            sb.append(modal.getDataKey()).append(",").append(" ");
        }
        String keyList = sb.toString().substring(0,sb.length()-2);
        System.out.println(keyList);
        return keyList;
    }


    public String getPic(String key) {
        String result = "";
        LineBotModal modal = dataRepository.getOne(key);
        result = modal.getDataValue();
        return result;
    }


}
