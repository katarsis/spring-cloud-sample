package ru.katarsis.recomendationservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.CollectionTable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

@EnableDiscoveryClient
@SpringBootApplication
public class RecomendationServiceApplication {

    @Bean
    CommandLineRunner runner(RecommendationRepository recommendationRepository) {
        return args -> {
            Arrays.asList("Piton, Peter, Antony".split(",")).forEach(x -> recommendationRepository.save(new Recommendation(x)));
            recommendationRepository.findAll().forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(RecomendationServiceApplication.class, args);
    }
}

@RefreshScope
@RestController
class RestApi {

    @Value("${message:Hello}")
    public String message;

    @RequestMapping(value = "/message")
    public String getMessage() {
        return message;
    }

}


@RepositoryRestResource
interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    @RestResource(path = "by-name")
    Collection<Recommendation> findRecommendationByName(@Param("rn") String rn);

}


@Entity
class Recommendation {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Recommendation() {
    }

    public Recommendation(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}