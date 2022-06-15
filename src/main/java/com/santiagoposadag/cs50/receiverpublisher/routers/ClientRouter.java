package com.santiagoposadag.cs50.receiverpublisher.routers;

import com.santiagoposadag.cs50.receiverpublisher.dto.ClientDTO;
import com.santiagoposadag.cs50.receiverpublisher.dto.CryptoCurrencyDto;
import com.santiagoposadag.cs50.receiverpublisher.usecases.PostClientToRabbitUseCase;
import com.santiagoposadag.cs50.receiverpublisher.usecases.PostMessageToRabbitUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ClientRouter {


    @Bean
    public RouterFunction<ServerResponse> postActionRoute(PostClientToRabbitUseCase post){
        Function<ClientDTO, Mono<ServerResponse>> executor =
                clientDTO -> post.apply(clientDTO)
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result));

        return route(POST("/SendAction")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ClientDTO.class).flatMap(executor));

    }
}
