package aufgabe4.Server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import aufgabe4.SurveyData;
import aufgabe4.SurveyOptions;

public class VoteServerInit {

	public static void main(String[] args) {
		String fileName = "Survey.txt";
		SurveyData surveyData = SurveyData.initEmptySurvey();
		
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			//if (new File(fileName).exists()) //Bei persistend über neustart wichtig!!!
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(surveyData);
			
			oos.flush();
			oos.close();
			fos.flush();
			fos.close();
			System.out.println("Survey file initialized");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
