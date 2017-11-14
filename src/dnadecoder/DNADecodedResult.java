package dnadecoder;

public class DNADecodedResult {
	private String dnaStrand1 = "";
	private String dnaStrand2 = "";
	private String mRNA = "";
	private String tRNA = "";
	
	public DNADecodedResult() {
		
	}
	
	public DNADecodedResult(String dnaStrand1, String dnaStrand2, String mRNA, String tRNA) {
		this.dnaStrand1 = dnaStrand1;
		this.dnaStrand2 = dnaStrand2;
		this.mRNA = mRNA;
		this.tRNA = tRNA;
	}

	public String getDnaStrand1() {
		return dnaStrand1;
	}

	public void setDnaStrand1(String dnaStrand1) {
		this.dnaStrand1 = dnaStrand1;
	}

	public String getDnaStrand2() {
		return dnaStrand2;
	}

	public void setDnaStrand2(String dnaStrand2) {
		this.dnaStrand2 = dnaStrand2;
	}

	public String getmRNA() {
		return mRNA;
	}

	public void setmRNA(String mRNA) {
		this.mRNA = mRNA;
	}

	public String gettRNA() {
		return tRNA;
	}

	public void settRNA(String tRNA) {
		this.tRNA = tRNA;
	}
	
	@Override
	public String toString() {
		return "\nDNA Strand #1: " + dnaStrand1 + "\n" +
				"DNA Strand #2: " + dnaStrand2 + "\n" +
				"mRNA: " + mRNA + "\n" +
				"tRNA: " + tRNA + "\n";
	}
	
	
}