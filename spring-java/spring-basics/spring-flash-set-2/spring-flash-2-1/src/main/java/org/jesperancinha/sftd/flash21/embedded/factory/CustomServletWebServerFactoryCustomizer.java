package org.jesperancinha.sftd.flash21.embedded.factory;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomServletWebServerFactoryCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    /**
     * Customizes a server configuration for the purposes of this exercise.
     * Namely, it changes the server port to 9000 if the configured port is not 0
     * 0 is a port that is used when in a post configuration phase, we want to randomly assign a new port
     * 8081 will be converted to 9000
     * 8082 will be converted to 9001
     *
     * @param factory {@link ConfigurableServletWebServerFactory}
     */
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        final int port = ((TomcatServletWebServerFactory) factory).getPort();
        ConsolerizerComposer.outSpace()
                .brightBlue("Starting Setting up Configurable Embedded Server")
                .orange("We override the normal Spring configuration this way.")
                .none()
                .yellow("Running with port").orange(port)
                .yellow("and uri encoding of").orange(((TomcatServletWebServerFactory) factory).getUriEncoding())
                .yellow(".")
                .newLine()
                .brightBlue("We will now change the Tomcat Server configuration")
                .newLine()
                .reset();


        switch (port) {
            case -1:
                factory.setPort(9003);
                break;
            case 8081:
                factory.setPort(9000);
                break;
            case 8082:
                factory.setPort(9001);
        }

        factory.setDisplayName("Testing ConfigurableServletWebServerFactory");
        factory.setServerHeader("ConfigurableServletWebServerFactory Header");
        factory.setContextPath("/api");
        ConsolerizerComposer.outSpace()
                .orange("This is our factory -> %s", factory)
                .brightBlue("Finishing Setting up Configurable Embedded Server")
                .brightBlue("Now we have:")
                .none()
                .yellow("Running with port").orange(((TomcatServletWebServerFactory) factory).getPort())
                .yellow("and uri encoding of").orange(((TomcatServletWebServerFactory) factory).getUriEncoding())
                .yellow(".")
                .newLine()
                .reset();
    }
}