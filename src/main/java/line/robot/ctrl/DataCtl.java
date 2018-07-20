package line.robot.ctrl;

import line.robot.model.LineBotModal;
import line.robot.service.DataService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

@Controller
public class DataCtl {

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/data")
    public String dataBackStage(HttpServletRequest request, ModelMap model) {
        model.put("root", request.getContextPath());
        return "index2";
    }

    @RequestMapping(value = "/dataApi", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String dataBackStageAPI(HttpServletRequest request, String startDate, String endDate) throws ParseException {

        return dataService.getDataByTime(startDate, endDate);
    }

    @RequestMapping(value = "/dataApi", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String dataAddApi(@RequestBody MultiValueMap<String, String> params) {

        try {
            dataService.addData(params.getFirst("payName"), params.getFirst("payValue"),
                    params.getFirst("command"), params.getFirst("firstClassify"), params.getFirst("secondClassify"),
                    params.getFirst("userId"), params.getFirst("createTime"));
        } catch (Exception e) {

            return null;
        }
        return "success";
    }

    @RequestMapping(value = "/dataApi", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String dataDeleteApi(@RequestBody MultiValueMap<String, String> params) {
        String serial = params.getFirst("serial");
        try {
            dataService.deleteData(serial);
        } catch (Exception e) {
            return null;
        }
        return "success";
    }


}
