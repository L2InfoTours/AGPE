package liaisonappliBDopta;

public class Main {

	public static void main(String[] args) {
		
		Authentification a = new Authentification("Tom","deusvult");
		Authentification b = new Authentification("PasTom","deusvult");
		Authentification c = new Authentification("PasTom","Pasdeusvult");
		Authentification d = new Authentification("Tom","Pasdeusvult");
		if (a.getAutorise() == true) {
			System.out.println("�a marche");
		}
		else if (a.getAutorise() == false)  {
			System.out.println("�a marche pas ");
		}
		if (b.getAutorise() == true) {
			System.out.println("�a marche");
		}
		else if (b.getAutorise() == false)  {
			System.out.println("�a marche pas ");
		}
		if (c.getAutorise() == true) {
			System.out.println("�a marche");
		}
		else if (c.getAutorise() == false)  {
			System.out.println("�a marche pas ");
		}
		if (d.getAutorise() == true) {
			System.out.println("�a marche");
		}
		else if (d.getAutorise() == false)  {
			System.out.println("�a marche pas ");
		}

	}

}
