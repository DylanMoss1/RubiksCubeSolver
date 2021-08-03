package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Formula_Creater;

import java.util.Arrays;
import java.util.Scanner;

public class ConvertToRubiksFormula {
    public static void main(String[] args) {

        while(true) {
            boolean array = true;

            Scanner sc = new Scanner(System.in);

            String moves = sc.nextLine();

            moves = moves.replace("[", "");
            moves = moves.replace("]", "");
            moves = moves.replace("(", "");
            moves = moves.replace(")", "");
            moves = moves.replace("{", "");
            moves = moves.replace("}", "");
            moves = moves.replace("'", "P");

            if (!array) {
                System.out.println(moves);
            } else {
                String[] movesArray = moves.split(" ");
                System.out.print("{");
                for (int i = 0; i < movesArray.length; i++) {
                    if (i == movesArray.length - 1) {
                        System.out.print("\"" + movesArray[i] + "\"");
                    } else {
                        System.out.print("\"" + movesArray[i] + "\"" + ",");
                    }
                }
                System.out.print("},");
                System.out.println("");
            }
        }
    }
}
