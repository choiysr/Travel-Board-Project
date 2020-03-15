package org.kakarrot.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.kakarrot.domain.AttachVO;
import org.kakarrot.domain.BoardVO;
import org.kakarrot.domain.ReplyImgVO;
import org.kakarrot.domain.ReplyVO;
import org.kakarrot.dto.ScrollDTO;
import org.kakarrot.dto.WrapperMsgEnum;
import org.kakarrot.service.BoardService;
import org.kakarrot.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.kakarrot.dto.MsgEnum.*;
import static org.kakarrot.util.CheckFileType.checkingImg;
import static org.kakarrot.util.FileLoader.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;


import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.net.URLConnection;
import java.util.*;

@Log4j
@CrossOrigin
@RestController
@RequestMapping(value = "/board")
public class BoardController {

    @Setter(onMethod_ = {@Autowired})
    private BoardService boardService;

    @Setter(onMethod_ = {@Autowired})
    private ReplyService replyService;


    @GetMapping(value = "/list", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<BoardVO>> getListPage(ScrollDTO dto) {
        return new ResponseEntity<>(boardService.listPage(dto), OK);
    }

    @PostMapping(value = "/write", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WrapperMsgEnum> writeBoard(@RequestBody @Valid BoardVO vo, BindingResult bind) {
        vo.setWriter("writer123");
        WrapperMsgEnum result = new WrapperMsgEnum();
        if (bind.hasErrors()) {
            result.setValues(FAIL);
        } else {
            boardService.write(vo);
            result.setValues(SUCCESS);
        }
        return new ResponseEntity<>(result, OK);
    }

    @GetMapping(value = "/{bno}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<BoardVO> readBoard(@PathVariable("bno") Integer bno) {
        return new ResponseEntity<>(boardService.read(bno), OK);
    }

    @DeleteMapping(value = "/{bno}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WrapperMsgEnum> deleteBoard(@PathVariable("bno") Integer bno) {
        boardService.delete(bno);
        return new ResponseEntity<>(new WrapperMsgEnum(DELETE), OK);
    }

    @PutMapping(value = "/update", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WrapperMsgEnum> updateBoard(@RequestBody @Valid BoardVO vo, BindingResult bind) {
        WrapperMsgEnum result = new WrapperMsgEnum();
        if (bind.hasErrors()) {
            result.setValues(FAIL);
        } else {
            try{
                boardService.updata(vo);
                result.setValues(SUCCESS);

            } catch (Exception e){
                result.setValues(FAIL);
            }
        }
        return new ResponseEntity<>(result, OK);
    }

    @PostMapping(value = "/uploadFile", produces = APPLICATION_JSON_UTF8_VALUE, consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<AttachVO>> fileUpload(MultipartFile[] files) {
        return new ResponseEntity<>(mapToAttachList(uploadFile(files)), CREATED);
    }

    @GetMapping("/viewFile")
    public ResponseEntity<byte[]> viewFile(AttachVO vo) {
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", URLConnection.guessContentTypeFromName(vo.getFname()));
        return new ResponseEntity<>(downloadFile(convertPath(vo.getUploadpath()), vo.getFname()), header, OK);
    }

    @GetMapping(value = "/downloadFile", produces = APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downFile(AttachVO vo) throws Exception {
        String fname = vo.getFname();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename="
                + new String(fname.substring(fname.indexOf("_") + 1).getBytes("UTF-8"), "ISO-8859-1"));
        return new ResponseEntity<>(downloadFile(convertPath(vo.getUploadpath()), fname), headers, OK);
    }





    @PostMapping(value = "/writeReply", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WrapperMsgEnum> writeReply(@RequestBody ReplyVO vo) {
        //메세지 if처리 필요
        vo.setReplyer("replyer123");
        replyService.writeReply(vo);
        return new ResponseEntity<>(new WrapperMsgEnum(SUCCESS), CREATED);
    }

    @DeleteMapping(value = "/reply/{rno}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WrapperMsgEnum> removeReply(@RequestBody ReplyVO vo){
        replyService.removeReply(vo);
        return new ResponseEntity<>(new WrapperMsgEnum(DELETE), OK);
    }

    @GetMapping(value = "/reply/{bno}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<ReplyVO>> readReply(@PathVariable("bno") Integer bno) {
        return new ResponseEntity<>(replyService.getReplyList(bno), OK);
    }

    @PutMapping(value = "/modifyReply", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WrapperMsgEnum> modifyReply(@RequestBody ReplyVO vo) {
        log.info(vo);
        replyService.modifyReply(vo);
        return new ResponseEntity<>(new WrapperMsgEnum(SUCCESS), OK);
    }



    private List<AttachVO> mapToAttachList(Map<String, List<String>> files) {
        List<AttachVO> result = new ArrayList<>();
        files.keySet().iterator().forEachRemaining(path ->
                files.get(path).forEach(fname -> result.add(new AttachVO().builder().fname(fname).uploadpath(convertPath(path)).isimg(checkingImg(fname)).build()))
        );
        return result;
    }

    public static String convertPath(String path) {
        return path.indexOf("\\") == -1 ? path.replace("-", "\\") : path.replace("\\", "-");
    }

}
