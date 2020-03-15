package org.kakarrot.service;

import org.kakarrot.domain.BoardVO;
import org.kakarrot.dto.ScrollDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardService {

    void write(BoardVO vo);

    List<BoardVO> listPage(ScrollDTO dto);

    BoardVO read(Integer bno);

    void updata(BoardVO vo);

    void delete(Integer bno);
}
