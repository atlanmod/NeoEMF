/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Timeout(time = 2, timeUnit = TimeUnit.HOURS)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(
        value = 1,
        jvmArgsPrepend = {}, // Used to declare the JProfiler agent in the Docker environment: Keep it empty!
        jvmArgs = {"-Dfile.encoding=utf-8", "-server", "-XX:+UseConcMarkSweepGC"},
        jvmArgsAppend = {"-Xmx8g"}
)
public class Runner {
}
