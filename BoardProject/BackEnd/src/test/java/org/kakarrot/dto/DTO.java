package org.kakarrot.dto;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.kakarrot.loading.LoadingTests;

import java.net.URLConnection;

@Log4j
public class DTO {

    @Test
    public void testMsg(){
        log.info(MsgEnum.SUCCESS.getMsg());
        log.info(URLConnection.guessContentTypeFromName("aaa.txt"));
    }
}
