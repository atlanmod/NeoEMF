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

package fr.inria.atlanmod.neoemf.benchmarks.util;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandLineUtil {

    private static final Logger LOG = LogManager.getLogger();

    // TODO: Replace by an enum with included options
    public static class Key {
        public static final String IN = "input";

        public static final String OUT = "output";

        public static final String REPO_NAME = "reponame";

        public static final String EPACKAGE_CLASS = "epackage_class";

        public static final String IN_EPACKAGE_CLASS = "input_epackage_class";

        public static final String OUT_EPACKAGE_CLASS = "output_epackage_class";

        public static final String OPTIONS_FILE = "options_file";

        public static final String LABEL = "label";
    }

    private CommandLineUtil() {
    }

    public static Map<String, String> getOptionsValues(Options options, String... args) throws ParseException {
        Map<String, String> values = new HashMap<>();

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine commandLine = parser.parse(options, args);

            options.getOptions().stream()
                    .filter(o -> o.isRequired() || (!o.isRequired() && commandLine.hasOption(o.getOpt())))
                    .forEach(o -> values.put(o.getOpt(), commandLine.getOptionValue(o.getOpt())));
        }
        catch (ParseException e) {
            LOG.error(e.toString());
            LOG.error("Current arguments: " + Arrays.toString(args));
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar <this-file.jar>", options, true);
            throw e;
        }

        return values;
    }
}
