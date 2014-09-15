package by.bsuir.logic;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class KasiskyTest {
	private int gcd(int a, int b) {
		while (a != 0 && b != 0) {
			if (a > b) {
				a %= b;
			} else {
				b %= a;
			}
		}
		return a + b;
	}

	private int defineNodOfLengths(List<Integer> lengths) {
		int nod = 0;
		if (lengths.size() >= 2) {
			nod = gcd(lengths.get(0), lengths.get(1));
			for (int i = 0; i < lengths.size(); i++) {
				nod = gcd(lengths.get(i), nod);
			}
		}
		return nod;
	}

	private boolean isSymbolInAlphabet(Character ch) {
		Translator translator = new Translator();
		List<Character> alphabet = translator.getRussianAlphabet();
		Character.toLowerCase(ch);
		if (alphabet.contains(ch)) {
			return true;
		} else {
			return false;
		}
	}

	private String getGrammText(String text, int firstIndex, int lengthOfGramm) {
		StringBuilder sb = new StringBuilder();
		int length = 0;
		int index = firstIndex;
		Character currentChar;
		if (index < text.length() - lengthOfGramm) {
			while (!(length == lengthOfGramm)) {
				currentChar = text.charAt(index);
				if (isSymbolInAlphabet(currentChar)) {
					sb.append(currentChar);
					length++;
					index++;
				} else {
					index++;
				}
			}
		}
		return sb.toString();
	}

	private boolean containGram(List<Gramm> gramms, Gramm gramm) {
		for (Gramm gr : gramms) {
			if (gr.equals(gramm)) {
				return false;
			}
		}
		return true;
	}

	public int getLengthOfKeyWord(String text) {
		List<Gramm> gramms = new ArrayList<Gramm>();
		String firstStr;
		String subStr = null;
		StringBuilder sb = new StringBuilder();
		for (Character ch : text.toCharArray()) {
			if (isSymbolInAlphabet(ch)) {
				sb.append(ch);
			}
		}
		text = sb.toString();
		int lengthOfGramm = 3;
		text = text.trim();
		Gramm gramm;
		for (int i = 0; i < text.length(); i++) {
			gramm = new Gramm();
			firstStr = getGrammText(text, i, lengthOfGramm);
			for (int j = i + 1; j < text.length(); j++) {
				subStr = getGrammText(text, j, lengthOfGramm);
				if (subStr.equals(firstStr)) {
					if (gramm.getDestList().size() == 0) {
						gramm.setName(firstStr);
					}
					// gramm.addDestValue(j);
					gramm.addDestValue(j - i);
				}
			}
			if (gramm.getDestList().size() >= 1) {
				if (containGram(gramms, gramm)) {
					gramms.add(gramm);
				}
			}
		}

		// List<Gramm> biggestGramms = new ArrayList<Gramm>();
		// int numberOfDest = 3;
		// for (Gramm gr : gramms) {
		// if (gr.getDestList().size() >= numberOfDest) {
		// biggestGramms.add(gr);
		// }
		// }
		// int nod = 0;
		// Random random = new Random();
		// if (biggestGramms.size() > 0) {
		// Gramm biggestGramm = biggestGramms.get(random.nextInt(biggestGramms
		// .size()));
		// if (!(biggestGramm == null)) {
		// nod = defineNodOfLengths(biggestGramm.getDestList());
		// } else {
		// JOptionPane.showMessageDialog(null,
		// "Check input text box: probabaly it is empty.",
		// "Check key word", 1);
		// }
		// }

		Gramm biggestGramm = null;
		int n = 0;
		for (Gramm gr : gramms) {
			if (gr.getDestList().size() > n) {
				n = gr.getDestList().size();
				biggestGramm = gr;
			}
			// System.out.println(gr);
		}
//		Random random = new Random();
//		biggestGramm = gramms.get(random.nextInt(gramms.size()));
		if (!(biggestGramm == null)) {
			n = defineNodOfLengths(biggestGramm.getDestList());
		} else {
			JOptionPane.showMessageDialog(null,
					"Check input text box: probabaly it is empty.",
					"Check key word", 1);
		}
//		System.out.println("Длина ключа : " + n);
		return n;
	}
}
