package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_Cube_Manager;

import org.lwjgl.system.CallbackI;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rendering.Camera;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_Cube_Manager.Rubiks_Cube.RubiksCube;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_CFOP.Managers.CFOP_Manager;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rendering.SkyBox;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Webcam.ConstructCubie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.function.DoubleToIntFunction;

public class RubiksCubeManager {

    RubiksCube mainRubiksCube;

    public RubiksCubeManager(){
    }

    public void createCube(){
        mainRubiksCube = new RubiksCube();
    }

    public void createCube(ConstructCubie[][][] cubieList){
        mainRubiksCube = new RubiksCube(cubieList);
    }

    public void setSkyBox(SkyBox skyBox){
        mainRubiksCube.skybox = skyBox;
    }


    public void renderCube(Camera camera){
        mainRubiksCube.render(camera);
    }

    public void makeMove(String move){
        mainRubiksCube.move(move);
    }


    public void makeMove(String[] moves){
        for(String move : moves) {
            mainRubiksCube.move(move);
        }
    }

    public void solveLayer(){
        String[] moves = CFOP_Manager.solveLayer(mainRubiksCube.renderCubiesList);
        System.out.println("");
        System.out.println("");
        System.out.println("MOVES:");
        System.out.println(Arrays.toString(sanitise(moves)));

        String[] makeMoves = moveOptimise(moves);
        //System.out.println(Arrays.toString(makeMoves));
        makeMove(moves);

        String[] printMove = printOptimise(moves);
        //System.out.println(Arrays.toString(printMove));
    }

    public String[] moveOptimise(String[] moves){

        moves = split(moves);

        ArrayList<String> oldMoves = new ArrayList<>();
        ArrayList<String> newMoves = new ArrayList<>();

        for(String move : moves){
            oldMoves.add(move);
        }

        int count;
        int rot;
        int newRot;
        int storeCount;
        boolean complete = false;

        for(String move : moves){
            oldMoves.add(move);
        }

        while(!complete) {
            //System.out.println("repeat");
            storeCount = 0;
            newMoves = new ArrayList<>();
            for (int i = 0; i < oldMoves.size(); i++) {

                char first = oldMoves.get(i).charAt(0);
                if (first == 'z' || first == 'v' || first == 'e' || first == 'q' || first == 'w' || first == 'g' || first == 'o') {
                    newMoves.add(oldMoves.get(i));
                } else {
                    count = 0;
                    rot = 0;
                    while (i + count < oldMoves.size()) {
                        if (oldMoves.get(i + count).substring(0, 1).equals(oldMoves.get(i).substring(0, 1))) {
                            rot += getRot(oldMoves.get(i + count));
                            count++;
                        } else {
                            break;
                        }
                    }
                    if (storeCount <= 0) {
                        newRot = rot % 4;

                        if (newRot < 0) {
                            newRot += 4;
                        }

                        storeCount = count;
                        if (newRot != 0) {
                            newMoves.add(findRotMove(newRot, oldMoves.get(i)));
                        }
                    }
                }
                storeCount--;
            }

            if(oldMoves.equals(newMoves)){
                complete = true;
            }

            oldMoves = newMoves;
        }

        String[] newMovesArr = new String[newMoves.size()];
        for(int j=0; j<newMoves.size(); j++){
            newMovesArr[j] = newMoves.get(j);
        }
        return newMovesArr;
    }

    public String[] printOptimise(String[] moves){

        moves = sanitise(moves);
        moves = split(moves);

        //System.out.println(Arrays.toString(moves));

        ArrayList<String> oldMoves = new ArrayList<>();
        ArrayList<String> newMoves = new ArrayList<>();

        for(String move : moves){
            oldMoves.add(move);
        }

        int count;
        int rot;
        int newRot;
        int storeCount;
        boolean complete = false;

        while(!complete) {
            //System.out.println("repeat");
            storeCount = 0;
            newMoves = new ArrayList<>();
            for (int i = 0; i < oldMoves.size(); i++) {
                count = 0;
                rot = 0;
                while (i + count < oldMoves.size()) {
                    //System.out.println(oldMoves.get(i).substring(0,1));
                    if (oldMoves.get(i + count).substring(0,1).equals(oldMoves.get(i).substring(0,1))) {
                        //System.out.println(oldMoves.get(i).substring(0,1));
                        rot += getRot(oldMoves.get(i + count));
                        count++;
                    } else {
                        /*
                        System.out.println(oldMoves.get(i).substring(0,1));
                        System.out.println(oldMoves.get(count + i).substring(0,1));
                        System.out.println(" ");
                        break;

                         */
                        break;
                    }
                }
                if(storeCount <= 0) {

                    //System.out.println(rot);
                    newRot = rot % 4;

                    if(newRot < 0){
                        newRot += 4;
                    }

                    storeCount = count;
                    if(newRot != 0) {
                        //System.out.println(findRotMove(newRot, oldMoves.get(i)));
                        newMoves.add(findRotMove(newRot, oldMoves.get(i)));
                    }
                }
                storeCount--;
            }

            if(oldMoves.equals(newMoves)){
                complete = true;
            }

            oldMoves = newMoves;
        }

        String[] newMovesArr = new String[newMoves.size()];
        for(int j=0; j<newMoves.size(); j++){
            newMovesArr[j] = newMoves.get(j);
        }
        return newMovesArr;
    }

    public String reverseMove(String move){
        if(move.length() == 2){
            if(move.substring(1,2).equals("2")){
                return move;
            } else {
                return move.substring(0, 1);
            }
        } else {
            return (move + "P");
        }
    }

    public int getRot(String move){
        if(move.length() == 1){
            return 1;
        } else {
            if(move.substring(1,2).equals("2")){
                return 2;
            } else {
                return -1;
            }
        }
    }

    public String findRotMove(int rot, String move){
        String first = move.substring(0,1);
        if(rot == 1){
            return first;
        } else if(rot == 2){
            return (first + "2");
        } else {
            return (first + "P");
        }
        /*
        if(move.length() == 1){

            if(rot == 1){
                return move;
            } else if(rot == 2){
                return (move + "2");
            } else{
                return (move + "R");
            }
        } else {
            if(rot == 1){
                return move;
            } else if(rot == 2){
                return(move.substring(0,1) + "2");
            } else {
                return(move.substring(0,1));
            }
        }
        */
    }

    public String[] sanitise(String[] moves){
        LinkedList<String> newList = new LinkedList<>();

        for(String move : moves){
            if(move.length() == 2) {
                char first = move.charAt(0);
                char second = move.charAt(1);
                if (first == 'z' || first == 'v' || first == 'e' || first == 'q' || first == 'w' || first == 'g' || first == 'o') {
                    newList.addAll(findAlg(first, second));
                } else {
                    newList.add(move);
                }
            } else {
                newList.add(move);
            }
        }
        String[] newArray = new String[newList.size()];
        for(int i=0; i<newList.size(); i++){
            newArray[i] = newList.get(i);
        }
        return newArray;
    }


    public ArrayList<String> findAlg(char first, char second){

        LinkedList<String> algList = new LinkedList<>();
        ArrayList<String> alg = new ArrayList<>();

        if(second == '2'){
            algList.add("y");
        } else if(second == '3'){
            algList.add("y2");
        } else if(second == '4'){
            algList.add("yP");
        }

        if(first == 'q'){
            algList.add("U");
            algList.add("R");
            algList.add("UP");
            algList.add("RP");
            algList.add("UP");
            algList.add("FP");
            algList.add("U");
            algList.add("F");
        } else if (first == 'e'){
			/*
			algList.add("UP");
			algList.add("LP");
			algList.add("U");
			algList.add("L");
			algList.add("U");
			algList.add("F");
			algList.add("UP");
			algList.add("FP");

			 */

            algList.add("UP");
            algList.add("LP");
            algList.add("UP");
            algList.add("L");
            algList.add("U");
            algList.add("F");
            algList.add("U");
            algList.add("FP");

        } else if (first == 'w'){
            algList.add("FP");
            algList.add("UP");
            algList.add("F");
        } else if (first == 'z'){
            algList.add("F");
            algList.add("U");
            algList.add("FP");
        } else if (first == 'v'){
            algList.add("FP");
            algList.add("LP");
            algList.add("U");
            algList.add("U");
            algList.add("L");
            algList.add("F");
        } else if (first == 'o'){
            algList.add("R");
            algList.add("U");
            algList.add("RP");
            algList.add("UP");
        } else if (first == 'g'){
            algList.add("R");
            algList.add("U");
            algList.add("RP");
            algList.add("U");
            algList.add("U");
            algList.add("R");
            algList.add("U");
            algList.add("U");
            algList.add("RP");
            algList.add("U");
            algList.add("FP");
            algList.add("UP");
            algList.add("F");
        }

        if(second == '2'){
            algList.add("yP");
        } else if(second == '3'){
            algList.add("y2");
        } else if(second == '4'){
            algList.add("y");
        }

        for(int i=0; i<algList.size(); i++){
            alg.add(algList.get(i));
        }

        return alg;
    }

    public String[] split(String[] moves){
        LinkedList<String> newList = new LinkedList<>();
        for(String move : moves){
            if(move.length() == 2){
                char first = move.charAt(0);
                char second = move.charAt(1);
                if (!(first == 'z' || first == 'v' || first == 'e' || first == 'q' || first == 'w' || first == 'g' || first == 'o')) {
                    if (second == '2') {
                        newList.add(move.substring(0, 1));
                        newList.add(move.substring(0, 1));
                    } else {
                        newList.add(move);
                    }
                } else {
                    newList.add(move);
                }
            } else {
                newList.add(move);
            }
        }
        String[] newArray = new String[newList.size()];
        for(int i=0; i<newList.size(); i++){
            newArray[i] = newList.get(i);
        }
        return newArray;
    }

}