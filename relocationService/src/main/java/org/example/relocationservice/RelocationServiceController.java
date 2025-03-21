package org.example.relocationservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelocationServiceController {


    /**
     * GET endpoint to check the health of the service
     * @return a string message indicating the health status
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "Service is healthy";
    }
}
