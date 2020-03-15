package org.kakarrot.controller;

import lombok.extern.log4j.Log4j;
import org.kakarrot.dto.WrapperMsgEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.kakarrot.dto.MsgEnum.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Log4j
@CrossOrigin
@RestController
@RequestMapping(value = "/security")
public class SecurityController {

    @GetMapping(value = "/login", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WrapperMsgEnum> login(String error, String logout) {
        log.info(error);
        log.info(logout);
        return new ResponseEntity<>(new WrapperMsgEnum(FAIL), OK);
    }

    @GetMapping(value = "/loginSuccess", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WrapperMsgEnum> loginSuccess(String error, String logout) {
        log.info(error);
        log.info(logout);
        return new ResponseEntity<>(new WrapperMsgEnum(SUCCESS), OK);
    }

    @GetMapping(value = "/logout", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WrapperMsgEnum> logout(String error, String logout) {
        log.info(error);
        log.info(logout);
        return new ResponseEntity<>(new WrapperMsgEnum(LOGOUT), OK);
    }

    @GetMapping(value = "/loginMsg", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<WrapperMsgEnum> loginMsg(String error, String logout) {
        log.info(error);
        log.info(logout);
        return new ResponseEntity<>(new WrapperMsgEnum(LOGIN), OK);
    }
}
