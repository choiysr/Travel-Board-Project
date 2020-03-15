package org.kakarrot.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static org.kakarrot.controller.BoardController.convertPath;
import static org.kakarrot.util.CheckFileType.checkingImg;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachVO {

    private Integer bno, isimg;
    private String fname, uploadpath;

    public String getRealname(){
        return this.fname.substring(this.fname.indexOf("_")+1);
    }

    public List<String> images(){
        List<String> result = new ArrayList<>();
        String path = convertPath(this.uploadpath)+"\\";
        result.add(path+this.fname);
        if(checkingImg(this.fname) == 1) {
            result.add(path+"s_"+this.fname);
            result.add(path+"v_"+this.fname);
        }
        return result;
    }

}
