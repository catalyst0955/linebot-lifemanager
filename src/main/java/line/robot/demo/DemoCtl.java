package line.robot.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public class DemoCtl {

    @RequestMapping("/init")
    public String test() {
        return "index";
    }

    @RequestMapping("/start")
    public String test2() {
        return "folder/index2";
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
