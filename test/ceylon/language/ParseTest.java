package ceylon.language;

import static ceylon.language.parseFloat.parseFloat;
import static ceylon.language.parseInteger.parseInteger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ParseTest {

	private void assertParseInteger(Long expect, java.lang.String str) {
		Integer parsed = parseInteger(str);
		if (expect != null) {
			if (parsed == null) {
				fail(str + " didn't parse as an Integer");
			}
			assertEquals("parseInteger(" + str + ")" + parsed.longValue(), 
					expect.longValue(), parsed.longValue());
		} else {
			assertNull("parseInteger(" + str + ") returned a result " + parsed, parsed);
		}
	}
	
	private void assertParseFloat(Double expect, java.lang.String str) {
		Float parsed = parseFloat(str);
		if (expect != null) {
			if (parsed == null) {
				fail(str + " didn't parse as a Float");
			}
			assertTrue("parseFloat(" + str + ") expected " + expect + " but got " + parsed.doubleValue(), 
					expect.doubleValue() == parsed.doubleValue());
		} else {
			assertNull("parseFloat(" + str + ") returned a result " + parsed, parsed);
		}
	}
	
	@Test
	public void testParseInteger() {
		assertParseInteger(0L, "0");
		assertParseInteger(1000L, "1_000");
		assertParseInteger(1000L, "1000");
		assertParseInteger(1000L, "1k");
		assertParseInteger(1000L, "+1_000");
		assertParseInteger(1000L, "+1000");
		assertParseInteger(1000L, "+1k");
		assertParseInteger(-1000L, "-1_000");
		assertParseInteger(-1000L, "-1000");
		assertParseInteger(-1000L, "-1k");
		
		assertParseInteger(0L, "0");
		assertParseInteger(0L, "00");
		assertParseInteger(0L, "0_000");
		assertParseInteger(0L, "-00");
		assertParseInteger(0L, "+00");
		assertParseInteger(0L, "0k");
		
		assertParseInteger(1L, "1");
		assertParseInteger(1L, "01");
		assertParseInteger(1L, "0_001");
		assertParseInteger(1L, "+1");
		assertParseInteger(1L, "+01");
		assertParseInteger(1L, "+0_001");
		
		assertParseInteger(-1L, "-1");
		assertParseInteger(-1L, "-01");
		assertParseInteger(-1L, "-0_001");
		
		assertParseInteger(1000L, "1k");
		assertParseInteger(1000000L, "1M");
		assertParseInteger(1000000000L, "1G");
		assertParseInteger(1000000000000L, "1T");
		assertParseInteger(1000000000000000L, "1P");
		assertParseInteger(-1000L, "-1k");
		assertParseInteger(-1000000L, "-1M");
		assertParseInteger(-1000000000L, "-1G");
		assertParseInteger(-1000000000000L, "-1T");
		assertParseInteger(-1000000000000000L, "-1P");
		
		assertParseInteger(9223372036854775807L, "9223372036854775807");
		assertParseInteger(9223372036854775807L, "9_223_372_036_854_775_807");
		assertParseInteger(-9223372036854775808L, "-9223372036854775808");
		assertParseInteger(-9223372036854775808L, "-9_223_372_036_854_775_808");
		
		
		assertParseInteger(null, "");
		assertParseInteger(null, "_");
		assertParseInteger(null, "+");
		assertParseInteger(null, "+_");
		assertParseInteger(null, "-");
		assertParseInteger(null, "-_");
		assertParseInteger(null, "foo");
		assertParseInteger(null, " 0");
		assertParseInteger(null, "0 ");
		assertParseInteger(null, "0+0");
		assertParseInteger(null, "0-0");
		assertParseInteger(null, "0+");
		assertParseInteger(null, "0-");
		assertParseInteger(null, "k");
		assertParseInteger(null, "k1");
		assertParseInteger(null, "+k");
		assertParseInteger(null, "-k");
		assertParseInteger(null, "1kk");
		assertParseInteger(null, "0_");
		assertParseInteger(null, "_0");
		assertParseInteger(null, "0_0");
		assertParseInteger(null, "0_00");
		assertParseInteger(null, "0_0000");
		assertParseInteger(null, "0_000_0");
		assertParseInteger(null, "0000_000");
		assertParseInteger(null, "1_0");
		assertParseInteger(null, "!_00");
		assertParseInteger(null, "1_0000");
		assertParseInteger(null, "1_000_0");
		assertParseInteger(null, "1000_000");
		assertParseInteger(null, "9223372036854775808");
		assertParseInteger(null, "-9223372036854775809");
	}
	
	@Test
	public void testParseFloat() {
		assertParseFloat(12.34e3, "12.34e3");
		assertParseFloat(12.34e3, "12.340e3");
		assertParseFloat(12.34e3, "123.4e2");
		assertParseFloat(12.34e3, "1234.0e1");
		assertParseFloat(12.34e3, "1234.0e+1");
		assertParseFloat(12.34e3, "12340.0e0");
		assertParseFloat(12.34e3, "12340.0");
		assertParseFloat(12.34e3, "12340.0");
		assertParseFloat(12.34e3, "123400.0e-1");
		
		assertParseFloat(12.34e3, "012340");
		assertParseFloat(12.34e3, "+12340");
		
		assertParseFloat(-12.34e3, "-12340");
		
		assertParseFloat(1e3, "1.0e3");
		assertParseFloat(1e3, "1.0E3");
		assertParseFloat(1e3, "+1.0e+3");
		assertParseFloat(1e3, "+1.0E+3");
		assertParseFloat(1e3, "1.0k");
		
		assertParseFloat(1e-3, "1.0m");
		assertParseFloat(1e-3, "1m");
		assertParseFloat(1e-3, "1.0e-3");
		assertParseFloat(1e-3, "1.0E-3");
		assertParseFloat(1e-6, "0.000_001");
		assertParseFloat(1e-9, "0.000_000_001");
		
		assertParseFloat(1e3, "+1.0E+3");
		
		assertParseFloat(1e3, "+1.000_000E+3");
		assertParseFloat(1e3, "+1_000.0");
		assertParseFloat(1e6, "+1_000_000.0");
		assertParseFloat(1e6, "+1000000.000_000_000");
		assertParseFloat(1e6, "+1000000.000_000_000e000_000");
		
		assertParseFloat(Double.POSITIVE_INFINITY, "+1.0E+1_000");
		
		assertParseFloat(null, "1E+3");
		assertParseFloat(null, "1e+3");
		assertParseFloat(null, "1e+1_00");
		assertParseFloat(null, "1e+1000_000");
		assertParseFloat(null, "1000_000.0");
		assertParseFloat(null, "1_000_00.0");
		assertParseFloat(null, "0.000_01");
        assertParseFloat(null, "0.00_1");
        assertParseFloat(null, "0.0000_1");
		assertParseFloat(null, "1T");
		assertParseFloat(null, "1_T");
		assertParseFloat(null, "1_m");
		assertParseFloat(null, "1.");
		assertParseFloat(null, ".1");
		assertParseFloat(null, ".1e1");
		assertParseFloat(null, "1.e1");
		assertParseFloat(null, "1.-1");
		assertParseFloat(null, "1.1-");
		assertParseFloat(null, "1-.1");
		assertParseFloat(null, "1.1.1");
		assertParseFloat(null, "1.0e1.1");
		assertParseFloat(null, "1.0e1e1");
		assertParseFloat(null, "1.0e1k");
		assertParseFloat(null, "1.0kk");
	}
	
}