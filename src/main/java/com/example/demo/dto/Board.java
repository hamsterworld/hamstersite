package com.example.demo.dto;

import lombok.Data;

@Data
public class Board {

    private Long BoardNumber;
    private String BoardTitle;
    private String BoardContent;
    private Long BoardView;
    private Long UserNumber;

}
