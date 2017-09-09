//package com.readmain.admin.utils;
//
//import com.saf.panpan.common.exception.CustomerException;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.ArrayUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//@Component
//@Slf4j
//public class UploadImageUtils {
//
//    private static final String[] IMAGE_TYPE = new String[]{"png", "jpg", "jpeg", "bmp", "gif"};
//
//    private static final Integer DEFAULT_WIDTH = 1024;
//
//    private static final Integer DEFAULT_HEIGHT = 768;
//
//    public String uploadImage(String path, MultipartFile file, Long limitSize) throws Exception {
//        if (file == null) {
//            throw new CustomerException("上传图片不能为空!", 888888);
//        }
//
//        String fileName = file.getOriginalFilename();
//        String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
//        ext = ext.toLowerCase();
//        if (!ArrayUtils.contains(IMAGE_TYPE, ext)) {
//            throw new CustomerException("上传图片格式错误!", 888888);
//        }
//        if (limitSize != null && file.getSize() > limitSize * 1024) {
//            throw new CustomerException("上传图片大小不符合要求!", 888888);
//        }
//        String fileRename = path + File.separatorChar + System.currentTimeMillis() + "." + ext;
//        File targetFile = new File(fileRename);
//        if (!targetFile.exists()) {
//            targetFile.mkdirs();
//        }
//        try {
//            file.transferTo(targetFile);
//        } catch (Exception e) {
//            log.error("上传图片失败, 系统错误! {}", e);
//            throw new CustomerException("上传图片失败, 系统错误!", 888888);
//        }
//        compressPic(fileRename, DEFAULT_WIDTH, DEFAULT_HEIGHT, true);
//        return fileRename;
//    }
//
//    private Boolean compressPic(String fileName, int width, int height, boolean gp) {
//        try {
//            //获得源文件
//            File file = new File(fileName);
//            if (!file.exists()) {
//                return false;
//            }
//            Image img = ImageIO.read(file);
//            // 判断图片格式是否正确
//            if (img.getWidth(null) == -1) {
//                log.error("压缩图片失败！file:{}", fileName);
//                return false;
//            } else {
//                int newWidth;
//                int newHeight;
//                // 判断是否是等比缩放
//                if (gp) {
//                    // 为等比缩放计算输出的图片宽度及高度
//                    double rate1 = ((double) img.getWidth(null)) / (double) width + 0.1;
//                    double rate2 = ((double) img.getHeight(null)) / (double) height + 0.1;
//                    // 根据缩放比率大的进行缩放控制
//                    double rate = rate1 > rate2 ? rate1 : rate2;
//                    newWidth = (int) (((double) img.getWidth(null)) / rate);
//                    newHeight = (int) (((double) img.getHeight(null)) / rate);
//                } else {
//                    newWidth = width; // 输出的图片宽度
//                    newHeight = height; // 输出的图片高度
//                }
//                BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
//
//                /*
//                 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
//                 * 优先级比速度高 生成的图片质量比较好 但速度慢
//                 */
//                tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
//                ImageIO.write(tag, "JPG", new File(fileName));
//                //out.close();
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return true;
//    }
//
//}
