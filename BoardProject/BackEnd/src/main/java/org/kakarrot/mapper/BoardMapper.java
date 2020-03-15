package org.kakarrot.mapper;

import org.kakarrot.domain.BoardVO;
import org.kakarrot.dto.ScrollDTO;

import java.util.List;

public interface BoardMapper {

    boolean insertBoard(BoardVO vo);

    List<BoardVO> listPage(ScrollDTO dto);

    BoardVO selectByBno(Integer bno);

    boolean updateBoard(BoardVO vo);

    boolean deleteBoard(Integer bno);

    boolean replyCnt(Integer bno);
}
