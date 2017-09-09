package com.readmain.admin.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtils {

    private static final String CHARSET = "utf-8";

    public static String createQRCode(String content) throws Exception {
        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
            BufferedImage srcImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(srcImage, "jpg", out);
            byte[] data = out.toByteArray();
            return Base64.encodeBase64String(data);
        } catch (Exception e) {
            return "";
        }
    }
}
