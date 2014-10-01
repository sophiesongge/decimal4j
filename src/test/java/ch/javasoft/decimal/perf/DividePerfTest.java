package ch.javasoft.decimal.perf;

import java.math.BigDecimal;
import java.math.MathContext;

import ch.javasoft.decimal.Decimal;
import ch.javasoft.decimal.MutableDecimal;
import ch.javasoft.decimal.arithmetic.DecimalArithmetics;
import ch.javasoft.decimal.scale.ScaleMetrics;

public class DividePerfTest extends AbstractPerfTest {

	public DividePerfTest(ScaleMetrics scaleMetrics) {
		super(scaleMetrics);
	}

	@Override
	protected long nextRandomLong() {
		long val;
		do {
			val = super.nextRandomLong();
		} while (val == 0);//avoid division by 0
		return val;
	}
	@Override
	protected String operation() {
		return "/";
	}
	
	@Override
	protected BigDecimal expectedResult(BigDecimal a, BigDecimal b, MathContext mathContext) {
		return a.divide(b, mathContext);
	}
	
	@Override
	protected <S extends ScaleMetrics> Decimal<S> actualResult(Decimal<S> a, Decimal<S> b) {
		return a.divide(b);
	}
	
	@Override
	protected int signumOfResult(BigDecimal a, BigDecimal b, MathContext mathContext) {
		return a.divide(b, mathContext).signum();
	}
	
	@Override
	protected int signumOfResult(double a, double b) {
		return (int)Math.signum(a / b);
	}
	
	@Override
	protected <S extends ScaleMetrics> int signumOfResult(Decimal<S> a, Decimal<S> b) {
		return a.divide(b).signum();
	}
	
	@Override
	protected <S extends ScaleMetrics> int signumOfResult(MutableDecimal<S, ?> m, Decimal<S> a, Decimal<S> b) {
		return m.set(a).divide(b).signum();
	}
	
	@Override
	protected <S extends ScaleMetrics> int signumOfResult(DecimalArithmetics arith, long a, long b) {
		return arith.signum(arith.divide(a, b));
	}

}