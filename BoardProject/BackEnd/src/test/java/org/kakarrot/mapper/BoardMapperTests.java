package org.kakarrot.mapper;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.kakarrot.domain.BoardVO;
import org.kakarrot.dto.ScrollDTO;
import org.kakarrot.loading.LoadingTests;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j
public class BoardMapperTests extends LoadingTests {

    @Autowired
    private BoardMapper mapper;

//    @Autowired
//    private ReplyMapper replyMapper;
//
//    @Test
//    public void getReplyList(){
//        log.info(replyMapper.replyList(5));
//    }

    @Test
    public void testSearch(){
        ScrollDTO dto = new ScrollDTO();
        dto.setLastBno(0);
        dto.setAmount(10);
        mapper.listPage(dto).forEach( board -> {
            board.getReplyList().forEach(reply -> reply.getReplyChildList().forEach(
                    log::info
            ));
        });
    }

    @Test
    public void testOne(){
        log.info(mapper.selectByBno(9).getReplyList());
    }
//
//    @Test
//    public void testPaging() {
//        log.info("total Test..... = "+mapper.getTotal());
//        log.info(new PageMaker(new PageDTO(), mapper.getTotal()));
//    }
//
//    @Test
//    public void testSelectByBno(){
//        log.info(mapper.selectByBno(2));
//    }
//
//    @Test
//    public void testBoardList(){
//        log.info(mapper.listPage(new PageDTO()));
//    }
//
    @Test
    public void testInsert(){
        for(int i=0; i<100; i++){
            BoardVO vo = new BoardVO();
            vo.setTitle("title"+i);
            vo.setContent("content"+i);
            vo.setWriter("writer"+i);
            log.info(mapper.insertBoard(vo));
        }
//
//        BoardVO vo = new BoardVO();
//        vo.setTitle("title");
//        vo.setContent("content"+10000);
//        vo.setWriter("writer"+10000);
//        log.info(mapper.insertBoard(vo));
    }

}
