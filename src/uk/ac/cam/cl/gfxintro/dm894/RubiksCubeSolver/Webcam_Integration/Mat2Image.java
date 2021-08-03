package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Webcam;//Credits to computervisionandjava for parts of webcam code
//http://computervisionandjava.blogspot.com/2013/10/java-opencv-webcam.html

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Mat2Image {
    Mat mat = new Mat();
    BufferedImage img;
    byte[] dat;
    public Mat2Image() {
    }
    public Mat2Image(Mat mat) {
        getSpace(mat);
    }
    public void getSpace(Mat mat) {
        this.mat = mat;
        Imgproc.cvtColor(mat,mat,Imgproc.COLOR_RGB2BGR);
        Core.flip(mat,mat,1);
        int w = mat.cols(), h = mat.rows();
        if (dat == null || dat.length != w * h * 3)
            dat = new byte[w * h * 3];
        if (img == null || img.getWidth() != w || img.getHeight() != h
                || img.getType() != BufferedImage.TYPE_3BYTE_BGR)
            img = new BufferedImage(w, h,
                    BufferedImage.TYPE_3BYTE_BGR);
    }
    BufferedImage getImage(Mat mat){
        getSpace(mat);
        mat.get(0, 0, dat);
        img.getRaster().setDataElements(0, 0,
                mat.cols(), mat.rows(), dat);

        int[][][][][] colourPanel = new int[3][3][5][5][3];

        for(int a=0; a<3; a++){
            for(int b=0; b<3; b++){
                for(int c=0; c<5; c++){
                    for(int d=0; d<5; d++){
                        int pixelColour = img.getRGB(268 + 35 * a + 5 * (c+1), 188 + 35 * b + 5 * (d+1));
                        int red = (pixelColour >> 16) & 0xFF;
                        int green = (pixelColour >> 8) & 0xFF;
                        int blue = (pixelColour) & 0xFF;
                        colourPanel[a][b][c][d][0] = red;
                        colourPanel[a][b][c][d][1] = green;
                        colourPanel[a][b][c][d][2] = blue;
                    }
                }
            }
        }

        ColourChecker.colourCondense(colourPanel);

        return img;
    }
    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
}