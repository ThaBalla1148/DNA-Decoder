/**
 * 
 */
package benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
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

import com.ebtechnet.dnadecoder.decoder.DNADecoder;
import com.ebtechnet.dnadecoder.decoder.DNADecoderLegacy;

/**
 * @author Eric
 *
 */
@State(Scope.Benchmark)
public class DNADecoderBenchmark {
	
	final int maxCodes = 100000;
	final int maxCodeLength = 100;
	volatile List<String> codes = null;
	final char[] validChars = new char[] {'A', 'C', 'G', 'T', 'U'};
	
	final DNADecoder streamDNADecoder = new DNADecoder();
	final DNADecoderLegacy legacyDNADecoder = new DNADecoderLegacy();
	
	public static void main(String[] args) {
		DNADecoderBenchmark benchmark = new DNADecoderBenchmark();
		benchmark.setup();
		
		{
			long start = System.currentTimeMillis();
			benchmark.legacyDNADecoder();
			long end = System.currentTimeMillis() - start;
			
			System.out.println("legacyDNADecoder Took: " + end);
		}
		
		{
			long start = System.currentTimeMillis();
			benchmark.streamDNADecoder();
			long end = System.currentTimeMillis() - start;
			
			System.out.println("streamDNADecoder Took: " + end);
		}
		
		{
			long start = System.currentTimeMillis();
			benchmark.legacyDNADecoder();
			long end = System.currentTimeMillis() - start;
			
			System.out.println("legacyDNADecoder Took: " + end);
		}
		
		{
			long start = System.currentTimeMillis();
			benchmark.streamDNADecoder();
			long end = System.currentTimeMillis() - start;
			
			System.out.println("streamDNADecoder Took: " + end);
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
	public void streamDNADecoder() {
		for(String code : codes) {
			streamDNADecoder.decode(code);
		}
	}
	
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@Fork(2)
	@Measurement(iterations = 5)
	@Warmup(iterations = 5)
	public void legacyDNADecoder() {
		for(String code : codes) {
			legacyDNADecoder.decode(code);
		}
	}

}
