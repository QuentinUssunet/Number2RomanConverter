package launcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("Lancement du programme de conversion Integer -> Nombre Romain");
		System.out.println("Saisissez un nombre a convertir : ");
		Scanner sc = new Scanner(System.in);
		Integer intToConvert = sc.nextInt();
		String convertedNum = converter(intToConvert);
		System.out.println("Votre nombre converti en chiffre Romain :");
		System.out.println(convertedNum);
	}

	/**
	 * Méthode principale de conversion.
	 * 
	 * @param intToConvert Le nombre à convertir (0 - 5000) (en réalité de 0 à 4999).
	 * @return Le résultat de la conversion du nombre.
	 */
	private static String converter(Integer intToConvert) {
		List<String> result = new ArrayList<>();
		List<Integer> intTab = convertIntToIntArray(intToConvert);
		Collections.reverse(intTab);
		for (int i = 0; i < intTab.size(); i++) {
			String romanChiffre = convertUnitToRoman(i, intTab.get(i));
			result.add(romanChiffre);
		}
		Collections.reverse(result);
		String strResult = "";
		for (String unity : result) {
			strResult += unity;
		}
		return strResult;
	}

	
	/**
	 * Convertit un chiffre en son équivalent Romain. 
	 * Prends en compte la place du chiffre dans le nombre (unité, dizaine, 
	 * centaine, ou millier) 
	 * 
	 * @param unit La place du chiffre dans le nombre.
	 * @param chiffre La valeur du chiffre à convertir.
	 * @return Le résultat en String de la conversion.
	 */
	private static String convertUnitToRoman(int unit, Integer chiffre) {
		String result = "";
		Map<Integer, String> data = prepareDb(unit);
		
		if(chiffre < 4 && chiffre != 0) {
			for (int i = 0; i < chiffre; i++) {
				result += data.get(1);
			}
		} else if(chiffre == 4) {
			result += data.get(1);
			result += data.get(5);
		} else if(chiffre == 5) {
			result += data.get(5);
		} else if(chiffre < 9 && chiffre != 0) {
			for (int i = 5; i < chiffre - 1; i++) {
				result += data.get(1);
			}
			result += data.get(10);
		}
		
		return result;
	}

	
	/**
	 * Prépare la base de données suivant si on analyse une unité, dizaine, 
	 * centaine, ou millier.
	 *  
	 * @param unit La place du chiffre dans le nombre (unité, dizaine, 
	 * centaine, ou millier) 
	 * @return data La base de données.
	 */
	private static Map<Integer, String> prepareDb(int unit) {
		Map<Integer, String> data = new HashMap<>();
		switch(unit) {
		case 0 : 
			data.put(1, "I");
			data.put(5, "V");
			data.put(10, "X");
			break;
		case 1 :
			data.put(1, "X");
			data.put(5, "L");
			data.put(10, "C");			
			break;
		case 2 : 
			data.put(1, "C");
			data.put(5, "D");
			data.put(10, "M");
			break;
		case 3 :
			data.put(1, "M");
			data.put(5, "Cinq Mille");
			break;
		}
		return data;
	}

	/**
	 * Méthode de transformation d'un nombre en tableau de chiffres.
	 * 
	 * @param intToConvert Le nombre à convertir
	 * @return Une liste de chiffres (nombre converti)
	 */
	// FIXME Il doit exister un moyen de le faire moins péniblement !
	private static List<Integer> convertIntToIntArray(Integer intToConvert) {
		String temp = intToConvert.toString();
		List<Integer> intTab = new ArrayList<Integer>();
		for (int i = 0; i < temp.length(); i++) {
			intTab.add(Character.getNumericValue(temp.charAt(i)));
		}
		return intTab;
	}
	

}
