package com.msys.smartkart.controller;

import com.msys.smartkart.config.GenericResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class Demo {
    @GetMapping("/hello")
    public ResponseEntity<GenericResponseDto> hello() {


            List list=new ArrayList<>();
            list.add("Siva");
            list.add("anil");
        log.info("Cheking for response");

        return new ResponseEntity<>(
        new GenericResponseDto<>(200, list), HttpStatus.ACCEPTED);
    }

    @GetMapping("/validate")
    public ResponseEntity<GenericResponseDto> validate(@RequestParam String name,@RequestParam String pass){


        log.info("name : "+ name);
        log.info("pass : "+pass);

        return new ResponseEntity<>(new GenericResponseDto(200,"Welcome"),HttpStatus.ACCEPTED);
    }

    @GetMapping("/demo")
    public ResponseEntity<GenericResponseDto> checking(@RequestParam String name, @RequestParam String value){

        log.info("Checking name={}",name);
        log.info("Checking value={}",value);

        return new ResponseEntity<>(new GenericResponseDto(200,"hi"), HttpStatus.ACCEPTED);
    }


}
