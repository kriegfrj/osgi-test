/*
 * Copyright (c) OSGi Alliance (2019, 2020). All Rights Reserved.
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

package org.osgi.test.assertj.bundle;

import org.assertj.core.api.SoftAssertionsProvider;
import org.osgi.framework.Bundle;

public interface BundleSoftAssertionsProvider extends SoftAssertionsProvider {
	/**
	 * Create assertion for {@link org.osgi.framework.Bundle}.
	 *
	 * @param actual the actual value.
	 * @return the created assertion object.
	 */
	default BundleAssert assertThat(Bundle actual) {
		return proxy(BundleAssert.class, Bundle.class, actual);
	}
}
