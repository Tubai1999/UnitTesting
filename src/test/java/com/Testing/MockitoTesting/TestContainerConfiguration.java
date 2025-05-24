package com.Testing.MockitoTesting;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
//import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class TestContainerConfiguration {
    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:16"))
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
    }
//    MySQLContainer<?> mysqlContainer() {
//        return new MySQLContainer<>(DockerImageName.parse("mysql:8.0"));
////                .withDatabaseName("testdb")
////                .withUsername("testuser")
////                .withPassword("testpass");
//    }
}
