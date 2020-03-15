package org.kakarrot.util;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Log4j
public class FileUtilTests {

    @Test
    public void testImgCheck(){
        log.info(CheckFileType.checkingImg("C:\\upload\\2019\\11\\25\\aaa.jpg"));
        log.info(CheckFileType.checkingImg("C:\\upload\\2019\\11\\25\\aaa.pdf"));
    }

    @Test
    public void testGetFileList(){
        File[] files = FileLoader.findByFile("C:\\upload\\2019\\11\\25");
        for(File file : files)
            log.info(file.getAbsolutePath());
    }


    @Test
    public void testDateFolder(){

        Calendar now  = Calendar.getInstance();

        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH)+1;
        int day = now.get(Calendar.DAY_OF_MONTH);

        log.info(year);
        log.info(month);
        log.info(day);

        String path = "C:\\upload\\";

        File yearFolder = new File(path+year);
        File monthFolder = new File(path+year+"\\"+month);
        File dayFolder = new File(path+year+"\\"+month+"\\"+day);

        if(yearFolder.exists() == false) yearFolder.mkdir();
        if(monthFolder.exists() == false) monthFolder.mkdir();
        if(dayFolder.exists() == false) dayFolder.mkdir();

//        SimpleDateFormat dateFormat = new SimpleDateFormat();
//        dateFormat.applyPattern("yyyy");
//
//        log.info(dateFormat.format(new Date()));
//
//        String path = "C:\\upload\\";
//        String now = dateFormat.format(new Date());
//
//        File folder = new File(path+now);
//
//        if (!folder.exists()) {
//            try{
//                folder.mkdir(); //폴더 생성합니다.
//                System.out.println("폴더가 생성되었습니다.");
//            }
//            catch(Exception e){
//                e.printStackTrace();
//            }
//        }else {
//            System.out.println("이미 폴더가 생성되어 있습니다.");
//        }
    }

}
