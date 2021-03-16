import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReadFile {
    private HWColor[][] myColors;
    private int i=0;
    private int j=0;
    private Color color;
    public void read()throws IOException{
        CompBMX bmx = new CompBMX();
        CompEUC euc = new CompEUC();
        CompLEX lex = new CompLEX();
        PriorityQueue<HWColor> PQLEX = new PriorityQueue<HWColor>(lex);
        PriorityQueue<HWColor> PQEUC = new PriorityQueue<HWColor>(euc);
        PriorityQueue<HWColor> PQBMX = new PriorityQueue<HWColor>(bmx);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int counter = 0;
                    File file = new File("./src/example.png");
                    BufferedImage image = null;
                    image = ImageIO.read(file);
                    myColors = new HWColor[image.getWidth()][image.getHeight()];
                    for (i=0;i<image.getWidth();i++){
                        for (j=0;j<image.getHeight();j++) {
                            color = new Color(image.getRGB(i, j));
                            insertColors(color);
                            PQLEX.offer(myColors[i][j]);
                            PQEUC.offer(myColors[i][j]);
                            PQBMX.offer(myColors[i][j]);
                            System.out.println("Thread 1 : " + myColors[i][j]);
                            if (counter >= 100) {
                                Thread thread2 = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            System.out.println("Thread2-PQLEX: " + PQLEX.remove() + " size => " + PQLEX.getSize());
                                        } catch (Exception e) {
                                            System.out.println("just wait 2");
                                        }
                                    }
                                });
                                thread2.start();
                                Thread thread3 = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            System.out.println("Thread3-PQEUC: " + PQEUC.remove() + " size => " + PQEUC.getSize());
                                        } catch (Exception e) {
                                            System.out.println("just wait 3");
                                        }
                                    }
                                });
                                thread3.start();
                                Thread thread4 = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            System.out.println("Thread4-PQBMX: " + PQBMX.remove() + " size => " + PQBMX.getSize());
                                        } catch (Exception e) {
                                            System.out.println("just wait 4");
                                        }
                                    }
                                });
                                thread4.start();
                            }
                             counter++;
                        }
                    }
                }
                catch (Exception e){
                    System.out.println("just wait");
                }
            }
        });
        thread1.start();
    }

    public void insertColors(Color temp){
        myColors[i][j] = new HWColor(temp.getRed(),temp.getGreen(),temp.getBlue());
    }
}