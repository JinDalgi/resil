package com.example.resil4jex.circuitbreaker_training;

import com.example.resil4jex.exception.IgnoreException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class CircuitBreakerService {

    private String callFeignClient(String param) {
        if("a".equals(param))
            throw new RuntimeException("A 유형 예외 발생");
        else if("b".equals(param))
            throw new IgnoreException("B 유형 예외 발생");

        return param;
    }

    @CircuitBreaker(name = "CIRCUITBREAKER_CONFIG", fallbackMethod = "fallback")
    public String process(String param) {
        return "";
    }

    private String fallback(String param, Exception e) {
        // circuit breaker의 fallback은 IgnoreException에 의해서도 실행됩니다.
        // 다만, IgnoreException에 등록된 유형의 예외를 통해서는 open상태로의 전환은 이루어지지 않음.
        System.out.println(param + "파라미터가 예외를 유발했습니다.");
        return "예외 종류 : " + e.toString();
    }
}
