package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rendering.OpenGLApplication;

/* Main class for setting up the OpenGL application */
public class RubiksCubeSolver {

    public static int frame_delay = 1;  //Number of millisecond delays between frame renders
    public static boolean webcam = false;  //Whether to set up the webcam pipeline or not

    /* Raises error at incorrect usage of this program */
    public static void usageError() {
        System.err.println("USAGE: <RubiksCubeSolver> [--output OUTPUT]");
        System.exit(-1);
    }

    /* Accepts all program inputs */
    public static void main(String[] args) {
        // We should have an even number of arguments
        if (args.length % 2 != 0)
            usageError();

        String output = null;
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
            case "-o":
            case "--output":
                output = args[i + 1];
                break;
            default:
                System.err.println("unknown option: " + args[i]);
                usageError();
            }
        }
        run();
    }

    /* Sets up OpenGL application in screenshot or render mode */
    public static void run(){
        String output = null;
        OpenGLApplication app = null;
        try {
            app = new OpenGLApplication();

            if (output != null) {  //If an output is specified, take a screenshot of the scene
                app.initialize();
                app.takeScreenshot(output);
            } else {
                app.run();  //Otherwise run the main the OpenGL application
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (app != null)
                app.stop();  //Release OpenGL resources at the end of the program's lifecycle
        }
    }
}
