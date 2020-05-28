/*
 * Copyright (c) OSGi Alliance (2020). All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.osgi.test.assertj.event;

import java.util.function.ToIntFunction;

import org.assertj.core.api.AbstractAssert;
import org.osgi.test.common.bitmaps.Bitmap;

public abstract class AbstractBitmappedTypeEventAssert<SELF extends AbstractBitmappedTypeEventAssert<SELF, ACTUAL>, ACTUAL>
	extends AbstractAssert<SELF, ACTUAL> {

	final protected ToIntFunction<ACTUAL>	getType;
	final protected Bitmap					bitmap;

	protected AbstractBitmappedTypeEventAssert(ACTUAL actual, Class<SELF> selfType, ToIntFunction<ACTUAL> getType,
		Bitmap bitmap) {
		super(actual, selfType);
		this.getType = getType;
		this.bitmap = bitmap;
	}

	protected int actualType() {
		return getType.applyAsInt(actual);
	}

	public SELF isOfType(int expected) {
		isNotNull();
		if ((expected & (expected - 1)) != 0) {
			throw new IllegalArgumentException(
				"Multiple bits set in expected (" + expected + ") - do you mean to use isOfTypeMaskedBy()?");
		}
		final String expectedString = bitmap.typeToString(expected);
		if ((actualType() & expected) == 0) {
			failWithMessage("%nExpecting%n <%s>%nto be of type:%n <%d:%s>%n but was of type:%n <%s>", actual, expected,
				expectedString, bitmap.typeMaskToString(actualType()));
		}
		return myself;
	}

	public SELF isNotOfType(int expected) {
		isNotNull();
		final String expectedType = bitmap.typeToString(expected);
		if ((actualType() & expected) != 0) {
			failWithMessage("%nExpecting%n <%s>%nnot to be of type:%n <%d:%s>%nbut it was", actual,
				expected,
				expectedType);
		}
		return myself;
	}

	public SELF isOfTypeMaskedBy(int mask) {
		isNotNull();
		if (mask <= 0 || mask >= bitmap.getKnownMask()) {
			throw new IllegalArgumentException("Mask testing for an illegal type: " + mask);
		}
		if ((actualType() & mask) == 0) {
			final String types = bitmap.typeMaskToString(mask);
			failWithMessage("%nExpecting%n <%s>%nto be of one of types:%n [%s]%n but was of type:%n <%s>",
				actual,
				types, bitmap.typeMaskToString(actualType()));
		}
		return myself;
	}

	public SELF isNotOfTypeMaskedBy(int mask) {
		isNotNull();
		if (mask <= 0 || mask >= bitmap.getKnownMask()) {
			throw new IllegalArgumentException("Mask testing for an illegal type: " + mask);
		}
		if ((actualType() & mask) != 0) {
			final String types = bitmap.typeMaskToString(mask);
			failWithMessage("%nExpecting%n <%s>%nto not be of one of types:%n [%s]%n but was of type:%n <%s>",
				actual,
				types, bitmap.typeMaskToString(actualType()));
		}
		return myself;
	}
}
