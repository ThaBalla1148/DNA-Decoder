/**
 * 
 */
package com.ebtechnet.dnadecoder.decoder;

import java.util.Arrays;
import java.util.List;

/**
 * @author Eric
 *
 */
public class DNADecoderLegacy {
	
	private final List<Character> validChars = Arrays.asList('A', 'C', 'G', 'T', 'U', ' ');
	private final char[] defaultDNACodes = new char[] {'A', 'C', 'G', 'T', 'U'};
	private final char[] secondaryDNACodes = new char[] {'T', 'G', 'C', 'A', 'A'};
	private final char[] mRNACodes = new char[] {'A', 'C', 'G', 'U', 'U'};
	private final char[] tRNACodes = new char[] {'U', 'G', 'C', 'A', 'A'};
	
	public DNADecoderLegacy() {
		
	}
	
	public DNADecodedResult decode(final String initialDnaStrand) {
		if(initialDnaStrand != null) {
			final String dnaStrand1 = formatDnaStrand(initialDnaStrand.toUpperCase().trim());
			final String dnaStrand2 = decodeCodes(dnaStrand1, defaultDNACodes, secondaryDNACodes);
			final String mRNA = decodeCodes(dnaStrand2, secondaryDNACodes, mRNACodes);
			final String tRNA = decodeCodes(mRNA, mRNACodes, tRNACodes);
			
			return new DNADecodedResult(dnaStrand1, dnaStrand2, mRNA, tRNA);
		}
		
		return new DNADecodedResult();
	}
	
	private String formatDnaStrand(final String initialDnaStrand) {
		final StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<initialDnaStrand.length(); i++) {
			final char currentChar = initialDnaStrand.charAt(i);
			
			if(validChars.contains(currentChar)) {
				sb.append(currentChar);
			}
		}
		
		return sb.toString();
	}
	
	
	private String decodeCodes(String codes, char[] originalCodes, char[] decoderCodes) {
		final StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<codes.length(); i++) {
			final char origChar = codes.charAt(i);
			
			sb.append(decodeCode(origChar, originalCodes, decoderCodes));
		}
		
		return sb.toString();
	}
	
	private char decodeCode(char code, char[] originalCodes, char[] decoderCodes) {
		char resultCode = code;
		
		for(int i=0; i<originalCodes.length; i++) {
			char originalCode = originalCodes[i];
			
			if(originalCode == code) {
				resultCode = decoderCodes[i];
				break;
			}
		}
		
		return resultCode;
	}

}
