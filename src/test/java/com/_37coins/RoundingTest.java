package com._37coins;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class RoundingTest {

	@Test
	public void testRoundBitcoinDown() {
		BigDecimal dividend = new BigDecimal("1");
		BigDecimal divisor = new BigDecimal("3");
		BigDecimal quotient = dividend.divide(divisor, 8, RoundingMode.HALF_UP);
		assertEquals(quotient.compareTo(new BigDecimal("0.33333333")), 0);
	}
	
	@Test
	public void testRoundBitcoinUp() {
		BigDecimal dividend = new BigDecimal("2");
		BigDecimal divisor = new BigDecimal("3");
		BigDecimal quotient = dividend.divide(divisor, 8, RoundingMode.HALF_UP);
		assertEquals(quotient.compareTo(new BigDecimal("0.66666667")), 0);
	}
	
	@Test
	public void testRoundFiatDown() {
		BigDecimal dividend = new BigDecimal("1");
		BigDecimal divisor = new BigDecimal("3");
		BigDecimal quotient = dividend.divide(divisor, 2, RoundingMode.HALF_UP);
		assertEquals(quotient.compareTo(new BigDecimal("0.33")), 0);
	}
	
	@Test
	public void testRoundFiatUp() {
		BigDecimal dividend = new BigDecimal("2");
		BigDecimal divisor = new BigDecimal("3");
		BigDecimal quotient = dividend.divide(divisor, 2, RoundingMode.HALF_UP);
		assertEquals(quotient.compareTo(new BigDecimal("0.67")), 0);
	}

}
