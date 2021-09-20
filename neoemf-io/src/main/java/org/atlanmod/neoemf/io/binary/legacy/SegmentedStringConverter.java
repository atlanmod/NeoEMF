package org.atlanmod.neoemf.io.binary.legacy;

import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.CompressedInts;
import org.atlanmod.neoemf.io.binary.BinaryWriter;
import org.atlanmod.neoemf.io.binary.Converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SegmentedStringConverter implements Converter<String> {
    private static final Map<String, Integer> SPECIAL_CHARACTERS = new HashMap<String, Integer>();
    private static final int MAX_DELIMITER = 0xC0;
    private static final String[] DELIMITERS = new String[MAX_DELIMITER];

    private Map<String, Integer> segmentedStringToIDMap;
    private Map<String, Integer> segmentToIDMap;
    private BinaryWriter stream;
    private List<String> segments;
    private StringConverter rawConverter = new StringConverter();


    static {
        SPECIAL_CHARACTERS.put("", 0);
        for (char c = 0; c < DELIMITERS.length; ++c) {
            if (!(Character.isDigit(c)) && Character.isLetter(c)) continue;
            SPECIAL_CHARACTERS.put(Character.toString(c), SPECIAL_CHARACTERS.size());
            if (!Character.isDigit(c)) {
                DELIMITERS[c] = Character.toString(c);
            }
        }
    }

    public SegmentedStringConverter() {
        segmentedStringToIDMap = new HashMap<String, Integer>();
        segmentToIDMap = new HashMap<String, Integer>(SPECIAL_CHARACTERS);
        segments = new ArrayList<>(40);
    }

    @Override
    public byte[] toBytes(Object object) {
        return new byte[0];
    }

    @Override
    public void writeOn(BufferWrangler buffer, String value) {
        if (value == null) {
            buffer.put(NULL);
            return;
        }

        Integer id = segmentedStringToIDMap.get(value);
        if (id == null) {
            int idValue = segmentedStringToIDMap.size();
            segmentedStringToIDMap.put(value, idValue);
            buffer.put(CompressedInts.toBytes(idValue));

            int segmentCount = 0;
            int start = 0;
            int end = value.length();
            for (int i = 0; i < end; ++i) {
                char c = value.charAt(i);
                if (c < MAX_DELIMITER) {
                    String delimiter = DELIMITERS[c];
                    if (delimiter != null) {
                        if (start < i) {
                            segments.add(value.substring(start, i));
                        }
                        segments.add(delimiter);
                        start = i + 1;
                    }
                }
            }
            if (start == 0 || segmentCount == 1 && start == end) {
                buffer.put(NULL);
                rawConverter.writeOn(buffer, value);
            } else {
                if (start < end) {
                    segments.add(value.substring(start, end));
                }

                buffer.put(CompressedInts.toBytes(segmentCount));
                for (int i = 0; i < segmentCount; ++i) {
                    rawConverter.writeOn(buffer, segments.get(i));
                }
            }
        } else {
            buffer.put(CompressedInts.toBytes(id));
        }
    }

    @Override
    public String readFrom(BufferWrangler buffer) {
        return null;
    }

    public String toString() {
        return "Segments: " + this.segments.size();
    }
}
