package com.in28minutes.learnspringboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// SumResponse.java
public class sumResponse {
    private int result;

    public sumResponse(int result) {
        this.result = result;
    }

}