package line.robot.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;

@Service
public class DeadPoolService {



    /**
     * 给图片添加文字水印
     * @param pressText 水印文字
     * @param input 源图像地址
     * @param destImageFile 目标图像地址
     * @param fontStyle 水印的字体样式
     * @param color 水印的字体颜色
     * @param fontSize 水印的字体大小
     * @param x 修正值
     * @param y 修正值
     * @param alpha 透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
     */
    public final static void pressText(String pressText,
                                       InputStream input, Path destImageFile,
                                       int fontStyle, Color color, int fontSize, int x,
                                       int y, float alpha) {
        try {
            Font font;
            boolean isChn = pressText.getBytes().length == pressText.length()?false:true;
            if(isChn){
                font = Font.createFont(Font.TRUETYPE_FONT,new ClassPathResource("/static/FONT/traditionalChn.ttf").getInputStream());
                font.deriveFont(fontSize);

            }else{
                font = new Font("DejaVu Sans", fontStyle, fontSize);
            }
            System.out.println("***********************************************************************************" + pressText);

            Image src = ImageIO.read(input);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);
            g.setColor(color);
            g.setFont(font);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // 在指定坐标绘制水印文字
            g.drawString(pressText, 100, 100);
            g.dispose();
            ImageIO.write((BufferedImage) image, "JPEG", destImageFile.toFile());// 输出到文件流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算text的长度（一个中文算两个字符）
     * @param text
     * @return
     */
    public final static int getLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (new String(text.charAt(i) + "").getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return length / 2;
    }
}
