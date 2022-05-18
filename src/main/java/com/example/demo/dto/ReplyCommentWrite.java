package com.example.demo.dto;

import lombok.Data;

@Data
public class ReplyCommentWrite {

    private Integer parentcommentnumber;
    private String commentcontent;
    private String nickname;
    private Integer usernumber;
    private Integer boardnumber;
    private String targetnickname;


}
