package br.com.martins.service;


import br.com.martins.connnector.Connector;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "businessBService")
public class BusinessBService implements BusinessService  {

    private final Connector backendBConnector;
    private final CircuitBreaker circuitBreaker;

    public BusinessBService(@Qualifier("backendBConnector") Connector backendBConnector,
                            CircuitBreakerRegistry circuitBreakerRegistry){
        this.backendBConnector = backendBConnector;
        circuitBreaker = circuitBreakerRegistry.circuitBreaker("backendB");
    }

    public String failure() {
        return CircuitBreaker.decorateSupplier(circuitBreaker, backendBConnector::failure).get();
    }

    public String success() {
        return CircuitBreaker.decorateSupplier(circuitBreaker, backendBConnector::success).get();
    }

    @Override
    public String ignore() {
        return CircuitBreaker.decorateSupplier(circuitBreaker, backendBConnector::ignoreException).get();
    }
    @Override
    public String failureWithFallback() {
        return backendBConnector.failureWithFallback();
    }
}
