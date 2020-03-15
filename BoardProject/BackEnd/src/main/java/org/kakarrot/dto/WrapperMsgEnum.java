package org.kakarrot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WrapperMsgEnum {

    private MsgEnum state;
    private String msg;

    public WrapperMsgEnum(MsgEnum msgEnum){
        this.state = msgEnum;
        this.msg = msgEnum.getMsg();
    }

    public void setValues(MsgEnum msgEnum){
        this.state = msgEnum;
        this.msg = msgEnum.getMsg();
    }
}
