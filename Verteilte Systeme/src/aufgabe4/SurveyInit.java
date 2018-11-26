package aufgabe4;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import aufgabe4.Server.SurveyDataset;

public class SurveyInit {
	public static void main(String[] args) {
		SurveyDataset sds = new SurveyDataset();
		sds.addAnswerToDataset(SurveyAnswers.ABSTENSION, 0);
		sds.addAnswerToDataset(SurveyAnswers.AGREE, 0);
		sds.addAnswerToDataset(SurveyAnswers.DISAGREE, 0);
		sds.setSurveyQuestion("Should java support multiple inheritance?");
		String fileName = "surveyData.txt";
	
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(sds);
			oos.flush();
			oos.close();
			fos.close();
			System.out.println("Prepared Survey data");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
