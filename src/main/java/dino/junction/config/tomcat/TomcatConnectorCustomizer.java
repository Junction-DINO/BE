package dino.junction.config.tomcat;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConnectorCustomizer {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return (factory) -> factory.addConnectorCustomizers(connector -> {
            connector.setScheme("http");
            connector.setProxyPort(443);  // 로드 밸런서에서 HTTPS로 수신
            connector.setSecure(false);
        });
    }
}
