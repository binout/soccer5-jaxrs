package net.binout.soccer5;

import com.github.kevinsawicki.http.HttpRequest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static com.github.kevinsawicki.http.HttpRequest.*;
import static org.fest.assertions.Assertions.assertThat;

/**
 * User: binout
 * Date: 26/07/13
 */
public class Soccer5ServerTest {

    private static final int PORT = 9999;
    private static Soccer5Server server;

    @BeforeClass
    public static void should_start() throws Exception {
        server = new Soccer5Server(9999);
        server.startUp();
    }

    @Test
    public void should_get_home() {
        assertThat(get("http://localhost:" + PORT).code()).isEqualTo(Response.Status.OK.getStatusCode());
    }

    @Test
    public void should_get_port() {
         assertThat(server.getPort()).isEqualTo(PORT);
    }

    @Test
    public void should_manage_cors() {
        assertThat(get("http://localhost:" + PORT).header("Access-Control-Allow-Origin")).isEqualTo("*");
    }

    @AfterClass
    public static void should_stop() throws Exception {
        server.shutDown();
    }
}
