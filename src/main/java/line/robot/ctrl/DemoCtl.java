package line.robot.ctrl;

import line.robot.config.DataRepository;
import line.robot.model.LineBotModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;


@Controller
public class DemoCtl {

    @Autowired
    DataSource dataSource;

    @Autowired
    DataRepository dataRepository;

    @RequestMapping("/init")
    public String test() {
        return "index";
    }

    @RequestMapping("/start")
    public String test2() {
        return "folder/index2";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCommand(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameterNames());
        for (String keys : request.getParameterMap().keySet()) {
            String value = request.getParameterMap().get(keys)[0];
            try {
                keys = new String(keys.getBytes("UTF-8"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            LineBotModal modal = new LineBotModal();
            modal.setDataKey(keys);
            modal.setDataValue(request.getParameterMap().get(keys)[0]);
            dataRepository.saveAndFlush(modal);
        }


        return "index";

    }

//    @RequestMapping(value = "/post", method = RequestMethod.POST)
//    public ResponseEntity receivePostAction(HttpServletRequest request, HttpServletResponse response) {
//
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        for(String key : parameterMap.keySet()){
//            String[] valueSet = parameterMap.get(key);
//            System.out.println( "KEY : " + key + "   VALUES:" + valueSet);
//        }
//
//        response.setStatus(HttpServletResponse.SC_OK);
//
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
