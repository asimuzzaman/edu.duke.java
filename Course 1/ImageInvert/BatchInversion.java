
/**
 * Write a description of BatchInversion here.
 * 
 * @author Md. Asimuzzaman
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class BatchInversion {
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
       
        for(Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());

            pixel.setRed(255-inPixel.getRed());
            pixel.setGreen(255-inPixel.getGreen());
            pixel.setBlue(255-inPixel.getBlue());
        }
        
        return outImage;
    }
    
    public void saveAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeInversion(inImage);
            
            String fname = inImage.getFileName();
            outImage.setFileName("inverted-"+fname);
            outImage.save();
            //outImage.draw();
        }
    }
    
    public void test() {
        saveAndConvert();
    }
}
