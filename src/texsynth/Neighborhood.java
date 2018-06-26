package texsynth;

public class Neighborhood {
	  int size;
	  int[] values;
	  Neighborhood(int size) {
		  this.size = size;
		  this.values = new int[size];
	  }
	  void setValues(int[] c) {
		  this.values = c;
	  }
	  private static double colorDist(int c1, int c2) {
		  int r1 = (c1 & 0xff0000) >> 16;
		  int r2 = (c2 & 0xff0000) >> 16;
		  int g1 = (c1 & 0x00ff00) >> 8;
		  int g2 = (c2 & 0x00ff00) >> 8;
		  int b1 = (c1 & 0x0000ff);
		  int b2 = (c2 & 0x0000ff);
		  return Math.sqrt((r1-r2)*(r1-r2) + (g1-g2)*(g1-g2) + (b1-b2)*(b1-b2));
	  }
	  double dist(Neighborhood n) {
		  float sum = 0;
		  for (int i = 0; i < this.values.length/2; i++) {
			  sum += colorDist(this.values[i], n.values[i]);
		  }
		  return Math.sqrt(sum);
	  }
	  int getCenter() {
		  return values[size*size / 2];
	  }
}
