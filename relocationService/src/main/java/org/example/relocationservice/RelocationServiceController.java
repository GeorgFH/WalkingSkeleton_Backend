package org.example.relocationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelocationServiceController {

    @Autowired
    private RelocationRequestRepository repository;


    /**
     * GET endpoint to check the health of the service
     * @return a string message indicating the health status
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "Service is healthy";
    }


    /**
     * POST endpoint to receive form data
     * @param request The form data sent as JSON
     * @return confirmation message if the data is successfully received
     */
    @PostMapping("/submit")
    public String submitFormData(@RequestBody RelocationRequest request) {
        repository.save(request);
        System.out.println("Formulardaten erhalten: " + request.toString());

        return "Formular erfolgreich empfangen";
    }
}
