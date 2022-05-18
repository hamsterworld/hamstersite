package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class testdto {

    private Integer boardnumber;
    private Integer page;
    private Integer pagesize;

}
