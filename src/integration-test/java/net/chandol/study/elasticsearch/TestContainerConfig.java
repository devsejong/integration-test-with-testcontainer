package net.chandol.study.elasticsearch;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.function.Consumer;

@Configuration
public class TestContainerConfig {
    private GenericContainer genericContainer;

    @PostConstruct
    public void initTestContainer() throws InterruptedException {
        int hostPort = 9200;
        int containerExposedPort = 9200;
        Consumer<CreateContainerCmd> cmd = e -> e.withPortBindings(new PortBinding(Ports.Binding.bindPort(hostPort), new ExposedPort(containerExposedPort)));

        genericContainer = new GenericContainer("elasticsearch:7.1.1")
                .withExposedPorts(hostPort)
                .withCreateContainerCmdModifier(cmd)
                .withEnv("discovery.type", "single-node")
                .waitingFor(new HttpWaitStrategy().forPort(hostPort).forStatusCode(HttpStatus.OK.value()));

        genericContainer.start();
    }

    @PreDestroy
    public void quitTestContainer() {
        genericContainer.close();
    }

}
