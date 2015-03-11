package tetris.control;

import java.util.LinkedHashMap;
import java.util.Map;

public class ControllerInput {
	public static final String UP = "Up", DOWN = "Down", LEFT = "Left", RIGHT = "Right", ROTATE_CW = "Rotate CW", ROTATE_CCW = "Rotate CCW",
			PAUSE = "Pause", EXIT = "Exit";
	
	private final Map<String, Integer> controlMap;

	public ControllerInput(int up, int down, int left, int right, int exit, int pause, int rotateCW, int rotateCCW) {
		controlMap = new LinkedHashMap<>(8);
		controlMap.put(UP, up);
		controlMap.put(DOWN, down);
		controlMap.put(RIGHT, right);
		controlMap.put(LEFT, left);
		controlMap.put(ROTATE_CCW, rotateCCW);
		controlMap.put(ROTATE_CW, rotateCW);
		controlMap.put(PAUSE, pause);
		controlMap.put(EXIT, exit);
	}

//	public ControllerInput(List<Integer> controls) {
//		this(controls.get(0), controls.get(1), controls.get(2), controls.get(3), controls.get(4), controls.get(5),controls.get(6),controls.get(7));
//	}
	
	public ControllerInput(Map<String, Integer> controls) {
		controlMap = controls;
	}

	public Input getInput(int keyCode) {
		if (keyCode == controlMap.get(UP))
			return Input.UP;
		else if (keyCode == controlMap.get(DOWN))
			return Input.DOWN;
		else if (keyCode == controlMap.get(LEFT))
			return Input.LEFT;
		else if (keyCode == controlMap.get(RIGHT))
			return Input.RIGHT;
		else if (keyCode == controlMap.get(ROTATE_CW))
			return Input.ROTATE_CW;
		else if (keyCode == controlMap.get(ROTATE_CCW))
			return Input.ROTATE_CCW;
		else if (keyCode == controlMap.get(PAUSE))
			return Input.PAUSE;
		else if (keyCode == controlMap.get(EXIT))
			return Input.EXIT;
		else {
			System.err.println("Invalid Key");
			return Input.INVALID_KEY;
		}
	}

	public Map<String, Integer> getMap() {
		return controlMap;
	}
}
