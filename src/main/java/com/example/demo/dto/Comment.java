package com.example.demo.dto;


import lombok.Data;

@Data
public class Comment {

    private Integer commentnumber;
    private Integer boardnumber;
    private Integer usernumber;
    private String nickname;
    private String commentcontent;
    private String commentdate;

}
