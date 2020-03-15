package org.kakarrot.controller;


import lombok.extern.log4j.Log4j;
import org.kakarrot.dto.WrapperMsgEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.kakarrot.dto.MsgEnum.FAIL;

@RestControllerAdvice
@Log4j
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<WrapperMsgEnum> except(@ModelAttribute("exception") Exception e, Model model) {
        log.info(e.getMessage());
        return new ResponseEntity<>(new WrapperMsgEnum(FAIL), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}