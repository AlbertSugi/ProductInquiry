package com.hackerrank.eshopping.product.dashboard.service.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@RestController
class HealthcheckController {

    @GetMapping(value = "/healthcheck/{format}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<responsedetailed> healthcheck(@PathVariable("format")String format) {
        if(format.equals("short")) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        else if (format.equals("full")){

            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
            df.setTimeZone(tz);
            String nowAsISO = df.format(new Date());

            responsedetailed responsedetailed = new responsedetailed();
            responsedetailed.setApplication("ok");
            responsedetailed.setCurrentTime(nowAsISO);

            return ResponseEntity.status(HttpStatus.OK).body(responsedetailed);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping(value = "/healthcheck", produces = "application/json",consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity healthcheckPut() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("");
    }

    @PostMapping(value = "/healthcheck")
    public ResponseEntity healthcheckPost() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }


    @DeleteMapping(value = "/healthcheck")
    public ResponseEntity<String> healthcheckDelete() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("");
    }

}


class responsedetailed{
    private String currentTime;
    private String application;

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}