package tetris.factories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tetris.piece.Piece;

public class PieceFactory {

	private static Random rand = new Random();
	private static List<Piece> pieces = new ArrayList<>();

	public static Piece getRandomPiece() {
		return pieces.get(rand.nextInt(pieces.size()));
	}

	public static void registerPiece(Piece newPiece) {
		newPiece.init(); 
		pieces.add(newPiece);
	}

	public static List<Piece> getAllPieces() { // return defensive copy
		return new ArrayList<>(pieces);
	}
}
