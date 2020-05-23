 

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here BY MANSIB
        int count = 0;
        
        for(Point p: s.getPoints()) {
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here BY MANSIB
        return getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here BY MANSIB
        Point lastPt = s.getLastPoint();
        double largest = -1;
        
        for(Point p: s.getPoints()) {
            double temp = p.distance(lastPt);
            if(temp > largest)
                largest = temp;
            lastPt = p;         
        }
        return largest;
    }

    public double getLargestX(Shape s) {
        // Put code here BY MANSIB
        double largest = Integer.MIN_VALUE;
        
        for(Point p: s.getPoints()) {
            if (p.getX() > largest)
                largest = p.getX();
        }
        return largest;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here BY MANSIB
        DirectoryResource dr = new DirectoryResource();
        double largest = Integer.MIN_VALUE;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            
            if (largest < getPerimeter(s)) {
                largest = getPerimeter(s);
            }
        }
        return largest;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double largest = Integer.MIN_VALUE;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            
            if (largest < getPerimeter(s)) {
                largest = getPerimeter(s);
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        //System.out.println("perimeter = " + length);
        //System.out.println("Points = " + getNumPoints(s));
        //System.out.println("Average = " + getAverageLength(s));
        System.out.println("Largest = " + getLargestSide(s));
        //System.out.println("LargestX = " + getLargestX(s));        
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here BY MANSIB
        System.out.println("Largest in File = " + getLargestPerimeterMultipleFiles());        
    }

    public void testFileWithLargestPerimeter() {
        // Put code here BY MANSIB
        System.out.println("Largest File name = " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
