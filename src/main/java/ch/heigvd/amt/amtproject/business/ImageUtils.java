package ch.heigvd.amt.amtproject.business;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

// source https://gist.github.com/werbth/4b1cf7567657cd64c71eb08099544f35
public class ImageUtils {

    public static InputStream resizeImage(InputStream inputStream, int scalesize) throws IOException {
        BufferedImage sourceImage = ImageIO.read(inputStream);

        int w = sourceImage.getWidth();
        int h = sourceImage.getHeight();
        double greatest = (w>h?w:h);
        double scale = (double)scalesize / greatest;

        int scaled_w = (int)(w * scale);
        int scaled_h = (int)(h * scale);

        Image thumbnail = sourceImage.getScaledInstance(scaled_w, scaled_h, Image.SCALE_SMOOTH);
        BufferedImage bufferedThumbnail = new BufferedImage(thumbnail.getWidth(null),
                thumbnail.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        bufferedThumbnail.getGraphics().drawImage(thumbnail, 0, 0, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedThumbnail, "jpeg", baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }
}
