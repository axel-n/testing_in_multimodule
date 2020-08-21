package com.example.trading.common.interfaces;

import com.example.trading.common.data.RestResponse;
import org.springframework.http.HttpMethod;

import java.util.Map;

public interface IRequestSender {
    RestResponse sendRequest(HttpMethod method, String url);
    RestResponse sendRequest(HttpMethod method, String url, Map<String, String> headers);
    RestResponse sendRequest(HttpMethod method, String url, Map<String, String> headers, Map<String, Object> object);
}
