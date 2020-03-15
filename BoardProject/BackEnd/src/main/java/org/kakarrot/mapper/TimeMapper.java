package org.kakarrot.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    @Select("select now()")
    public String getTimeMySQL();

    @Select("select sysdate from dual")
    public String getTimeOracle();
}
