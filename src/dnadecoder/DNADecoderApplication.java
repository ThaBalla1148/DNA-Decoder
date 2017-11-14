package dnadecoder;
/**
 * 
 */

import java.util.Scanner;

/**
 * @author Eric
 *
 */
public class DNADecoderApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting DNA Decoder");
		
		final DNADecoder dnaDecoder = new DNADecoder();
		
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(System.in);
			do {
				System.out.println("---------------------------------");
				System.out.println("Enter a DNA Strand Sequence: ");
				final String dnaStrand = scanner.nextLine();
				
				final DNADecodedResult result = dnaDecoder.decode(dnaStrand);
				
				System.out.println("----Results----");
				System.out.println(result.toString());
				System.out.println("---------------------------------");
				System.out.println("Would you like to try again? (y/yes or n/no)");
				
				final String tryAgain = scanner.nextLine();
				
				if(tryAgain == null || tryAgain.toLowerCase().charAt(0) != 'y') {
					System.out.println("Exiting....");
					return;
				}
				
			} while(true);
		} catch(Exception e) {
			
		} finally {
			if(scanner != null) {
				scanner.close();
			}
		}
		
		
		

	}

}
