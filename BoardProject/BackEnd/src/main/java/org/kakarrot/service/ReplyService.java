package org.kakarrot.service;

import org.kakarrot.domain.ReplyVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyService {

    void writeReply(ReplyVO vo);

    void removeReply(ReplyVO vo);

    void modifyReply(ReplyVO vo);

    List<ReplyVO> getReplyList(Integer bno);

}
