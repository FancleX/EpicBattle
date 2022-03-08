package Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.awt.Graphics;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements KeyListener {

    // store background
    private List<Background> allBackground = new ArrayList<>();
    // current background
    private Background currentBackground = new Background();
    // double cach
    private Image offScreenImage = null;

    public GameFrame() {
        // set window's size
        this.setSize(800, 600);
        // window displays in the center
        this.setLocationRelativeTo(null);
        // set visibility
        this.setVisible(true);
        // set close
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // set window unresizable
        this.setResizable(false);
        // add keyboard listener
        this.addKeyListener(this);
        // set title
        this.setTitle("Epic Battle");
        // initialize static value
        StaticValue.initializer();
        // initialize all scences
        for (int i = 1; i <= 3; i++) {
            allBackground.add(new Background(i, i == 3 ? true : false));
        }
        // set first scence to current scence
        currentBackground = allBackground.get(0);
        // sketch background
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(800, 600);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0, 0, 800, 600);

        // draw background
        graphics.drawImage(currentBackground.getBackgroundImage(), 0, 0, this);

        // connect off screen image to window
        g.drawImage(offScreenImage, 0, 0, this);

    }


    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();

    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

}