package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class OptimiseTest {
    public static void main(String[] args) {
        OptimiseTest optimiseTest = new OptimiseTest();
        optimiseTest.solveLayer();
    }

    public void solveLayer(){

        String[] moves = new String[]{"RP","R"};

        System.out.println("MOVES");
        System.out.println(Arrays.toString(moves));

        String[] makeMoves = moveOptimise(moves);
        System.out.println(Arrays.toString(makeMoves));

        String[] printMove = printOptimise(moves);
        System.out.println(Arrays.toString(printMove));
    }

    public String[] moveOptimise(String[] moves){

        moves = split(moves);

        LinkedList<String> oldMoves = new LinkedList<>();
        LinkedList<String> newMoves1;
        LinkedList<String> newMoves2 = new LinkedList<>();

        for(String move : moves){
            oldMoves.add(move);
        }

        int count;
        int newCount;
        int storeCount;
        boolean complete = false;

        while(!complete) {
            storeCount = 0;
            newMoves1 = new LinkedList<>();
            newMoves2 = new LinkedList<>();
            for (int i = 0; i < oldMoves.size(); i++) {
                count = 0;
                while (i + count < oldMoves.size()) {
                    if (oldMoves.get(i + count) == oldMoves.get(i)) {
                        count++;
                    } else {
                        break;
                    }
                }
                if(storeCount <= 0) {
                    newCount = count % 4;
                    storeCount = count;
                    if (newCount == 1) {
                        newMoves1.add(oldMoves.get(i));
                    } else if (newCount == 2) {
                        newMoves1.add(oldMoves.get(i));
                        newMoves1.add(oldMoves.get(i));
                    } else if (newCount == 3) {
                        newMoves1.add(reverseMove(oldMoves.get(i)));
                    } else {
                        break;
                    }
                }
                storeCount--;
            }

            for (int i = 0; i < newMoves1.size(); i++) {
                if (i + 1 < newMoves1.size()) {
                    if (!(newMoves1.get(i) == reverseMove(newMoves1.get(i+1)))){
                        newMoves2.add(newMoves1.get(i));
                    }
                } else {
                    newMoves2.add(newMoves1.get(i));
                }
            }

            if(oldMoves.equals(newMoves2)){
                complete = true;
            }

            oldMoves = newMoves2;
        }

        String[] newMovesArr = new String[newMoves2.size()];
        for(int j=0; j<newMoves2.size(); j++){
            newMovesArr[j] = newMoves2.get(j);
        }
        return newMovesArr;
    }

    public String[] printOptimise(String[] moves){

        moves = sanitise(moves);
        moves = split(moves);

        //System.out.println(Arrays.toString(moves));

        LinkedList<String> oldMoves = new LinkedList<>();
        LinkedList<String> newMoves1;
        LinkedList<String> newMoves2 = new LinkedList<>();

        for(String move : moves){
            oldMoves.add(move);
        }

        int count;
        int rot;
        int newRot;
        int storeCount;
        boolean complete = false;

        while(!complete) {
            //System.out.println("loop");
            //System.out.println("repeat");
            storeCount = 0;
            newMoves1 = new LinkedList<>();
            newMoves2 = new LinkedList<>();
            for (int i = 0; i < oldMoves.size(); i++) {
                count = 0;
                rot = 0;
                while (i + count < oldMoves.size()) {
                    //System.out.println(oldMoves.get(i).substring(0,1));
                    if (oldMoves.get(i + count).substring(0,1).equals(oldMoves.get(i).substring(0,1))) {
                        //System.out.println(oldMoves.get(i).substring(0,1));
                        rot += getRot(oldMoves.get(i + count));
                        count++;
                        //System.out.println("rot");
                        //System.out.println(rot);
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

                    //System.out.println(newRot);
                    if(newRot != 0) {
                        //System.out.println(findRotMove(newRot, oldMoves.get(i)));
                        newMoves1.add(findRotMove(newRot, oldMoves.get(i)));
                    }
                }
                storeCount--;
            }

            boolean skip = false;

            for (int i = 0; i < newMoves1.size(); i++) {
                //System.out.println(i);
                if(skip == true){
                    skip = false;
                } else {
                    if (i + 1 < newMoves1.size()) {
                        //System.out.println(newMoves1.get(i));
                        //System.out.println(newMoves1.get(i+1));
                        //System.out.println(reverseMove(newMoves1.get(i+1)));
                        if (!(newMoves1.get(i).equals(reverseMove(newMoves1.get(i+1))))){
                            newMoves2.add(newMoves1.get(i));
                        } else {
                            //System.out.println("skip");
                            skip = true;
                        }
                    } else {
                        //System.out.println("Nope");
                        newMoves2.add(newMoves1.get(i));
                    }
                }
            }
            //System.out.println("new moves 2");
            //System.out.println(newMoves2);



            if(oldMoves.equals(newMoves2)){
                complete = true;
            }

            oldMoves = newMoves2;
        }
        //System.out.println("---");
        //System.out.println(newMoves2);
        String[] newMovesArr = new String[newMoves2.size()];
        for(int j=0; j<newMoves2.size(); j++){
            newMovesArr[j] = newMoves2.get(j);
        }
        //System.out.println(newMovesArr);
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
        Collections.reverse(alg);
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


