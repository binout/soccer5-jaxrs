package net.binout.soccer5;

import com.google.common.util.concurrent.AbstractIdleService;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

import static net.binout.soccer5.Soccer5Application.ROOT_PATH;

/**
 * User: binout
 * Date: 26/07/13
 */
public class Soccer5Server extends AbstractIdleService {

    private static final String DEFAULT_PORT = "8080";
    private int port;
    private HttpServer httpServer;

    public Soccer5Server(int port) throws IOException {
        this.port = port;

        Soccer5Application application = new Soccer5Application();
        ResourceConfig config = new DefaultResourceConfig(application.getClasses());
        config.getContainerResponseFilters().add(new CORSFilter());
        httpServer = HttpServerFactory.create("http://localhost:" + port + ROOT_PATH, config, null);
    }

    public int getPort() {
        return port;
    }

    @Override
    protected void startUp() throws Exception {
        httpServer.start();
    }

    @Override
    protected void shutDown() throws Exception {
        httpServer.stop(1);
    }

    public static void main(String[] args) throws IOException {
        String port = getEnvPort();
        new Soccer5Server(Integer.valueOf(port)).startAndWait();
    }

    private static String getEnvPort() {
        String herokuPort = System.getenv("PORT");
        String cloudBeesPort = System.getProperty("app.port");
        String port = DEFAULT_PORT;
        if (herokuPort!= null) {
            port = herokuPort;
        }
        if (cloudBeesPort != null) {
            port = cloudBeesPort;
        }
        return port;
    }
}
class CORSFilter implements ContainerResponseFilter {
    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
        return response;
    }
}

