package org.decimal4j.test;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import org.decimal4j.truncate.DecimalRounding;
import org.decimal4j.truncate.OverflowMode;
import org.decimal4j.truncate.TruncationPolicy;

public enum TestTruncationPolicies {
	
	TINY(Arrays.asList(DecimalRounding.DOWN.getUncheckedTruncationPolicy(), DecimalRounding.HALF_UP.getUncheckedTruncationPolicy(), 
			DecimalRounding.DOWN.getCheckedTruncationPolicy(), DecimalRounding.UNNECESSARY.getCheckedTruncationPolicy())),
	
	SMALL(Arrays.asList(DecimalRounding.DOWN.getUncheckedTruncationPolicy(), DecimalRounding.HALF_UP.getUncheckedTruncationPolicy(), DecimalRounding.UNNECESSARY.getUncheckedTruncationPolicy(), 
			DecimalRounding.DOWN.getCheckedTruncationPolicy(), DecimalRounding.HALF_UP.getCheckedTruncationPolicy())),
	
	STANDARD(Arrays.asList(DecimalRounding.DOWN.getUncheckedTruncationPolicy(), DecimalRounding.HALF_UP.getUncheckedTruncationPolicy(), DecimalRounding.HALF_EVEN.getUncheckedTruncationPolicy(), DecimalRounding.UNNECESSARY.getUncheckedTruncationPolicy(), 
			DecimalRounding.DOWN.getCheckedTruncationPolicy(), DecimalRounding.HALF_UP.getCheckedTruncationPolicy())),
	
	LARGE(Arrays.asList(DecimalRounding.UP.getUncheckedTruncationPolicy(), DecimalRounding.DOWN.getUncheckedTruncationPolicy(), DecimalRounding.HALF_UP.getUncheckedTruncationPolicy(), DecimalRounding.HALF_EVEN.getUncheckedTruncationPolicy(), DecimalRounding.UNNECESSARY.getUncheckedTruncationPolicy(), 
			DecimalRounding.UP.getCheckedTruncationPolicy(), DecimalRounding.DOWN.getCheckedTruncationPolicy(), DecimalRounding.HALF_UP.getCheckedTruncationPolicy(), DecimalRounding.HALF_EVEN.getCheckedTruncationPolicy(), DecimalRounding.UNNECESSARY.getCheckedTruncationPolicy())),
			
	ALL(TruncationPolicy.VALUES);
	
	private final Collection<TruncationPolicy> policies;
	
	private TestTruncationPolicies(Collection<TruncationPolicy> policies) {
		this.policies = Collections.unmodifiableCollection(policies);
	}
	
	public Collection<TruncationPolicy> getPolicies() {
		return policies;
	}
	
	public Set<RoundingMode> getUncheckedRoundingModes() {
		return getRoundingModesFor(OverflowMode.UNCHECKED);
	}
	
	public Set<RoundingMode> getRoundingModesFor(OverflowMode overflowMode) {
		final Set<RoundingMode> rounding = EnumSet.noneOf(RoundingMode.class);
		for (final TruncationPolicy policy : getPolicies()) {
			if (overflowMode.equals(policy.getOverflowMode())) {
				rounding.add(policy.getRoundingMode());
			}
		}
		return Collections.unmodifiableSet(rounding);
	}

}