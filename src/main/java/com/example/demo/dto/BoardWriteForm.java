package com.example.demo.dto;

import lombok.Data;

@Data
public class BoardWriteForm {

    private Long UserNumber;
    private String boardtitle;
    private String boardcontent;

}
