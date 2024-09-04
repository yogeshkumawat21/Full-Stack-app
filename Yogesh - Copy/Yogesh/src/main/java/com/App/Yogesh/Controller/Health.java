//HealthController responsible for performing application health checks to ensure the system is running smoothly and operational.

package com.App.Yogesh.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {
    @GetMapping("/health")
    public String hello()
    {
        return "ok";
    }
}
