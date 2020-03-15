package org.kakarrot.util;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.kakarrot.controller.BoardController.convertPath;
import static org.kakarrot.util.CheckFileType.checkingImg;

@Log4j
public class FileLoader {

    private static String checkDirectory() {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy\\MM\\dd");
        String result = "C:\\upload\\" + dateFormat.format(new Date());

        if (new File(result).exists() == false) {
            Calendar now = Calendar.getInstance();

            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH) + 1;
            int day = now.get(Calendar.DAY_OF_MONTH);

            String path = "C:\\upload\\";

            File yearFolder = new File(path + year);
            File monthFolder = new File(path + year + "\\" + month);
            File dayFolder = new File(path + year + "\\" + month + "\\" + day);

            if (yearFolder.exists() == false) yearFolder.mkdir();
            if (monthFolder.exists() == false) monthFolder.mkdir();
            if (dayFolder.exists() == false) dayFolder.mkdir();
        }
        return result;
    }

    public static Map<String, List<String>> uploadFile(MultipartFile... multipartFiles) {
        Map<String, List<String>> result = new HashMap<>();
        List<String> files = new ArrayList<>();

        String path = checkDirectory();

        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = multipartFile.getOriginalFilename();
            String uuidName = UUID.randomUUID().toString() + "_" + fileName;
            File file = new File(path, uuidName);
            try {
                multipartFile.transferTo(file);
                if (checkingImg(fileName) == 1){
                    createThumbnail(path + "\\s_" + uuidName, multipartFile);
                    createSmallImg(path+"\\v_"+ uuidName, multipartFile);
                }
                files.add(uuidName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put(path, files);
        return result;
    }

    public static byte[] downloadFile(String path, String fname) {
        byte[] result = null;
        File file = new File(path, fname);
        try {
            result = FileCopyUtils.copyToByteArray(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static File[] findByFile(String path){
        File directory = new File(path);
        return directory.listFiles();
    }

    public static void removeFile(String originalPath){
        new File(originalPath).delete();
    }

    private static void createThumbnail(String pathName, MultipartFile multipartFile) throws IOException {
        FileOutputStream thumbnail = new FileOutputStream(new File(pathName));
        Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
        thumbnail.close();
    }

    private static void createSmallImg(String pathName, MultipartFile multipartFile) throws IOException {
        Thumbnails.of(multipartFile.getInputStream()).scale(0.5).toFile(pathName);
    }

}
