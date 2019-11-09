package io.github.rbajek.rasa.action.server;

import io.github.rbajek.rasa.action.server.config.AppConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Rafał Bajek
 */
@SpringBootApplication
@Import({AppConfig.class})
public class RasaActionServer {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(RasaActionServer.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
