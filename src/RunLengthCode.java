
//Code by Maryam ElOraby
//        ID: 46-3294



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.awt.image.BufferedImage; 
import java.awt.image.Raster;

import javax.imageio.ImageIO; 


public class RunLengthCode {
	
	
	private static String rowcounts(int [] str)
	{	
		String out = "";
	    int n = str.length;
	    for (int i = 0; i < n; i++) {	 
	        int count = 1;
	        while (i < n-1 && str[i] == str[i+1]) {
	            count++;
	            i++;
	        }
	        // Print character and its count
	        out += str[i] +"'"+ count + " "; 
	    }
	    return out;
	}
	

	private static String ComputeCode(int[][] imgArr){
		String RLC = "";
		for (int row=0;row<imgArr.length;row++) {
			
			String curr = rowcounts(imgArr[row]);
			int s = 0;
			
			if (curr.contains("1'"))
			{
				RLC += "(" + row;
				String[] f = curr.split(" ");	
				for (int i = 0; i<f.length;i++)
				{ 
					if (f[i].charAt(0) == '1')
					{
						RLC += " " + s;
						s += Integer.parseInt(f[i].substring(2)) - 1 ;
						RLC +=" " + s;
						s++;
					} 
					else
					{
						s += Integer.parseInt(f[i].substring(2));
					}
				}
				RLC += ")";
			}
		}
		return RLC;
	}
	public static void main(String [] args) {
		//write image path 
		String path = "binary_line.jpg";
		//String path = "binary_triangle.jpg";
		
		
		
		BufferedImage image = null;
		
		try {
			File input_image = new File(path);
			// Reading input image 
		    image = ImageIO.read(input_image);
		    System.out.println("Reading complete."); 
		} 
		catch (IOException e) {}
		
		// convert image to 2D array 
		int width = image.getWidth();
	    int height = image.getHeight();
	    int[][] imgArr = new int[height][width];
	    Raster raster = image.getData();
	    for (int i = 0; i < height; i++) {
	        for (int j = 0; j < width; j++) {
	            imgArr[i][j] = raster.getSample(j, i, 0);
	        }
	    }
		
	    String fileName = "Line_RLE.txt";
	    //String fileName = "Triangle_RLE.txt";
		
	    final boolean append = true, autoflush = true;
	    PrintStream printStream;
		try {
			printStream = new PrintStream(new FileOutputStream(fileName, append),autoflush);
			System.setOut(printStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
		// print run length Code 
		System.out.println(ComputeCode(imgArr));

	}//main ends here
}// class ends here 