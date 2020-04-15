package com.netcracker.savenko.fapi.controller.handler;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

public interface HandlerService {
    Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex);
}
