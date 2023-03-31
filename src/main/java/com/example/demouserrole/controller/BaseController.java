package com.example.demouserrole.controller;

import com.example.demouserrole.response.BaseItemResponse;
import com.example.demouserrole.response.BaseListItemResponse;
import org.springframework.http.ResponseEntity;
import java.util.List;

public class BaseController {

    protected  <T> ResponseEntity<?> buildItemResponse(T data) {
        BaseItemResponse<T> response = new BaseItemResponse<>();
        response.setData(data);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    protected <T> ResponseEntity<?> buildListItemResponse(List<T> data, long total) {
        BaseListItemResponse<T> response = new BaseListItemResponse<>();
        response.setResult(data, total);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }
}
