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

package org.osgi.test.assertj.bundleevent;

import org.assertj.core.api.InstanceOfAssertFactory;
import org.osgi.framework.BundleEvent;

public class BundleEventAssert extends AbstractBundleEventAssert<BundleEventAssert, BundleEvent> {

	public static final InstanceOfAssertFactory<BundleEvent, BundleEventAssert> BUNDLE_EVENT = new InstanceOfAssertFactory<>(
		BundleEvent.class, BundleEventAssert::new);

	public BundleEventAssert(BundleEvent actual) {
		super(actual, BundleEventAssert.class, BundleEvent::getType);
	}

	public static BundleEventAssert assertThat(BundleEvent actual) {
		return new BundleEventAssert(actual);
	}
}
