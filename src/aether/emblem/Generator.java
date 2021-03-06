package aether.emblem;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Generator {
	private static final String IMG_PATH = "res/aether-emblem/";
	private static final int BLUE_TEAM = 1;
	private static final List<Character> ListBlue = new ArrayList<>();
	private static final List<Character> ListRed = new ArrayList<>();

	public static void addCharacter(Character character) {
		List<Character> team = character.getTeam() == BLUE_TEAM ? ListBlue : ListRed;
		team.add(character);
	}

	public static List<Character> getBlueList() {
		return ListBlue;
	}

	public static List<Character> getRedList() {
		return ListRed;
	}

	public static String toString(List<Character> list) {
		return list.stream()
				   .map(Character::toString)
				   .collect(Collectors.joining("\n"));
	}

	// Weapon -- Weapon -- Weapon -- Weapon -- Weapon -- Weapon --
	// Name , Power , Hit Chance
	static Weapon Axe = new Weapon("Axe", 15, 50);
	static Weapon Sword = new Weapon("Sword", 5, 85);
	static Weapon Lance = new Weapon("Lance", 10, 60);
	static Weapon Oathkeeper = new Weapon("Oathkeeper", 90, 20);
	static Weapon SoulEater = new Weapon("SoulEater", 80, 25);
	static Weapon Keyblade = new Weapon("Keyblade", 100, 15);
	// Character Images -- Character Images -- Character Images --
	static Image remove = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "remove.png");
	static Image heroImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "BlueHero.png");
	static Image grayheroImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "GrayBlueHero.png");
	static Image lanceImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "BluePikeman.png");
	static Image graylanceImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "GrayBluePikeman.png");
	static Image swordImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "BlueSwordMaster.png");
	static Image grayswordImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "GrayBlueSwordMaster.png");
	static Image axeImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "BlueFighter.png");
	static Image grayaxeImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "GrayBlueFighter.png");

	static Image redheroImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "RedHero.png");
	static Image grayredheroImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "GrayRedHero.png");
	static Image redlanceImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "RedPikeman.png");
	static Image grayredlanceImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "GrayRedPikeman.png");
	static Image redswordImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "RedSwordMaster.png");
	static Image grayredswordImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "GrayRedSwordMaster.png");
	static Image redaxeImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "RedFighter.png");
	static Image grayredaxeImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "GrayRedFighter.png");

	static Image FaceFighterFemale = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "FaceFighterFemale.png");
	static Image FaceFighterMale = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "FaceFighterMale.png");
	static Image FaceHeroFemale = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "FaceHeroFemale.png");
	static Image FaceHeroMale = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "FaceHeroMale.png");
	static Image FacePikemanFemale = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "FacePikemanFemale.png");
	static Image FacePikemanMale = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "FacePikemanMale.png");
	static Image FaceSwordFemale = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "FaceSwordFemale.png");
	static Image FaceSwordMale = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "FaceSwordMale.png");

	static Image ThiefImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "Thief.png");
	// Terrain -- Terrain -- Terrain -- Terrain -- Terrain -- Terrain -- Terrain --
	static Image BackgroundImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "Background.png");
	static Image grassImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "Tile_Grass.jpg");
	static Image wallImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "Tile_Wall.jpg");
	static Image waterImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "Tile_Water.jpg");
	static Image bridgeImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "bridge.png");
	// Int Declaration -- Int Declaration -- Int Declaration -- Int Declaration --
	static int mouseMoveX, mouseMoveY;
	static int countteam1 = 1;
	static int countteam2 = 1;
	static int appletWidth = 600;
	static int appletHeight = 600;
	// Field Boxes -- Field Boxes -- Field Boxes -- Field Boxes -- Field Boxes --
	static Image WhiteTextBg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "WhiteTextBg.png");
	static Image BlueTextBg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "BlueTextBg.png");
	static Image RedTextBg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "RedTextBg.png");
	static Image Red_SquareImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "Red_Square.png");
	static Image Green_SquareImg = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "Green_Square.png");
	// Map -- Map -- Map -- Map -- Map -- Map -- Map -- Map -- Map -- max size is about 20
	static Field Map = new Field(25, 4);
	static int picSize = appletHeight / Map.getSize();

	static InfoPannel InfoScreen = new InfoPannel();
	static Image imageRaw = Toolkit.getDefaultToolkit().getImage(IMG_PATH + "Blue_Square.png");
	static Image image = imageRaw.getScaledInstance(picSize, picSize, 1);
	static FieldDriver Field = new FieldDriver(image);
}
