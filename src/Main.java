import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import texsynth.TextureSynthesis;

public class Main extends Component {
  BufferedImage img;
  BufferedImage tex;

  public void paint(Graphics g) {
    g.drawImage(img, 0, 0, null);
    g.drawImage(tex, tex.getWidth(), 0, null);
  }
  public Main(String path) {
    try {
      img = ImageIO.read(new File(path));
    }  catch (IOException e) {
    	System.err.println(e.getMessage());
      System.exit(ERROR);
    }
    tex = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
    TextureSynthesis.generateTexture(img, tex);
  }

  public Dimension getPreferredSize() {
    if (img == null) {
      return new Dimension(100,100);
    }  else {
      return new Dimension(2*img.getWidth(null), img.getHeight(null));
    }
  }
  public static void main(String[] args) {
    JFrame f = new JFrame("Load Image");
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    f.add(new Main("wood128.jpg"));
    f.pack();
    f.setVisible(true);
  }
}