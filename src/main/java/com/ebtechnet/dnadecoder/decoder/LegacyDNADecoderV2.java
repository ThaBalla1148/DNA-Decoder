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
public class LegacyDNADecoderV2 implements DNADecoder{
	
	private final List<Character> validChars = Arrays.asList('A', 'C', 'G', 'T', 'U', ' ');
	private final char[] defaultDNACodes = new char[] {'A', 'C', 'G', 'T', 'U', ' '};
	private final char[] secondaryDNACodes = new char[] {'T', 'G', 'C', 'A', 'A', ' '};
	private final char[] mRNACodes = new char[] {'A', 'C', 'G', 'U', 'U', ' '};
	private final char[] tRNACodes = new char[] {'U', 'G', 'C', 'A', 'A', ' '};
	
	public LegacyDNADecoderV2() {
		
	}
	
	@Override
	public DNADecodedResult decode(final String initialDnaStrand) {
		if(initialDnaStrand != null) {
			final StringBuilder dnaStrand1 = new StringBuilder();
			final StringBuilder dnaStrand2 = new StringBuilder();
			final StringBuilder mRNA = new StringBuilder();
			final StringBuilder tRNA = new StringBuilder();
			
			final String formatedDnaStrand = initialDnaStrand.trim().toUpperCase();
			
			for(int i=0; i<formatedDnaStrand.length(); i++) {
				final char currentChar = initialDnaStrand.charAt(i);
				final int idx = validChars.indexOf(currentChar);
				
				if(idx > -1) {
					dnaStrand1.append(defaultDNACodes[idx]);
					dnaStrand2.append(secondaryDNACodes[idx]);
					mRNA.append(mRNACodes[idx]);
					tRNA.append(tRNACodes[idx]);
				}
				
			}
			
			return new DNADecodedResult(dnaStrand1.toString(), dnaStrand2.toString(), mRNA.toString(), tRNA.toString());
		}
		
		return new DNADecodedResult();
	}

}
