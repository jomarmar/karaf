/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.bundle.state.spring.internal;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.karaf.bundle.core.BundleStateService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.springframework.osgi.context.event.OsgiBundleApplicationContextListener;

public class Activator implements BundleActivator {

    public void start(BundleContext bundleContext) {
	    registerSpringBundleStateService(bundleContext);
    }

	private void registerSpringBundleStateService(BundleContext bundleContext) {
		SpringStateService springStateService = new SpringStateService(bundleContext);
	    Dictionary<String, ?> properties = new Hashtable<String, String>();
	    String[] classes2 = new String[] {
				OsgiBundleApplicationContextListener.class.getName(),
				BundleStateService.class.getName()
			};
	    bundleContext.registerService(classes2, springStateService, properties);
	}

    public void stop(BundleContext context) {
    }

}