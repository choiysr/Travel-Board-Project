package org.kakarrot.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static org.kakarrot.controller.BoardController.convertPath;

@Data
public class ReplyImgVO {

    private Integer rno;
    private String img;

    public List<String> getImages(){
        List<String> result = new ArrayList<>();
        String path = convertPath(this.img.substring(0,this.img.indexOf("_")))+"\\";
        String fname = this.img.substring(this.img.indexOf("_")+1);
        result.add(path+fname);
        result.add(path+"s_"+fname);
        result.add(path+"v_"+fname);
        return result;
    }
}
