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

package org.osgi.test.assertj.dictionary;

import java.util.Dictionary;
import java.util.Hashtable;

import org.assertj.core.api.SoftAssertionsProvider;

public interface DictionarySoftAssertionsProvider extends SoftAssertionsProvider {
	/**
	 * Create soft assertion for {@link java.util.Dictionary}.
	 *
	 * @param actual the actual value.
	 * @return the created assertion object.
	 */
	default <K, V> ProxyableDictionaryAssert<K, V> assertThat(Dictionary<K, V> actual) {
		@SuppressWarnings("unchecked")
		ProxyableDictionaryAssert<K, V> softly = proxy(ProxyableDictionaryAssert.class, Dictionary.class, actual);
		return softly;
	}

	/**
	 * Create soft assertion for {@link java.util.Hashtable}. Provided to allow
	 * the compiler to resolve the ambiguity for {@code Hashtable} (as it also
	 * implements {@link java.util.Map}).
	 *
	 * @param actual the actual value.
	 * @param <K> the type of the keys used in the hashtable
	 * @param <V> the type of the values used in the hashtable
	 * @return the created assertion object.
	 */
	default <K, V> ProxyableDictionaryAssert<K, V> assertThat(Hashtable<K, V> actual) {
		return assertThat((Dictionary<K, V>) actual);
	}
}
