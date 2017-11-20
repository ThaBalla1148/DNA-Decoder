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
public class StreamDNADecoder implements DNADecoder {
	
	private final List<Character> validChars = Arrays.asList('A', 'C', 'G', 'T', 'U', ' ');
	private final char[] defaultDNACodes = new char[] {'A', 'C', 'G', 'T', 'U', ' '};
	private final char[] secondaryDNACodes = new char[] {'T', 'G', 'C', 'A', 'A', ' '};
	private final char[] mRNACodes = new char[] {'A', 'C', 'G', 'U', 'U', ' '};
	private final char[] tRNACodes = new char[] {'U', 'G', 'C', 'A', 'A', ' '};
	
	public StreamDNADecoder() {
		
	}
	
	/* (non-Javadoc)
	 * @see com.ebtechnet.dnadecoder.decoder.DNADecoder#decode(java.lang.String)
	 */
	@Override
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
	
	private String formatDnaStrand(final String initialDnaStrand) {
		return initialDnaStrand.trim().toUpperCase().chars()
			.mapToObj(c -> (char)c)
			.filter(validChars::contains)
			.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
			.toString();
	}
	
	
	private String decodeCodes(final String codes, final char[] originalCodes, final char[] decoderCodes) {
		return codes.chars()
			.mapToObj(c -> decodeCode((char)c, originalCodes, decoderCodes))
			.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
			.toString();
	}
	
	private char decodeCode(final char code, final char[] originalCodes, final char[] decoderCodes) {
		int idx = IntStream.range(0, originalCodes.length)
			.filter(i -> (originalCodes[i] == code))
			.findFirst()
			.getAsInt();
		
		return decoderCodes[idx];
	}

}
