package line.robot.service;

import line.robot.config.DataRepository;
import line.robot.model.LineBotModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class LineBotService {

    @Autowired
    DataRepository dataRepository;


    public String addValue(String[] strList,String userId) {
        String result = "";

        if(!strList[0].equals("記帳")){
            result = "系統錯誤";
        }else{
            LineBotModal modal = new LineBotModal();
            //DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
            //String date = fmt.format(new Date());
            Date d = new Date();
//            try {
//                 d = fmt.parse(date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//                d = new Date();
//            }

            if(userId.equals("Ufd45c49f9bd95094e8a01c4d11baefde") ){
                userId="Alexander";
            }else if(userId.equals("U8698476c2181ff370b4a56fe6b5ac3cf")){
                userId = "Dory";
            }
            int length = strList.length;
            switch (length){
                case 6:
                    modal.setFirstClassify(strList[5]);
                case 5:
                    modal.setSecondClassify(strList[4]);
                case 4:
                    modal.setCommand(strList[3]);
                case 3:
                    modal.setPayName(strList[1]);
                    modal.setPayValue(strList[2]);
                    modal.setUserId(userId);
                    modal.setCreateTime(d);
                    break;
                case 2:
                case 1:
                    result = "輸入格式錯誤，請至少輸入{記帳 名稱 金額}";
                    break;
            }
            dataRepository.saveAndFlush(modal);
            result = "記帳完成";
        }
        return result;
    }

    public String howToUse(){
        return "使用格式 {記帳 名稱 金額 備註(選) 粗分類(選) 細分類(選)}";
    }





}
