import helpers.TraefikDockerTagResolver;
import io.homecentr.testcontainers.containers.GenericContainerEx;
import io.homecentr.testcontainers.containers.ProcessNotFoundException;
import io.homecentr.testcontainers.containers.wait.strategy.WaitEx;
import io.homecentr.testcontainers.images.PullPolicyEx;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TraefikContainerShould {
    private static GenericContainerEx _cronContainer;

    @BeforeClass
    public static void before() {
        _cronContainer = new GenericContainerEx<>(new TraefikDockerTagResolver())
                .withEnv("PUID", "7088")
                .withEnv("PGID", "7099")
                .withImagePullPolicy(PullPolicyEx.never())
                .waitingFor(WaitEx.forS6OverlayStart());

        _cronContainer.start();
    }

    @AfterClass
    public static void after() {
        _cronContainer.close();
    }

    @Test
    public void runTraefikAsPassedPuid() throws InterruptedException, ProcessNotFoundException, IOException {
        int uid = _cronContainer.getProcessUid("traefik");

        assertEquals(7088, uid);
    }

    @Test
    public void runTraefikAsPassedPgid() throws InterruptedException, ProcessNotFoundException, IOException {
        int gid = _cronContainer.getProcessGid("traefik");

        assertEquals(7099, gid);
    }
}
