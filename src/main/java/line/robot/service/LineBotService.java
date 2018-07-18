package line.robot.service;

import line.robot.config.DataRepository;
import line.robot.model.LineBotModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
