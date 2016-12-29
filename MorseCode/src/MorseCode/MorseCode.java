package MorseCode;
import java.util.HashMap;

/**
 * Creates a morse code class that holds the mapping from symbol to morse code.
 * Has array of strings and HashMap to hold variables.
 */
public class MorseCode {

	// Array is easier to randomly choose and show in table
	private String[][] symbols;
	
	// Hasp map is easier to code messages
	private HashMap<String, String> symbolsMap;
	
	/** 
	 * Initiates bank of symbols to morse code.
	 */
	public MorseCode()
	{
		// Adds morse code into double array
		symbols = new String[36][2];
		symbols[0][0] = "A";	symbols[0][1] = ".-";
		symbols[1][0] = "B";	symbols[1][1] = "-...";
		symbols[2][0] = "C";	symbols[2][1] = "-.-.";
		symbols[3][0] = "D";	symbols[3][1] = "-..";
		symbols[4][0] = "E";	symbols[4][1] = ".";
		symbols[5][0] = "F";	symbols[5][1] = "..-.";
		symbols[6][0] = "G";	symbols[6][1] = "--.";
		symbols[7][0] = "H";	symbols[7][1] = "....";
		symbols[8][0] = "I";	symbols[8][1] = "..";
		symbols[9][0] = "J";	symbols[9][1] = ".---";
		symbols[10][0] = "K";	symbols[10][1] = "-.-";
		symbols[11][0] = "L";	symbols[11][1] = ".-..";
		symbols[12][0] = "M";	symbols[12][1] = "--";
		symbols[13][0] = "N";	symbols[13][1] = "-.";
		symbols[14][0] = "O";	symbols[14][1] = "---";
		symbols[15][0] = "P";	symbols[15][1] = ".--.";
		symbols[16][0] = "Q";	symbols[16][1] = "--.-";
		symbols[17][0] = "R";	symbols[17][1] = ".-.";
		symbols[18][0] = "S";	symbols[18][1] = "...";
		symbols[19][0] = "T";	symbols[19][1] = "-";
		symbols[20][0] = "U";	symbols[20][1] = "..-";
		symbols[21][0] = "V";	symbols[21][1] = "...-";
		symbols[22][0] = "W";	symbols[22][1] = ".--";
		symbols[23][0] = "X";	symbols[23][1] = "-..-";
		symbols[24][0] = "Y";	symbols[24][1] = "-.--";
		symbols[25][0] = "Z";	symbols[25][1] = "--..";
		symbols[26][0] = "0";	symbols[26][1] = "-----";
		symbols[27][0] = "1";	symbols[27][1] = ".----";
		symbols[28][0] = "2";	symbols[28][1] = "..---";
		symbols[29][0] = "3";	symbols[29][1] = "....--";
		symbols[30][0] = "4";	symbols[30][1] = "....-";
		symbols[31][0] = "5";	symbols[31][1] = ".....";
		symbols[32][0] = "6";	symbols[32][1] = "-....";
		symbols[33][0] = "7";	symbols[33][1] = "--...";
		symbols[34][0] = "8";	symbols[34][1] = "---..";
		symbols[35][0] = "9";	symbols[35][1] = "----.";
		
		// Adds into hash map
		symbolsMap = new HashMap<String, String>();
		for (int i=0; i<symbols.length; i++)
		{
			symbolsMap.put(symbols[i][0], symbols[i][1]);
		}
	}
	
	/**
	 * Converts String message to morse code
	 * @param textMessage (String)
	 * @return String - message in dots, dashes, c (end of character), and s (space)
	 */
	public String toMorseCode(String textMessage)
	{
		// Buffer message with character
		String morseMessage = "c";
		
		// Iterate through message
		for (int i=0; i<textMessage.length(); i++)
		{
			// Get character and convert to string
			String s = String.valueOf(textMessage.charAt(i));
			
			// Morse text or separation between words
			if (s.equals(" "))
				morseMessage = morseMessage + "s";
			else
				morseMessage = morseMessage + symbolsMap.get(s.toUpperCase());
			
			// Separation between letters
			if (i + 1 < textMessage.length() && !s.equals(" "))
				morseMessage = morseMessage + "c";
		}
	
		// Return message as morse code with end buffer
		return morseMessage + "c";
	}
		
	/**
	 * Returns String of the Symbols and Morse code equivalent for table
	 * @return String[][] - String[Symbol][Morse Code]
	 */
	public String[][] getSymbols()
	{
		return symbols;
	}
	
	/**
	 * Returns HashMap<Symbol, MorseCode> for converting
	 * @return HashMap<String, String> - (Symbol, MorseCode)
	 */
	public HashMap<String, String> getSymbolsMap()
	{
		return symbolsMap;
	}
}