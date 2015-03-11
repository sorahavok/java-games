package life.generator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileBoardGenerator implements BoardGenerator {

	private static final String BASE_PATH = "res/life/boards/";

	private final String fileName;

	public FileBoardGenerator(final String fileName) {
		this.fileName = fileName;
	}

	@Override
	public int[][] getBoard() {

		List<int[]> intLists = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(BASE_PATH + fileName))) {
			String str;
			while ((str = br.readLine()) != null) {
				intLists.add(Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return intLists.toArray(new int[0][0]);
	}
}
