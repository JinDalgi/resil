package com.example.resil4jex.retry_training;

import com.example.resil4jex.exception.IgnoreException;
import com.example.resil4jex.exception.RetryException;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
public class RetryService {

    // retry에 관련된 config 파일 이름을 지정하는 상수
    private static final String CONFIG_CLASS_NAME = "retryConfig";
    
    
    // Retry 애너테이션이 붙은 메서드가 재시도 로직
    // 재시도를 구체적으로 어떻게 할 지는 CONFIG_CALSS_NAME에 적힌 Config 클래스로 등록된 빈을 통해 설정
    // fallbackMethod에 적은 메서드가 바로 재시도를 했는데도 문제가 발생하면 호출해줄 메서드의 시그니처 등록
    @Retry(name = CONFIG_CLASS_NAME, fallbackMethod = "fallback")
    public String process(String param) {
        return callAnotherService(param);
    }

    private String fallback(String param, Exception e) {
        // retry에서 지정한 횟수를 모두 채우면 fallback 호출
        System.out.println(param + "요청을 처리하려고 했지만 실패 한도까지 실패했습니다.");
        return "처리됨 : " + e.toString();
    }

    private String callAnotherService(String param) {
        // retry exception은 retry 된다.
//        throw new RetryException("재시도 해주는 예외 발생!!!");
        // ignore exception은 retry를 하지 않는다.
        throw new IgnoreException("재시도 안해주는 예외 발생!!!");
    }
    
}
