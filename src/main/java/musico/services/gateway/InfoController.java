package musico.services.gateway;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import java.security.Principal;

@RestController
public class InfoController {
    private static final Logger log = LoggerFactory.getLogger(InfoController.class);
    @GetMapping("/session")
    @PreAuthorize("hasAuthority('OIDC_USER')")
    public Flux<String> info(Principal principal, ServerWebExchange session) {
        log.info(principal.getName());
        String cookie =  session.getRequest().getHeaders().getFirst("Cookie") != null ? session.getRequest().getHeaders().getFirst("Cookie") : "No cookie";
        return Flux.just(cookie);
    }
}
