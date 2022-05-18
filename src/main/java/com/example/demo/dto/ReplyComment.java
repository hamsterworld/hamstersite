package com.example.demo.dto;

import lombok.Data;

@Data
public class ReplyComment {

    private Integer replycommentnumber;
    private Integer commentnumber;
    private String replycommentcontent;
    private String replycommentdate;
    private Integer usernumber;
    private String nickname;

}
