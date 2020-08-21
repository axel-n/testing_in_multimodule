package com.example.trading.common;

import com.example.trading.common.data.RestResponse;
import com.example.trading.common.interfaces.IRequestSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class RequestSender implements IRequestSender {

    @Override
    public RestResponse sendRequest(HttpMethod method, String url) {
        return sendRequest(method, url, null, null);
    }

    @Override
    public RestResponse sendRequest(HttpMethod method, String url, Map<String, String> headers) {
        return sendRequest(method, url, headers, null);
    }

    @Override
    public RestResponse sendRequest(HttpMethod method, String url, Map<String, String> headers, Map<String, Object> object) {
        log.info("sendRequest for method {}, url {}, object {}", method, url, object);
        return new RestResponse(true, null); // emulate send success response
    }
}
