package org.kakarrot.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j
@Data
public class BoardVO {

    private Integer bno;

    @Size(min = 1, max = 50, message = "1자 이상 50자 이하로 입력해주세요...")
    private String title;

    @NotBlank
    private String content;

    private String writer;

    private Date regdate;

    private String represent;

    private List<AttachVO> attachList;

    private List<ReplyVO> replyList;
}
