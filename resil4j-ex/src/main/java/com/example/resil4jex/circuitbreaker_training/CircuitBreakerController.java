package com.example.resil4jex.circuitbreaker_training;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cb")
@RequiredArgsConstructor
public class CircuitBreakerController {

    private final CircuitBreakerService circuitBreakerService;

    @GetMapping("/call/{param}")
    public String call(@PathVariable String param) {
        return circuitBreakerService.process(param);
    }
}
