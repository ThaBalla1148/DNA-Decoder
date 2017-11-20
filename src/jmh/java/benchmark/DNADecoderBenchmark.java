/**
 * 
 */
package benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import com.ebtechnet.dnadecoder.decoder.DNADecodedResult;
import com.ebtechnet.dnadecoder.decoder.DNADecoder;
import com.ebtechnet.dnadecoder.decoder.LegacyDNADecoder;
import com.ebtechnet.dnadecoder.decoder.LegacyDNADecoderV2;
import com.ebtechnet.dnadecoder.decoder.StreamDNADecoder;
import com.ebtechnet.dnadecoder.decoder.StreamDNADecoderV2;

/**
 * @author Eric
 *
 */
@State(Scope.Benchmark)
public class DNADecoderBenchmark {
	
	final static int maxRuns = 5;
	
	final int maxCodes = 100000;
	final int maxCodeLength = 100;
	volatile List<String> codes = null;
	final char[] validChars = new char[] {'A', 'C', 'G', 'T', 'U', 'X', 'Y'};
	
	final static DNADecoder streamDNADecoder = new StreamDNADecoder();
	final static DNADecoder streamDNADecoderV2 = new StreamDNADecoderV2();
	final static DNADecoder legacyDNADecoder = new LegacyDNADecoder();
	final static DNADecoder legacyDNADecoderV2 = new LegacyDNADecoderV2();
	
	public static void main(String[] args) {
		DNADecoderBenchmark benchmark = new DNADecoderBenchmark();
		benchmark.setup();
		
		
		for(int i=0; i<maxRuns; i++) {
			System.out.println("Running test # " + (i + 1));
			{
				long start = System.currentTimeMillis();
				benchmark.decodeDNACodes(legacyDNADecoder);
				long end = System.currentTimeMillis() - start;
				
				if(i == maxRuns - 1) System.out.println("legacyDNADecoder Took: " + end);
			}
			
			{				
				long start = System.currentTimeMillis();
				benchmark.decodeDNACodes(legacyDNADecoderV2);
				long end = System.currentTimeMillis() - start;
				
				if(i == maxRuns - 1) System.out.println("legacyDNADecoderV2 Took: " + end);
			}
			
			{
				long start = System.currentTimeMillis();
				benchmark.decodeDNACodes(streamDNADecoder);
				long end = System.currentTimeMillis() - start;
				
				if(i == maxRuns - 1) System.out.println("streamDNADecoder Took: " + end);
			}
			
			{
				long start = System.currentTimeMillis();
				benchmark.decodeDNACodes(streamDNADecoderV2);
				long end = System.currentTimeMillis() - start;
				
				if(i == maxRuns - 1) System.out.println("streamDNADecoderV2 Took: " + end);
			}
		}

	}
	
	@Setup
	public void setup() {
		codes = new ArrayList<>(maxCodes);
		populate(codes);
	}
	
	public void populate(final List<String> list) {
		Random random = new Random(100);
		
		IntStream.range(0, maxCodes)
			.mapToObj(i -> IntStream.range(0, maxCodeLength)
					.map(j -> validChars[random.nextInt(validChars.length)])
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append))
			.forEach(sb -> list.add(sb.toString()));
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public List<DNADecodedResult> decodeDNACodes(DNADecoder decoder) {
		return codes.stream()
				.parallel()
				.map(decoder::decode)
				.collect(Collectors.toList());
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public void streamDNADecoder() {
		codes.stream().parallel().forEach(streamDNADecoder::decode);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public void legacyDNADecoder() {
		codes.stream().parallel().forEach(legacyDNADecoder::decode);
	}

}
