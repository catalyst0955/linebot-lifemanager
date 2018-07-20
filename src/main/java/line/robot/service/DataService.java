package line.robot.service;

import line.robot.config.DataRepository;
import line.robot.model.LineBotModal;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DataService {

    @Autowired
    DataRepository dataRepository;


    public String getDataByTime(String startDate,String endDate) throws ParseException {
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");

        Date start = fmt.parse(startDate);
        Date end = fmt.parse(endDate);

//        List<LineBotModal> result = dataRepository.findAllByCreateTimeBeforeAndCreateTimeAfter(end,start);
        List<LineBotModal> result = dataRepository.findAllByCreateTimeBetween(start,end);
        JSONArray ary = new JSONArray();
        JSONObject returnValue = new JSONObject();
        for(LineBotModal modal : result){
            String date = fmt.format(modal.getCreateTime());
            JSONObject obj = JSONObject.fromObject(modal);
            obj.put("createTime",date);
            ary.add(obj);
        }
        returnValue.put("data",ary);

        return returnValue.toString();
    }

    public void addData(String payName,String payValue,String command,String firstClassify,String secondClassify,String userId,String createTime) throws ParseException {
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");

        LineBotModal modal = new LineBotModal();
        modal.setUserId(userId);
        modal.setCommand(command);
        modal.setPayValue(payValue);
        modal.setPayName(payName);
        modal.setFirstClassify(firstClassify);
        modal.setSecondClassify(secondClassify);
        modal.setCreateTime(fmt.parse(createTime));
        dataRepository.saveAndFlush(modal);
    }

    public void deleteData(String serial){
        dataRepository.deleteBySerial(Integer.valueOf(serial));
//        dataRepository.deleteById(Integer.valueOf(serial));
    }
}
