package ru.katarsis.recomendationclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rec")
public class RecomendationApiController {

    @Autowired
    private RestTemplate restTemplate;

    public Collection<String> fallback() {
        return Collections.emptyList();
    }


    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/names")
    public Collection<String> getRecomendatinos() {
        ParameterizedTypeReference<Resources<Recommendation>> ptr = new ParameterizedTypeReference<Resources<Recommendation>>() {
        };
        ResponseEntity<Resources<Recommendation>> responseEntity = this.restTemplate.exchange("http://recommendation-server/recommendations",
                HttpMethod.GET, null, ptr);
        return responseEntity.getBody().getContent().stream().map(Recommendation::getName).collect(Collectors.toList());
    }


}
