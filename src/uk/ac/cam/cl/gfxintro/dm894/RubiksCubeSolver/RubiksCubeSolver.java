package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rendering.OpenGLApplication;

public class RubiksCubeSolver {

    public static void usageError() {
        System.err.println("USAGE: <RubiksCubeSolver> [--output OUTPUT]");
        System.exit(-1);
    }

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

        OpenGLApplication app = null;
        try {
            app = new OpenGLApplication();

            if (output != null) {
                app.initialize();
                app.takeScreenshot(output);
            } else {
                app.run();
            }
        } catch (Exception e) {
			e.printStackTrace();
		} finally {
            if (app != null)
                app.stop();
        }
    }
}