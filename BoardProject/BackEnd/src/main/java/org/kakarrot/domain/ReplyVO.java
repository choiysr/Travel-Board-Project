package org.kakarrot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyVO {
    private Integer rno, bno, pno;
    private String reply, replyer;
    private Date regdate, updateddate;
    private List<ReplyVO> replyChildList;
    private List<ReplyImgVO> replyImgList;
}
