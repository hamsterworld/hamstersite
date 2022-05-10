package com.example.demo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class BoardUser {

    private String BoardTitle;
    private String BoardContent;
    private Long BoardView;
    private Long UserNumber;
    private Long BoardNumber;
    private String Nickname;
    private String BoardTime;

}
