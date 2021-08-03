package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Webcam;

//Credits to computervisionandjava for parts of webcam code
//http://computervisionandjava.blogspot.com/2013/10/java-opencv-webcam.html

import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

public class VideoCap {
    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    VideoCapture cap;
    Mat2Image mat2Img = new Mat2Image();

    VideoCap(){
        cap = new VideoCapture();
        cap.open(0);
    }

    BufferedImage getOneFrame() {
        cap.read(mat2Img.mat);
        return mat2Img.getImage(mat2Img.mat);
    }
}