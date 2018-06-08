package line.robot.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DemoCtl {

    @RequestMapping("/init")
    public String test(){
        return "index";
    }
}
