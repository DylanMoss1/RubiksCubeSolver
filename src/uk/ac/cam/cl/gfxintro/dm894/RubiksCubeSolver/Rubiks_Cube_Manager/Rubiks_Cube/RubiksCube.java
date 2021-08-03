package uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rubiks_Cube_Manager.Rubiks_Cube;

import org.joml.Matrix3f;
import org.joml.Vector3f;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rendering.Camera;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Cubies.Render_Cubies.RenderCubie;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Rendering.SkyBox;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.RubiksCubeSolver;
import uk.ac.cam.cl.gfxintro.dm894.RubiksCubeSolver.Webcam.ConstructCubie;

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
	public int timeConstant;

	public RubiksCube() {

		timeConstant = RubiksCubeSolver.frame_delay;

		if (preprogram) {
			Scanner sc = new Scanner(System.in);
			movesInput = sc.nextLine();
			movesArray = movesInput.split(" ");
			for (String moves : movesArray) {
				movesList.add(moves);
			}
		}

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				for (int k = -1; k <= 1; k++) {
					int index = (i + 1) + (j + 1) * 3 + (k + 1) * 9;
					renderCubiesList[i + 1][j + 1][k + 1] = new RenderCubie(index, i, j, k, timeConstant);
				}
			}
		}
	}

	public RubiksCube(ConstructCubie[][][] cubieList) {

		timeConstant = RubiksCubeSolver.frame_delay;

		if (preprogram) {
			Scanner sc = new Scanner(System.in);
			movesInput = sc.nextLine();
			movesArray = movesInput.split(" ");
			for (String moves : movesArray) {
				movesList.add(moves);
			}
		}

		ArrayList<Integer> numList = new ArrayList<>();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				for (int k = -1; k <= 1; k++) {
					ConstructCubie constructCubie = cubieList[i + 1][j + 1][k + 1];
					ConstructCubie constructCubie2 = cubieList[i + 1][j + 1][2 - (k + 1)];
					numList.add(constructCubie.index);
					int index = constructCubie.index;
					Vector3f position = constructCubie.position;
					Matrix3f orientation = constructCubie2.orientation;
					renderCubiesList[i + 1][j + 1][k + 1] = new RenderCubie(index, (int) position.x - 1, (int) position.y - 1, (int) position.z - 1, orientation, timeConstant);
					/*
					if (index == 6) {
						System.out.println(position.x);
						System.out.println(position.y);
						System.out.println(position.z);
						System.out.println(orientation);
						System.out.println("Hmmmmmmm");
						RenderCubie renderCubie = renderCubiesList[0][2][0];

						System.out.println(renderCubie.position);
						System.out.println(renderCubie.orientation);
					}

					 */
				}
			}
		}

		Collections.sort(numList);
		//System.out.println(numList.toString());

		//System.out.println("####");
		RenderCubie renderCubie = renderCubiesList[2][1][0];

		//System.out.println(renderCubie.position);
		//System.out.println(renderCubie.orientation);
	}

	public void move(String move) {
		movesList.add(move);
	}

	public void render(Camera camera) {

		makeMove();

		RenderCubie renderCubie = renderCubiesList[2][1][0];

		for (RenderCubie[][] cubie1 : renderCubiesList) {
			for (RenderCubie[] cubie2 : cubie1) {
				for (RenderCubie cubie : cubie2) {
					if (cubie != null) {
						cubie.render(camera, currentMove);
					}
				}
			}
		}

	}

	public void makeMove() {
		boolean doubled = false;
		boolean wait = false;

		if (nextMove) {
			if (algList.size() != 0) {
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
				if (doubled) {
					Collections.reverse(algList);
					algList.add(currentMove);
					Collections.reverse(algList);
				}
			} else if (movesList.size() != 0) {
				currentMove = movesList.get(0);
				if (currentMove.length() == 2) {
					char first = currentMove.charAt(0);
					char second = currentMove.charAt(1);

					if (first == 'z' || first == 'v' || first == 'e' || first == 'q' || first == 'w' || first == 'g' || first == 'o') {
						wait = true;
						currentMove = "";
						movesList.remove(0);
						Collections.reverse(movesList);
						ArrayList<String> newAlg = findAlg(first, second);
						for (int i = 0; i < newAlg.size(); i++) {
							movesList.add(newAlg.get(i));
						}
						Collections.reverse(movesList);
					} else if (second == '2') {
						currentMove = String.valueOf(first);
						doubled = true;
					}
				}
				moveCounter = 0;
				if (!wait) {
					movesList.remove(0);
					nextMove = false;
				}
				if (doubled) {
					Collections.reverse(movesList);
					movesList.add(currentMove);
					Collections.reverse(movesList);
				}
			} else {
				currentMove = "";
			}
		}

		if (currentMove != "") {
			if (moveCounter == timeConstant - 1) {
				nextMove = true;
				reconstructCubeList();
			} else {
				moveCounter++;
			}
		}
	}

	public ArrayList<String> findAlg(char first, char second) {

		LinkedList<String> algList = new LinkedList<>();
		ArrayList<String> alg = new ArrayList<>();

		if (second == '2') {
			algList.add("y");
		} else if (second == '3') {
			algList.add("y2");
		} else if (second == '4') {
			algList.add("yP");
		}

		if (first == 'q') {
			algList.add("U");
			algList.add("R");
			algList.add("UP");
			algList.add("RP");
			algList.add("UP");
			algList.add("FP");
			algList.add("U");
			algList.add("F");
		} else if (first == 'e') {
			algList.add("UP");
			algList.add("LP");
			algList.add("UP");
			algList.add("L");
			algList.add("U");
			algList.add("F");
			algList.add("U");
			algList.add("FP");
		} else if (first == 'w') {
			algList.add("FP");
			algList.add("UP");
			algList.add("F");
		} else if (first == 'z') {
			algList.add("F");
			algList.add("U");
			algList.add("FP");
		} else if (first == 'v') {
			algList.add("FP");
			algList.add("LP");
			algList.add("U");
			algList.add("U");
			algList.add("L");
			algList.add("F");
		} else if (first == 'o') {
			algList.add("R");
			algList.add("U");
			algList.add("RP");
			algList.add("UP");
		} else if (first == 'g') {
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

		if (second == '2') {
			algList.add("yP");
		} else if (second == '3') {
			algList.add("y2");
		} else if (second == '4') {
			algList.add("y");
		}

		for (int i = 0; i < algList.size(); i++) {
			alg.add(algList.get(i));
		}
		Collections.reverse(alg);
		return alg;
	}

	public void reconstructCubeList() {
		RenderCubie[][][] newCubiesList = new RenderCubie[3][3][3];
		for (RenderCubie[][] cubie1 : renderCubiesList) {
			for (RenderCubie[] cubie2 : cubie1) {
				for (RenderCubie cubie : cubie2) {
					newCubiesList[Math.round(cubie.position.x + 1)][Math.round(cubie.position.y + 1)][Math.round(cubie.position.z + 1)] = cubie;
				}
			}
		}
		renderCubiesList = newCubiesList;
	}
}