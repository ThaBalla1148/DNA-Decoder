/**
 * 
 */
package com.ebtechnet.dnadecoder.decoder;

/**
 * @author Eric
 *
 */
public class StreamDNADecoderV2 implements DNADecoder {
	
	private final char[] validChars = new char[] {'A', 'C', 'G', 'T', 'U', ' '};
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
				.map(this::findValidCharIndex)
				.filter(this::isValidIndex)
				.forEach(idx -> writeChars(idx, dnaStrand1, dnaStrand2, mRNA, tRNA));

			return new DNADecodedResult(dnaStrand1.toString(), dnaStrand2.toString(), mRNA.toString(), tRNA.toString());
		}
		
		return new DNADecodedResult();
	}
	
	private int findValidCharIndex(final int c) {
		char castedC = (char)c;
		for(int i=0; i<validChars.length; i++) {
			if(validChars[i] == castedC) {
				return i;
			}
		}
		
		return -1;
	}
	
	private boolean isValidIndex(final int idx) {
		return idx > -1;
	}
	
	private void writeChars(final int idx, final StringBuilder dnaStrand1, final StringBuilder dnaStrand2, final StringBuilder mRNA, final StringBuilder tRNA) {
		dnaStrand1.append(defaultDNACodes[idx]);
		dnaStrand2.append(secondaryDNACodes[idx]);
		mRNA.append(mRNACodes[idx]);
		tRNA.append(tRNACodes[idx]);
	}

}
