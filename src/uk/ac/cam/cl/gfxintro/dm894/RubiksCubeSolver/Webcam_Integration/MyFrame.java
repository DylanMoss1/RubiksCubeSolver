package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Webcam;

//Credits to computervisionandjava for parts of webcam code
//http://computervisionandjava.blogspot.com/2013/10/java-opencv-webcam.html

//Credits to edu4java for parts of key listener code
//http://www.edu4java.com/en/game/game4.html

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_Cube_Manager.RubiksCubeManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MyFrame extends JFrame {
    private JPanel contentPane;

    public static boolean calibrationMode = false;
    public static boolean readFrame = false;
    public static RubiksCubeManager rubiksCubeManager;

    /**
     * Launch the application.
     */


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyFrame frame = new MyFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void runFrame() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyFrame frame = new MyFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MyFrame() {
        KeyListener listener = new MyKeyListener();
        addKeyListener(listener);
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 640, 480);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        new MyThread().start();
    }

    VideoCap videoCap = new VideoCap();

    public void paint(Graphics g){
        g = contentPane.getGraphics();
        g.drawImage(videoCap.getOneFrame(), 0, 0, this);
        g.setColor(new Color(0,255,0));
        /*
        for(int a=0; a<3; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 5; c++) {
                    for (int d = 0; d < 5; d++) {
                        g.drawRect(268 + 35 * a + 5 * (c+1), 188 + 35 * b + 5 * (d+1), 2, 2);
                    }
                }
            }
        }
        
         */

                        //int pixelColour = img.getRGB(268 + 35 * a + 5 * (c+1), 188 + 35 * b + 5 * (d+1));
        g.drawRect((640 - 105) / 2, (480 - 105) / 2, 105, 105);
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            for (;;){
                repaint();
                try { Thread.sleep(30);
                } catch (InterruptedException e) {    }
            }
        }
    }

    class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == 32 && calibrationMode){
                MyFrame.readFrame = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}