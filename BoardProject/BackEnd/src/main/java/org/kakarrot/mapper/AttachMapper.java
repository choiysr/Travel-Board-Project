package org.kakarrot.mapper;

import org.kakarrot.domain.AttachVO;

import java.util.List;

public interface AttachMapper {

    boolean insertFile(AttachVO vo);

    boolean deleteFiles(Integer bno);

    AttachVO selectOne(Integer bno);

    List<AttachVO> findByBno(Integer bno);
}
