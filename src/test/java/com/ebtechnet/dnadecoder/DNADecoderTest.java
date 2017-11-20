package com.ebtechnet.dnadecoder;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ebtechnet.dnadecoder.decoder.DNADecodedResult;
import com.ebtechnet.dnadecoder.decoder.DNADecoder;
import com.ebtechnet.dnadecoder.decoder.LegacyDNADecoder;
import com.ebtechnet.dnadecoder.decoder.LegacyDNADecoderV2;
import com.ebtechnet.dnadecoder.decoder.StreamDNADecoder;
import com.ebtechnet.dnadecoder.decoder.StreamDNADecoderV2;

public class DNADecoderTest {
	
	private final String dnaStrand1 = "AGT UTG CCG ACA";
	
	private final DNADecoder streamDNADecoder = new StreamDNADecoder();
	private final DNADecoder streamDNADecoderV2 = new StreamDNADecoderV2();
	private final DNADecoder legacyDNADecoder = new LegacyDNADecoder();
	private final DNADecoder legacyDNADecoderV2 = new LegacyDNADecoderV2();
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test_streamDecoder() {
		DNADecodedResult result = streamDNADecoder.decode(dnaStrand1);

		assertTrue(result.getDnaStrand1().equals("AGT UTG CCG ACA"));
		assertTrue(result.getDnaStrand2().equals("TCA AAC GGC TGT"));
		assertTrue(result.getmRNA().equals("AGU UUG CCG ACA"));
		assertTrue(result.gettRNA().equals("UCA AAC GGC UGU"));
	}
	
	@Test
	public void test_streamDecoderV2() {
		DNADecodedResult result = streamDNADecoderV2.decode(dnaStrand1);

		assertTrue(result.getDnaStrand1().equals("AGT UTG CCG ACA"));
		assertTrue(result.getDnaStrand2().equals("TCA AAC GGC TGT"));
		assertTrue(result.getmRNA().equals("AGU UUG CCG ACA"));
		assertTrue(result.gettRNA().equals("UCA AAC GGC UGU"));
	}
	
	@Test
	public void test_legacyDecoder() {
		DNADecodedResult result = legacyDNADecoder.decode(dnaStrand1);

		assertTrue(result.getDnaStrand1().equals("AGT UTG CCG ACA"));
		assertTrue(result.getDnaStrand2().equals("TCA AAC GGC TGT"));
		assertTrue(result.getmRNA().equals("AGU UUG CCG ACA"));
		assertTrue(result.gettRNA().equals("UCA AAC GGC UGU"));
	}
	
	@Test
	public void test_legacyDecoderV2() {
		DNADecodedResult result = legacyDNADecoderV2.decode(dnaStrand1);

		assertTrue(result.getDnaStrand1().equals("AGT UTG CCG ACA"));
		assertTrue(result.getDnaStrand2().equals("TCA AAC GGC TGT"));
		assertTrue(result.getmRNA().equals("AGU UUG CCG ACA"));
		assertTrue(result.gettRNA().equals("UCA AAC GGC UGU"));
	}

}
