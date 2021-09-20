package fr.inria.atlanmod.neoemf.io.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

public class FileAssert {

    /**
     * Don't let anyone have access to this constructor.
     */
    private FileAssert() {
    }

    /**
     * Asserts that two files are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.<p>
     *
     * <b>Note</b>: This assertion method rely on the standard
     * <tt>junit.framework.Assert(String expected, String actual)</tt> method
     * to compare the lines of the files.  JUnit > 3.8 provides a nicer way to
     * display differences between two strings but since only lines are
     * compared (and not entire paragraphs) you can still use JUnit 3.7.
     */
    public static void assertTextEquals(String message,
                                        File expected,
                                        File actual,
                                        String charsetName) {
        assertNotNull(expected);
        assertNotNull(actual);

        assertTrue(expected.exists(), "File does not exist [" + expected.getAbsolutePath() + "]");
        assertTrue(actual.exists(), "File does not exist [" + actual.getAbsolutePath() + "]");

        assertTrue(expected.canRead(), "Expected file not readable");
        assertTrue(actual.canRead(), "Actual file not readable");

        FileInputStream eis = null;
        FileInputStream ais = null;

        try {
            try {
                eis = new FileInputStream(expected);
                ais = new FileInputStream(actual);

                BufferedReader expData = new BufferedReader(new InputStreamReader(eis, createCharsetDecoder(charsetName)));
                BufferedReader actData = new BufferedReader(new InputStreamReader(ais, createCharsetDecoder(charsetName)));

                assertNotNull(expData, message);
                assertNotNull(actData, message);

                assertTextEquals(message, expData, actData);
            } finally {
                if (eis != null) eis.close();
                if (ais != null) ais.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Asserts that two files are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.
     */
    public static void assertTextEquals(File expected,
                                        File actual,
                                        String charsetName) {
        assertTextEquals(null, expected, actual, charsetName);
    }

    /**
     * Asserts that two files are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.
     */
    public static void assertTextEquals(File expected,
                                        File actual) {
        assertTextEquals(null, expected, actual, null);
    }

    /**
     * <b>Testing only</b> Asserts that two readers are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.
     */
    protected static void assertTextEquals(String message,
                                           Reader expected,
                                           Reader actual) {
        assertNotNull(expected, message);
        assertNotNull(actual, message);

        LineNumberReader expReader = new LineNumberReader(expected);
        LineNumberReader actReader = new LineNumberReader(actual);

        assertNotNull(expReader, message);
        assertNotNull(actReader, message);

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        String expLine;
        String actLine;
        try {
            while (true) {
                if (!expReader.ready() && !actReader.ready()) {
                    return;
                }

                expLine = expReader.readLine();
                actLine = actReader.readLine();

                if (expLine == null && actLine == null) {
                    return;
                }

                int line = expReader.getLineNumber() + 1;

                if (expReader.ready()) {
                    if (actReader.ready()) {
                        Assertions.assertEquals(expLine, actLine, formatted + "Line [" + line + "]");
                    } else {
                        fail(formatted + "Line [" + line + "] expected <" + expLine + "> but was <EOF>");
                    }
                } else {
                    if (actReader.ready()) {
                        fail(formatted + "Line [" + line + "] expected <EOF> but was <" + actLine + ">");
                    } else {
                        Assertions.assertEquals(expLine, actLine, formatted + "Line [" + line + "]");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Asserts that two binary files are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.<p>
     */
    public static void assertBinaryEquals(File expected,
                                          File actual) {
        assertBinaryEquals(null, expected, actual);
    }

    /**
     * Asserts that two binary files are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.<p>
     */
    public static void assertBinaryEquals(String message,
                                          File expected,
                                          File actual) {
        assertNotNull(expected, message);
        assertNotNull(actual, message);

        assertTrue(expected.exists(), "File does not exist [" + expected.getAbsolutePath() + "]");
        assertTrue(actual.exists(), "File does not exist [" + actual.getAbsolutePath() + "]");

        assertTrue(expected.canRead(), "Expected file not readable");
        assertTrue(actual.canRead(), "Actual file not readable");

        FileInputStream eis = null;
        FileInputStream ais = null;

        try {
            try {
                eis = new FileInputStream(expected);
                ais = new FileInputStream(actual);

                assertNotNull(expected, message);
                assertNotNull(actual, message);

                byte[] expBuff = new byte[8192];
                byte[] actBuff = new byte[8192];

                long pos = 0;
                while (true) {
                    int expLength = eis.read(expBuff, 0, 8192);
                    int actLength = ais.read(actBuff, 0, 8192);

                    if (expLength < actLength) {
                        fail("actual file is longer");
                    }
                    if (expLength > actLength) {
                        fail("actual file is shorter");
                    }

                    if (expLength == 0) {
                        return;
                    }

                    for (int i = 0; i < expBuff.length; ++i) {
                        if (expBuff[i] != actBuff[i]) {
                            String formatted = "";
                            if (message != null) {
                                formatted = message + " ";
                            }
                            fail(formatted + "files differ at byte " + (pos + i + 1));  // i starts at 0 so +1
                        }
                    }

                    pos += expBuff.length;
                    return;
                }
            } finally {
                if (eis != null) eis.close();
                if (ais != null) ais.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static CharsetDecoder createCharsetDecoder(String charsetName) {
        Charset charset = (charsetName != null) ? Charset.forName(charsetName) : Charset.defaultCharset();
        return charset.newDecoder()
                .onMalformedInput(CodingErrorAction.REPORT)
                .onUnmappableCharacter(CodingErrorAction.REPORT);
    }
}
