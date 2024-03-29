package photo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public interface IPhoto {

    public static final int MAXIMUM_BYTES_DATA = 15000;
    public static final int HEADER_MAGIC_NUMBER = 123456789;

    public static final int EDITED = 0;
    public static final int DELETED = 1;
    public static final int NEXT = 2;

    public static final int META_DATA_LENGTH = 16;

    public static byte[] loadImageToBytes(String filePath) {
        File input = new File(filePath);
        BufferedImage bufImage;
        try {
            bufImage = ImageIO.read(input);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufImage, "jpg", bos);

            byte[] data = bos.toByteArray();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * User will call this method and output a local file of jpg
     * 
     * @param data byte[]
     */
    public static Image bytesToImage(byte[] data, int uniqueID) {
        ByteArrayInputStream input = new ByteArrayInputStream(data);
        try {
            BufferedImage res = ImageIO.read(input);
            ImageIO.write(res, "jpg", new File("output" + uniqueID + ".jpg"));
            return res;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String chooseFile() {
        JFileChooser j = new JFileChooser();
        j.setDialogType(JFileChooser.OPEN_DIALOG);
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);

        j.setCurrentDirectory(new File("/Users/lala/eclipse-workspace/594Java/Haystack_Database"));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Format(.jpg/jpeg)",
                "jpg", "jpeg");
        j.setFileFilter(filter);

        int response = j.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            return j.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

}
