package line.robot.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping(value = "/data")
public class DataCtl {

    @RequestMapping(value = "/data")
    public String dataBackStage(HttpServletRequest request, ModelMap model) {
        model.put("root",request.getContextPath());
        return "index2";
    }

    @RequestMapping(value="/dataApi", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String dataBackStageAPI(HttpServletRequest request, String startDate, String endDate){

        return "";
    }


}
