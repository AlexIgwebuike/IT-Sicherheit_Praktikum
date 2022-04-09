package caesar_chiffre;

import javax.swing.JOptionPane;

public class CaesarChiffre {
	
	public static void main(String[]args) {
		
		String menue_eingabe = menue();
		String text = enterText(menue_eingabe);
		int schluessel = enterKey();
		String methode = entscheide_funktion(menue_eingabe, text, schluessel);
		ausgabe(menue_eingabe, methode);
	}
	
	//Hier kann der Nutzer aussuchen ob er seinen Text verschluesseln oder entschuesseln möchte.
	public static String menue() {
		String option = "";
		while(option != "v" && option != "e") {
			option = JOptionPane.showInputDialog("1) Geben sie v ein um einen Text zu verschluesseln.\n2) Geben sie e ein um einen Text zu entschluesseln.\n");
			switch(option) {
				case "v": option = "v";
					break;
				case "e": option = "e";
					break;
				default: JOptionPane.showMessageDialog(null, "Diese Option existiert nicht!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		return option;
	}

	//Hier findet die eingabe für das Chiffrat oder Klartext statt.
	public static String enterText(String menue_auswahl) {
		String input = "";
		if(menue_auswahl == "v") {
			input = JOptionPane.showInputDialog("Geben sie den Text ein den sie verschluesseln wollen.");
		}else {
			input = JOptionPane.showInputDialog("Geben sie den Text ein den sie entschluesseln wollen.");
		}
		return input; 
	}
	
	//Diese Funktion ist dazu da, um zu entscheiden, ob ein Text verschluesselt, oder entschluesselt wird.
	public static String entscheide_funktion(String funktions_auswahl, String text, int key) {
		if(funktions_auswahl == "v") {
			return encryptText(text, key);
		}else {
			return decryptText(text, key);
		}
	}
		
	//Hier wird der Schlüssel zum Verschlüssln eingegeben.
	public static int enterKey() {
		int key = 0;
		while(key == 0) {
			try {
				key =  Integer.parseInt(JOptionPane.showInputDialog("Geben sie den Schlüssel ein"));
				if(key > 26) {
					JOptionPane.showMessageDialog(null, "Der Schluesselraum geht nur bis 26! Geben sie erneut eine Zahl im Schluesselraum ein.");
					key = 0;
				}
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Geben sie bitte eine Zahl im Schluesselraum ein.");
			}
		}
		
		return key;
	}
	
	//Hier wird der Text Verschlüsselt.
	public static String encryptText(String plain_text, int key) {
		char[]array = new char[plain_text.length()];
		array = plain_text.toCharArray();
		for(int i = 0; i < array.length; i++) {
			if(array[i] == (char)32) {
				array[i] = ' ';
			}else if(array[i] == (char)44) {
				array[i] = ',';
			}else if(array[i] == (char)46){
				array[i] = '.';
			}else {
				array[i] = (char)((int)array[i]);
				array[i] = checkIfLetterOrNumberEncryption(array[i], key);
			}
		}
		
		return String.valueOf(array);
	}
	
	//Hier wird Kontrolliert ob Es sich um einen Groß/Klein Buchstaben oder Zahl handelt beim verschlüsseln des Chiffrats.
	public static char checkIfLetterOrNumberEncryption(char character, int key) {
		if((int)character > 64 && (int)character < 91) {
			character = (char)((int)character + key);
			if((int)character > 90) {
				character = (char)((int)character - 26);
			}
		}else if((int)character > 96 && (int)character < 123) {
			character = (char)((int)character + key);
			if((int)character > 122) {
				character = (char)((int)character - 26);
			}
		}else if((int)character > 47 && (int)character < 58){
			int new_key = 0;

			if(key > 9 && key < 20) {
				new_key = key % 10;
			}else if(key > 20) {
				new_key = key % 20;
			}
			character = (char)((int)character + new_key);
			if((int)character > 57) {
				character = (char)((int)character - 10);
			}
		}
			
		return character;
	}
	
	//Das ist eine Methode zum entschlüsseln, des Chiffrats. 
	public static String decryptText(String encrypted_text, int key) {
			
		char[]array = new char[encrypted_text.length()];
		array = encrypted_text.toCharArray();
		for(int i = 0; i < array.length; i++) {
			if(array[i] == (char)32) {
				array[i] = ' ';
			}else if(array[i] == (char)44) {
				array[i] = ',';
			}else if(array[i] == (char)46){
				array[i] = '.';
			}else {
				array[i] = (char)((int)array[i]);
				array[i] = checkIfLetterOrNumberDecryption(array[i], key);
			}
		}
			
			return String.valueOf(array);
	}
		
	//Hier wird Kontrolliert ob Es sich um einen Groß/Klein Buchstaben oder Zahl handelt beim entschlüsseln des Chiffrats.
	public static char checkIfLetterOrNumberDecryption(char character, int key) {
		if((int)character > 64 && (int)character < 91) {
			character = (char)((int)character - key);
			if((int)character < 65) {
				character = (char)((int)character + 26);
			}
		}else if((int)character > 96 && (int)character < 123) {
			character = (char)((int)character - key);
			if((int)character < 97) {
				character = (char)((int)character + 26);
			}
		}else if((int)character > 47 && (int)character < 58){
			int new_key = 0;
				if(key > 9 && key < 20) {
				new_key = key % 10;
			}else if(key > 20) {
				new_key = key % 20;
			}
			character = (char)((int)character - new_key);
			if((int)character < 48) {
				character = (char)((int)character + 10);
			}
		}
				
		return character;
	}
	
	//Ausgabe
	public static void ausgabe(String ausgabe_art, String methode) {
		if(ausgabe_art == "v") {
			System.out.println("Verschluesselt: " + methode);
		}else {
			System.out.println("Entschluesselt: " + methode);
		}
	}
}
