package org.kakarrot.service;

import lombok.Setter;
import org.kakarrot.domain.BoardVO;
import org.kakarrot.dto.ScrollDTO;
import org.kakarrot.mapper.AttachMapper;
import org.kakarrot.mapper.BoardMapper;
import org.kakarrot.util.FileLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {

    @Setter(onMethod_ = {@Autowired})
    private BoardMapper boardMapper;

    @Setter(onMethod_ = {@Autowired})
    private AttachMapper attachMapper;

    @Transactional
    @Override
    public void write(BoardVO vo) {
        boardMapper.insertBoard(vo);
        vo.getAttachList().forEach(attachMapper::insertFile);
    }

    @Override
    public List<BoardVO> listPage(ScrollDTO dto) {
        return boardMapper.listPage(dto);
    }

    @Override
    public BoardVO read(Integer bno) {
        return boardMapper.selectByBno(bno);
    }

    @Override
    public void updata(BoardVO vo) {
        boardMapper.updateBoard(vo);
    }

    @Transactional
    @Override
    public void delete(Integer bno) {
        attachMapper.findByBno(bno).forEach(attach-> attach.images().forEach(FileLoader::removeFile));
        attachMapper.deleteFiles(bno);
        boardMapper.deleteBoard(bno);
    }
}
