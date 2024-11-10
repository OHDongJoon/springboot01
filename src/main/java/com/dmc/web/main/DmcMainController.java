package com.dmc.web.main;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DmcMainController {

    @GetMapping("/")
    public String index(@RequestParam(value = "errorMessage", required = false) String errorMessage,
                        Model model) {
        model.addAttribute("errorMessage", errorMessage);
        return "/main/index";
    }
}
