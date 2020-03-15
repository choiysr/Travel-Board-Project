package org.kakarrot.dto;

import lombok.Getter;

@Getter
public enum MsgEnum {

    SUCCESS("성공적으로 등록되었습니다..."), ERROR("잠시 후에 시도해주세요..."), FAIL("다시 확인해주세요..."),
    DELETE("삭제되었습니다..."), LOGIN("로그인이 필요합니다."), LOGOUT("로그아웃 되었습니다.");

    private String msg;

    MsgEnum(String msg) {
        this.msg = msg;
    }
}
