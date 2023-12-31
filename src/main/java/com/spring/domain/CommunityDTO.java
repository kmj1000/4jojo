package com.spring.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommunityDTO {
	private int c_no;
    private String nickname;
    private String title;
    private String content;
    private String reg_date;
    private int view_cnt;
    private int like_cnt;
    private int reply_cnt;
    private List<CommunityAttachDTO> attachList;
}
