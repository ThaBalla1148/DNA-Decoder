/**
 * 
 */
package com.ebtechnet.dnadecoder.decoder;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Eric
 *
 */
public class StreamDNADecoderV2 implements DNADecoder {
	
	private final List<Character> validChars = Arrays.asList('A', 'C', 'G', 'T', 'U', ' ');
	private final char[] defaultDNACodes = new char[] {'A', 'C', 'G', 'T', 'U', ' '};
	private final char[] secondaryDNACodes = new char[] {'T', 'G', 'C', 'A', 'A', ' '};
	private final char[] mRNACodes = new char[] {'A', 'C', 'G', 'U', 'U', ' '};
	private final char[] tRNACodes = new char[] {'U', 'G', 'C', 'A', 'A', ' '};
	
	public StreamDNADecoderV2() {
		
	}
	
	/* (non-Javadoc)
	 * @see com.ebtechnet.dnadecoder.decoder.DNADecoder#decode(java.lang.String)
	 */
	@Override
	public DNADecodedResult decode(final String initialDnaStrand) {
		if(initialDnaStrand != null) {
			final StringBuilder dnaStrand1 = new StringBuilder();
			final StringBuilder dnaStrand2 = new StringBuilder();
			final StringBuilder mRNA = new StringBuilder();
			final StringBuilder tRNA = new StringBuilder();
			
			initialDnaStrand.trim().toUpperCase().chars()
				.map(c -> validChars.indexOf((char)c))
				.filter(idx -> idx > -1)
				.forEach(idx -> {
					dnaStrand1.append(defaultDNACodes[idx]);
					dnaStrand2.append(secondaryDNACodes[idx]);
					mRNA.append(mRNACodes[idx]);
					tRNA.append(tRNACodes[idx]);
				});

			return new DNADecodedResult(dnaStrand1.toString(), dnaStrand2.toString(), mRNA.toString(), tRNA.toString());
		}
		
		return new DNADecodedResult();
	}

	
	

}
