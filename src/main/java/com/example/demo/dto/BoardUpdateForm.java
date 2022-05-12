package com.example.demo.dto;

import lombok.Data;

@Data
public class BoardUpdateForm {

    private String boardtitle;
    private String boardcontent;
    private Integer page;
    private Integer pagesize;
    private Long boardnumber;
}
