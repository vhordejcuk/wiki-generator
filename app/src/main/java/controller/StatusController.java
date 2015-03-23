package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatusController {
    @RequestMapping("/")
    @ResponseBody
    public String status() {
        return "Generator is OK.";
    }
}
