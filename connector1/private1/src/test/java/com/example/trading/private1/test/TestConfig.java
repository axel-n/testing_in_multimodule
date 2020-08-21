package com.example.trading.private1.test;


import com.example.trading.common.RedisSenderMock;
import com.example.trading.common.RequestSender;
import com.example.trading.common.interfaces.IPrivateApiClient;
import com.example.trading.common.interfaces.IPrivateConnector;
import com.example.trading.common.interfaces.IRedisSender;
import com.example.trading.common.interfaces.IRequestSender;
import com.example.trading.private1.Private1Connector;
import com.example.trading.private1_core.Private1ApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@Slf4j
@TestConfiguration
public class TestConfig {

    @Bean
    public IRequestSender requestSender() {
        log.info("requestSender init...");
        return new RequestSender();
    }

    @Bean
    public IRedisSender redisSender() {
        log.info("redisSender init...");
        return new RedisSenderMock();
    }

    @Bean
    public IPrivateApiClient apiClient() {
        log.info("private1ApiClient init...");
        return new Private1ApiClient(requestSender());
    }

    @Bean
    public IPrivateConnector privateConnector(){
        log.info("private1Connector init...");
        return new Private1Connector(apiClient(), redisSender());
    }
}
