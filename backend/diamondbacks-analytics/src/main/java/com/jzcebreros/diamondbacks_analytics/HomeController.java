package com.jzcebreros.diamondbacks_analytics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Diamondbacks Analytics Platform is running!";
    }
}