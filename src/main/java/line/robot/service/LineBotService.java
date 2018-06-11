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


    public String addValue(String key, String value, String userId) {
        String result = "";
        if (value.startsWith("https://i.imgur.com")) {
            try {
                LineBotModal modal = new LineBotModal();
                modal.setDataValue(value);
                modal.setDataKey(new String(key.getBytes("UTF-8"), "UTF-8"));
                modal.setUserId(userId);
                dataRepository.saveAndFlush(modal);
            } catch (Exception e) {
                e.printStackTrace();
                result = "資料庫存取失敗，請稍後再試";
            }

        } else {
            result = "圖片資料格式不符，請取得Imgur的正確網址";
        }
        return result;
    }

    public String replyKeyList(MessageEvent<TextMessageContent> event) {

        List<LineBotModal> modalList = dataRepository.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("目前可以用的指令為: ");
        for (LineBotModal modal : modalList) {
            sb.append(modal.getDataKey()).append(",").append(" ");
        }
        String keyList = sb.toString().substring(0, sb.length() - 2);
        System.out.println(keyList);
        return keyList;
    }


    public String getPic(String key) {
        String result = "";
        try {
            LineBotModal modal = dataRepository.getOne(key);
            result = modal.getDataValue();
        } catch (Exception e) {

        }
        System.out.println(result);
        return result;
    }


}
