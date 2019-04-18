package Plants_vs_Zombies;

import java.io.*;
import java.util.logging.Logger;

public class StageData {
	public static String STAGE_NUMBER = "1";
	public static String[][] STAGE_CONTENT = {{"NormalZombie"}, {"NormalZombie", "ConeHeadedZombie"}};
	public static int[][][] STAGE_VALUE = {{{0, 99}}, {{0, 49}, {50,99}}};
	
	public StageData() {
		try {
			File file = new File("STAGE_CONTENT.vbhv");
			if (!file.exists()) {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
				bufferedWriter.write("1");
				bufferedWriter.close();
				STAGE_NUMBER = "1";
			} else {
				@SuppressWarnings("resource")
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				STAGE_NUMBER = bufferedReader.readLine();
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
	}
	
	public static void Write(String stage) {
		File file = new File("STAGE_CONTENT.vbhv");
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(stage);
			bufferedWriter.close();
			STAGE_NUMBER = stage;
		} catch (Exception e) {
			Logger.getLogger(StageData.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
		}
	}
}
