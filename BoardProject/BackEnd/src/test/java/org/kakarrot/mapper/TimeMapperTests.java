package org.kakarrot.mapper;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.kakarrot.loading.LoadingTests;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j
public class TimeMapperTests extends LoadingTests {

    @Autowired
    private TimeMapper timeMapper;

    @Autowired
    private AttachMapper attachMapper;

    @Test
    public void mySQLTimeMapperAnnoTest() {
        log.info(timeMapper.getTimeMySQL());
    }
}
