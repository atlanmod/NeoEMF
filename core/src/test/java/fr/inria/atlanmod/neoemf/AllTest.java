/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public abstract class AllTest {

    private static final int MB = 1024 * 1024;

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            System.out.println("[INFO] --- Succeeded");
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("[WARN] --- Failed");
        }

        @Override
        protected void starting(Description description) {
            System.out.println("\n[INFO] --- Running " + description.getMethodName() + "()");
        }

        @Override
        protected void finished(Description description) {
            System.out.println();
        }
    };

    protected void printMemoryUsage() {
        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();
        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println(formatMemoryUsage("Used memory", (runtime.totalMemory() - runtime.freeMemory()) / MB));

        //Print free memory
        System.out.println(formatMemoryUsage("Free memory", runtime.freeMemory() / MB));

        //Print total available memory
        System.out.println(formatMemoryUsage("Total memory", runtime.totalMemory() / MB));

        //Print Maximum available memory
        System.out.println(formatMemoryUsage("Max memory", runtime.maxMemory() / MB));

        System.out.println("#####");
    }

    private String formatMemoryUsage(String msg, long value) {
        return String.format("   %-12s  :  %4d", msg, value);
    }
}
