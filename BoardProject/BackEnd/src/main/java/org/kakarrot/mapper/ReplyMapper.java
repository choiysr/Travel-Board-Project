package org.kakarrot.mapper;

import org.kakarrot.domain.AttachVO;
import org.kakarrot.domain.ReplyImgVO;
import org.kakarrot.domain.ReplyVO;

import java.util.List;

public interface ReplyMapper {

    boolean insertReply(ReplyVO vo);

    boolean deleteReply(Integer rno);

    boolean updateReply(ReplyVO vo);

    List<ReplyVO> replyList(Integer bno);


    Integer countPno(Integer pno);

    boolean insertReplyImg(ReplyImgVO vo);

    boolean deleteReplyImg(Integer rno);

    boolean updateReplyImg(ReplyImgVO vo);

    List<ReplyImgVO> replyImgList(Integer rno);
}
