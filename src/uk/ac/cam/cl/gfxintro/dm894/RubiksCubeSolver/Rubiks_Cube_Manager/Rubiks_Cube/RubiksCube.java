package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_Cube_Manager.Rubiks_Cube;

import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rendering.Camera;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Render_Cubies.RenderCubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rendering.SkyBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


public class RubiksCube {

	public RenderCubie[][][] renderCubiesList = new RenderCubie[3][3][3];

	public SkyBox skybox;

	public boolean preprogram = false;

	public float totalTime;
	public float centreRotate;
	public Scanner sc;
	public String movesInput;
	public String[] movesArray;
	public ArrayList<String> movesList = new ArrayList<String>();
	public ArrayList<String> algList = new ArrayList<>();
	public boolean nextMove = true;
	public int moveCounter = 0;
	String currentMove = "";
	public int timeConstant = 1;

	public RubiksCube() {

		if(preprogram) {
			Scanner sc = new Scanner(System.in);
			movesInput = sc.nextLine();
			movesArray = movesInput.split(" ");
			for(String moves : movesArray){
				movesList.add(moves);
			}
		}

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				for (int k = -1; k <= 1; k++) {
					int index = (i + 1) + (j + 1) * 3 + (k + 1) * 9;
					if(i != 0 && j != 0 && k != 0) {
						renderCubiesList[i + 1][j + 1][k + 1] = new RenderCubie(index, i, j, k, timeConstant);
					} else {
						renderCubiesList[i + 1][j + 1][k + 1] = new RenderCubie(index,i, j, k, timeConstant);
					}
				}
			}
		}
	}

	public void move(String move){
		movesList.add(move);
	}

	public void render(Camera camera) {

		makeMove();

		for (RenderCubie[][] cubie1 : renderCubiesList) {
			for (RenderCubie[] cubie2 : cubie1) {
				for (RenderCubie cubie : cubie2) {
					cubie.render(camera, currentMove);
				}
			}
		}
	}

	public void makeMove(){
		boolean doubled = false;
		boolean wait = false;

		if(nextMove){
			if(algList.size() != 0){
				currentMove = algList.get(0);
				if (currentMove.length() == 2) {
					char first = currentMove.charAt(0);
					char second = currentMove.charAt(1);
					if (second == '2') {
						currentMove = String.valueOf(first);
						doubled = true;
					}
				}
				moveCounter = 0;
				algList.remove(0);
				nextMove = false;
				if(doubled){
					Collections.reverse(algList);
					algList.add(currentMove);
					Collections.reverse(algList);
				}
			} else if(movesList.size() != 0){
				currentMove = movesList.get(0);
				if (currentMove.length() == 2) {
					char first = currentMove.charAt(0);
					char second = currentMove.charAt(1);
					/*
					if(first == 'e' && second == '1') {

						wait = true;
						currentMove = "";
						movesList.remove(0);
						Collections.reverse(movesList);

						movesList.add("FP");
						movesList.add("UP");
						movesList.add("F");
						movesList.add("U");
						movesList.add("L");
						movesList.add("U");
						movesList.add("LP");
						movesList.add("UP");

						Collections.reverse(movesList);

					*/
					if(first == 'z' || first == 'v' || first == 'e' || first == 'q' || first == 'w' || first == 'g' || first == 'o'){
						//algList = findAlg(first,second);
						//currentMove = "";


						wait = true;
						currentMove = "";
						movesList.remove(0);
						Collections.reverse(movesList);
						ArrayList<String> newAlg = findAlg(first,second);
						for(int i=0; i< newAlg.size(); i++){
							movesList.add(newAlg.get(i));
						}
						Collections.reverse(movesList);
					} else if (second == '2') {
						currentMove = String.valueOf(first);
						doubled = true;
					}
				} /*else if(currentMove.length() == 3){
					movesList.remove(0);
					//makeMove();
					//perm("T");
					nextMove = true;
				}
				*/
				moveCounter = 0;
				if(!wait) {
					movesList.remove(0);
					nextMove = false;
				}
				if(doubled){
					Collections.reverse(movesList);
					movesList.add(currentMove);
					Collections.reverse(movesList);
				}
			} else {
				currentMove = "";
			}
		}

		if(currentMove != "") {
			if (moveCounter == timeConstant - 1) {
				//System.out.println(currentMove);
				nextMove = true;
				reconstructCubeList();
				//makeMove();
			} else {
				moveCounter++;
			}
		}
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
/*
	public void makeMove(){
		reconstructCubeList();
		checkSolved();
	}
	*/

	public void reconstructCubeList(){
		RenderCubie[][][] newCubiesList = new RenderCubie[3][3][3];
		for (RenderCubie[][] cubie1 : renderCubiesList) {
			for (RenderCubie[] cubie2 : cubie1) {
				for (RenderCubie cubie : cubie2) {
					newCubiesList[Math.round(cubie.position.x+1)][Math.round(cubie.position.y+1)][Math.round(cubie.position.z+1)] = cubie;
				}
			}
		}
		renderCubiesList = newCubiesList;
	}

/*
	public void checkSolved(){
		boolean solved = true;
		for (int i = -1; i <= 1; i++) {
			for (Cubie[][] cubie1 : renderCubiesList) {
				for (Cubie[] cubie2 : cubie1) {
					for (Cubie cubie : cubie2) {
						if(!cubie.positionCorrect()){
							solved = false;
						}
					}
				}
			}
		}
		if(solved){
			System.out.println("Solved Cube");
		}
	}

 */
/*
	public void solveLayer(String layer){
		if(layer.equals("PLL")){
			String[] newMoves = CFOP_P.Apply(renderCubiesList);
			for(String move : newMoves){
				movesList.add(move);
			}
			System.out.println(movesList);
		}
	}

 */
	/*

	public Cubie[][][] copyCube() {
		Cubie[][][] newCubieList = new Cubie[3][3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					newCubieList[i][j][k] = renderCubiesList[i][j][k].copy();
				}
			}
		}
		return newCubieList;
	}

	 */
}
