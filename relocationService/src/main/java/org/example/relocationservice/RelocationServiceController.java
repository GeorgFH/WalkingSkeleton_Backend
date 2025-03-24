package org.example.relocationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public ResponseEntity<Map<String, String>> submitFormData(@RequestBody RelocationRequest request) {
        repository.save(request);
        System.out.println("Formulardaten erhalten: " + request.toString());

        // Rückgabe als JSON-Objekt
        Map<String, String> response = new HashMap<>();
        response.put("message", "Formular erfolgreich empfangen");
        return ResponseEntity.ok(response);
    }
    /**
     * Get endpoint that returns all data
     * @return all received data
     */
    @GetMapping("/requests")
    public ResponseEntity<List<RelocationRequest>> getAllRequests() {
        List<RelocationRequest> requests = repository.findAll();
        return ResponseEntity.ok(requests);
    }

}
