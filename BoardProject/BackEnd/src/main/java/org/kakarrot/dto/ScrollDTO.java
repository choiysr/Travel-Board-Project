package org.kakarrot.dto;

import lombok.Data;

@Data
public class ScrollDTO {

    private Integer lastBno, amount;
    private String category, keyword;

    public String[] getType(){
        if(category == null || category.trim().length() == 0) return null;
        return this.category.split("");
    }
}
