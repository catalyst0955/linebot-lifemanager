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


    public String getDataByTime(String beforTime,String AfterTime) throws ParseException {
        DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");

        Date beforeDate = fmt.parse(beforTime);
        Date afterDate = fmt.parse(AfterTime);

        List<LineBotModal> result = dataRepository.findAllByCreateTimeBeforeAndCreateTimeAfter(afterDate,beforeDate);
        JSONArray ary = new JSONArray();
        JSONObject returnValue = new JSONObject();
        for(LineBotModal modal : result){
            JSONObject obj = JSONObject.fromObject(modal);
            ary.add(obj);
        }
        returnValue.put("data",ary);

        return returnValue.toString();
    }

}
