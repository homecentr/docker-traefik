import helpers.TraefikDockerTagResolver;
import io.homecentr.testcontainers.containers.GenericContainerEx;
import io.homecentr.testcontainers.containers.HttpResponse;
import io.homecentr.testcontainers.containers.wait.strategy.WaitEx;
import io.homecentr.testcontainers.images.PullPolicyEx;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.Network;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class TraefikContainerWithConfigInContainerSpecificLocationShould {
    private static GenericContainerEx _traefikContainer;
    private static GenericContainerEx _nginxContainer;
    private static Network _network;

    @BeforeClass
    public static void before() {
        _network = Network.newNetwork();

        _traefikContainer = new GenericContainerEx<>(new TraefikDockerTagResolver())
                .withNetwork(_network)
                .withExposedPorts(8080)
                .withRelativeFileSystemBind(Paths.get("..", "example", "traefik", "traefik.yaml"), "/config/traefik.yaml")
                .withRelativeFileSystemBind(Paths.get("..", "example", "traefik", "nginx.yaml"), "/nginx.yaml")
                .withImagePullPolicy(PullPolicyEx.never())
                .waitingFor(WaitEx.forS6OverlayStart());

        _nginxContainer = new GenericContainerEx<>("nginx")
                .withNetwork(_network)
                .withNetworkAliases("nginx")
                .withRelativeFileSystemBind(Paths.get("..", "example", "nginx", "nginx.conf"), "/etc/nginx/conf.d/default.conf");

        _nginxContainer.start();
        _traefikContainer.start();
    }

    @AfterClass
    public static void after() {
        _nginxContainer.close();
        _traefikContainer.close();
    }

    @Test
    public void listenOnWebPort() throws IOException {
        HttpResponse response = _traefikContainer.makeHttpRequest(80, "/nginx");
        String responseContent = response.getResponseContent();

        assertEquals("Hello, world!", responseContent);
    }
}