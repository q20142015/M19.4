import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        String srcFolder = "C:/Users/Slava/Desktop/src";
        String dstFolder = "C:/Users/Slava/Desktop/dst";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int nimP = Runtime.getRuntime().availableProcessors();

        int midle = files.length / nimP;
        int nach = 0;
        int con = nach + midle;
        for (int i = 0; i < nimP; i++) {
            File[] file1 = new File[con - nach];
            System.arraycopy(files, nach, file1, 0, con - nach);
            ImageResizer image1 = new ImageResizer(file1, dstFolder, start);
            new Thread(image1).start();
            nach = con;
            con = (i + 2) * midle;
            if (i == nimP - 2) {
                con = files.length;
            }
        }

    }
}


