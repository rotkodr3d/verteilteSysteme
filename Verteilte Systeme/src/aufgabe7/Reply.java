package aufgabe7;

import java.io.Serializable;

public class Reply implements Serializable
{
  public int ja;
  public int nein;
  public int enthaltungen;
  public String thema;
  public String nachricht;

  public Reply(int ja, int nein, int enthaltungen, String thema) {
    super();
    this.ja = ja;
    this.nein= nein;
    this.enthaltungen=enthaltungen;
    this.thema = thema;
  }
  public Reply() {
	  
  }
  public Reply(String nachricht) {
      this.nachricht = nachricht;
  }

} // Reply