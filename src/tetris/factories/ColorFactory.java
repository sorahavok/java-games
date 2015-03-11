package tetris.factories;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ColorFactory {

	private final static int ALPHA = 160;
	private static List<Color> colors = new ArrayList<>();
	private static List<String> colorNames = new ArrayList<>();
	
	public static void addColor(Color c){
		Color alphaColor = new Color(c.getRed(), c.getGreen(), c.getBlue(), ALPHA);		
		colors.add(alphaColor);
	}
	
	public static Color getColor(int index){
		return colors.get(index);
	}

	public static List<String> getColorNameArray() {
		return colorNames;
	}

	public static void addColor(String s) {
		try {
			Color c = (Color) Color.class.getField(s).get(null);
			Color alphaColor = new Color(c.getRed(), c.getGreen(), c.getBlue(), ALPHA);		
			colors.add(alphaColor);
			colorNames.add(s);
		}
		catch (IllegalArgumentException | SecurityException | IllegalAccessException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		
	}
}
