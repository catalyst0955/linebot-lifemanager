package line.robot.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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
