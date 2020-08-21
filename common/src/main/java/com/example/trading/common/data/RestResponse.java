package com.example.trading.common.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
public class RestResponse {
    private final boolean isSuccess;
    private final String text;
}
