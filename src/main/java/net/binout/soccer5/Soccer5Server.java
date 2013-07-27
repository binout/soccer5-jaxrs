package net.binout.soccer5;

import com.google.common.util.concurrent.AbstractIdleService;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;

import static com.google.common.base.Objects.firstNonNull;
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
        int port = Integer.valueOf(firstNonNull(System.getProperty("app.port"), DEFAULT_PORT));
        new Soccer5Server(port).startAndWait();
    }
}
