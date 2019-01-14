package aufgabe7;


import java.io.Serializable;

public class Umfrage implements Serializable {  
	   public int ja = 0;
	   public int nein = 0;
	   public int enthaltung = 0; 
	   public String thema;

	   public Umfrage(String thema) {
		  this.thema = thema;
	   }
	}