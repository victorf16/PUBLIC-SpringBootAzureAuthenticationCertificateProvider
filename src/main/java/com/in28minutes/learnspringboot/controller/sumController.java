package com.in28minutes.learnspringboot.controller;

//import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.Data;
import com.in28minutes.learnspringboot.dto.sumResponse;
import com.in28minutes.learnspringboot.dto.sumRequest;


//@RestController ("/sum")
//@Data
@RestController
@RequestMapping("/sum")
public class sumController {

    @PostMapping("/calculate")
    public ResponseEntity<sumResponse> calculateSum(@RequestBody sumRequest request) {
        int result = sum(request.getA(), request.getB());
        sumResponse response = new sumResponse(result);
        return ResponseEntity.ok(response);
    }

    private int sum(int a, int b){
        return a+b;
    }

//    public String calculateSum() {
//        int result = getSum();
//        String message = "O Resultado da Soma é = " + result;
//        return message;
//    }

    // how  to get these 2 number from user that send thorught post request 2 numbers and it sum them and return the result
    // 1. create a method that takes 2 numbers as input
    // 2. create a post request that takes 2 numbers as input
    // 3. create a post request that takes 2 numbers as input and return the sum of them

//s





}
