/**
 * 
 */
package dnadecoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Eric
 *
 */
public class DNADecoder {
	
	private final List<Character> validChars = Arrays.asList('A', 'C', 'G', 'T', 'U', ' ');
	private final char[] defaultDNACodes = new char[] {'A', 'C', 'G', 'T', 'U'};
	private final char[] secondaryDNACodes = new char[] {'T', 'G', 'C', 'A', 'A'};
	private final char[] mRNACodes = new char[] {'A', 'C', 'G', 'U', 'U'};
	private final char[] tRNACodes = new char[] {'U', 'G', 'C', 'A', 'A'};
	
	public DNADecoder() {
		
	}
	
	public DNADecodedResult decode(final String initialDnaStrand) {
		if(initialDnaStrand != null) {
			final String dnaStrand1 = formatDnaStrand(initialDnaStrand);
			final String dnaStrand2 = decodeCodes(dnaStrand1, defaultDNACodes, secondaryDNACodes);
			final String mRNA = decodeCodes(dnaStrand2, secondaryDNACodes, mRNACodes);
			final String tRNA = decodeCodes(mRNA, mRNACodes, tRNACodes);
			
			return new DNADecodedResult(dnaStrand1, dnaStrand2, mRNA, tRNA);
		}
		
		return new DNADecodedResult();
	}
	
	private String formatDnaStrand(String initialDnaStrand) {
		final StringBuilder sb = new StringBuilder();
		
		initialDnaStrand.trim().toUpperCase().chars()
			.mapToObj(c -> Character.valueOf((char)c))
			.filter(c -> validChars.contains(c))
			.forEach(sb::append);
			
		return sb.toString();
	}
	
	
	private String decodeCodes(String codes, char[] originalCodes, char[] decoderCodes) {
		final StringBuilder sb = new StringBuilder();
		
		codes.chars()
			.forEach(c -> sb.append(decodeCode((char)c, originalCodes, decoderCodes)));
		
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
