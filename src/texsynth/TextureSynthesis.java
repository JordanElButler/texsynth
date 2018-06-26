package texsynth;

import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ArrayList;

public class TextureSynthesis {
  public static void generateTexture(BufferedImage src, BufferedImage output) {
    int ksize = 5;
	initializeRandomTexture(output);
	// initialize samples
    ArrayList<Neighborhood> samples = new ArrayList<Neighborhood>();
    for (int i = 0; i < src.getHeight(); i++) {
    	for (int j = 0; j < src.getWidth(); j++) {
    		Neighborhood n = new Neighborhood(ksize);
    		int[] c = new int[ksize*ksize];
    		for (int k = 0; k < ksize; k++) {
    			for (int l = 0; l < ksize; l++) {
    				int xx = (j + l - ksize/2 + src.getWidth()) % src.getWidth();
    				int yy = (i + k - ksize/2 + src.getHeight()) % src.getHeight();
    				c[l + k * ksize] = src.getRGB(xx, yy);
    			}
    		}
    		n.setValues(c);
    		samples.add(n);
    	}
    }
    // find best fit for each slice of output
    for (int i = 0; i < output.getHeight(); i++) {
    	for (int j = 0; j < output.getWidth(); j++) {
    		Neighborhood n = new Neighborhood(ksize);
    		int[] c = new int[ksize*ksize];
    		for (int k = 0; k < ksize; k++) {
    			for (int l = 0; l < ksize; l++) {
    				int xx = (j + l - ksize/2 + output.getWidth()) % output.getWidth();
    				int yy = (i + k - ksize/2 + output.getHeight()) % output.getHeight();
    				c[l + k * ksize] = output.getRGB(xx, yy);
    			}
    		}
    		n.setValues(c);
    		
    		Neighborhood minN = null;
    		double minD = Double.POSITIVE_INFINITY;
    		for (Neighborhood nn : samples) {
    			double dist = nn.dist(n);
    			if (dist < minD) {
    				minD = dist;
    				minN = nn;
    			}
    		}
    		output.setRGB(j, i, minN.getCenter());
    	}
    }
  }
  private static void initializeRandomTexture(BufferedImage tex) {
    Random r = new Random();
    for (int i = 0; i < tex.getHeight(); i++) {
      for (int j = 0; j < tex.getWidth(); j++) {
        int c = r.nextInt(256) << 16 | r.nextInt(256) << 8 | r.nextInt(256);
        tex.setRGB(j,i, c);
      }
    }
  }
}
