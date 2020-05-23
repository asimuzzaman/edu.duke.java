
/**
 * Write a description of Processor here.
 * 
 * @author Md. Asimuzzaman
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Processor {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        
        for(Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue())/ 3;
            
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        
        return outImage;
    }
    
    public void saveMultipleGray() {
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeGray(inImage);
            
            String fname = inImage.getFileName();
            outImage.setFileName("gray-"+fname);
            outImage.save();
            //outImage.draw();
        }
    }

    public void test() {
        /*ImageResource ir = new ImageResource();
        
        ImageResource out = makeGray(ir);
        out.draw();*/
        saveMultipleGray();
    }
}
