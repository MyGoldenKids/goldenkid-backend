package com.ehours.goldenchild.common;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseResource {
    public static ResponseEntity<Map<String, Object>> handleSuccess(Object data, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("message", message);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    public static ResponseEntity<Map<String, Object>> handleError(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
}
