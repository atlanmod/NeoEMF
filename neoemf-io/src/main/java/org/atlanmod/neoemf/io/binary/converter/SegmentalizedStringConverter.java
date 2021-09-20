package org.atlanmod.neoemf.io.binary.converter;

import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.io.Numbers;
import org.atlanmod.commons.io.UnsignedVarInt;
import org.atlanmod.commons.log.Log;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.atlanmod.neoemf.io.binary.Converter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SegmentalizedStringConverter implements Converter<String> {
    public static final int MAX_TOKEN_LENGTH = 0x3F;    // 0011 1111
    private final Charset charset;
    private Map<String, Integer> cache = new HashMap<>();
    private Map<Integer, String> index = new HashMap<>();

    public SegmentalizedStringConverter() {
        this(StandardCharsets.UTF_8);
    }

    public SegmentalizedStringConverter(Charset charset) {
        this.charset = charset;
    }

    @Override
    public byte[] toBytes(Object object) {
        return new byte[0];
    }

    @Override
    public void writeOn(BufferWrangler buffer, String value) {
        //Log.info("Using charset {0}", this.charset);
        Collection<Segment> segments = this.tokenize(value);
        buffer.put(Numbers.uvarint(segments.size()));
        for (Segment each : segments) {
            each.writeOn(buffer);
        }
    }

    public Collection<Segment> tokenize(String value) {
        Collection<Segment> segments = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < value.length(); i++) {
            final char each = value.charAt(i);
            if (Delimiter.isDelimiter(each)) {
                segments.add(createStringSegmentAndCleanBuilder(builder));
                segments.add(Delimiter.valueOf(each));
            } else {
                builder.append(each);
            }
        }
        segments.add(createStringSegmentFor(builder.toString()));
        return segments;
    }

    private Segment createStringSegmentAndCleanBuilder(StringBuilder builder) {
        String value = builder.toString();
        builder.setLength(0);
        return createStringSegmentFor(value);
    }

    private Segment createStringSegmentFor(String value) {
        Integer reference = cache.get(value);
        if (reference == null) {
            cache.put(value, cache.size());
            return new StringSegment(value, charset);
        } else {
            return new ReferenceSegment(reference);
        }
    }

    @Override
    public String readFrom(BufferWrangler buffer) {
        UnsignedVarInt length = buffer.getUnsignedVarInt();
        //Log.info("Reading {0} tokens", length);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length.intValue(); i++) {
            SegmentCode code = new SegmentCode(buffer.get());
            //Log.info("Read code {0}", code);
            if (code.isStringLength()) {
                //Log.info("Token {0} is a String Segment", i);
                int size = code.value();
                if (size == MAX_TOKEN_LENGTH) {
                    size = size + buffer.getUnsignedVarInt().intValue();
                }
                byte[] bytes = new byte[size];
                buffer.get(bytes);
                String readString = new String(bytes, charset);
                //Log.info("Read: {0}", readString);
                builder.append(readString);
                index.put(index.size(), readString);
            } else if (code.isReference()) {
                //Log.info("Token {0} is Reference", i);
                int reference = code.value();
                if (reference == MAX_TOKEN_LENGTH) {
                    reference = reference + buffer.getUnsignedVarInt().intValue();
                }
                String cachedString = index.get(reference);
                //Log.info("Found cached reference {0} ", reference);
                if (cachedString == null) System.out.println(index);
                builder.append(cachedString);
            } else if (code.isDelimiter()) {
                //Log.info("Token {0} is Delimiter", i);
                // Is Delimiter
                int clean = code.value();
                //Log.info("Clean code: {0}", clean);
                builder.append(Delimiter.charAt(clean));
            }
        }

        return builder.toString();
    }

    static class StringSegment extends Segment {
        private final String value;
        private final Charset charset;

        public StringSegment(String str, Charset charset) {
            this.value = str;
            this.charset = charset;
        }

        @Override
        public void writeOn(BufferWrangler buffer) {
            byte[] bytes = this.value.getBytes(this.charset);
            int length = bytes.length;
            if (length < MAX_TOKEN_LENGTH) {
                buffer.put((byte) length);
            } else {
                buffer.put((byte) MAX_TOKEN_LENGTH);
                buffer.put(Numbers.uvarint(length - MAX_TOKEN_LENGTH));
            }
            buffer.put(bytes);
        }

        @Override
        public void readFrom(BufferWrangler buffer) {
            throw Throwables.notImplementedYet("StringSegment::readFrom()");
        }
    }

    static class ReferenceSegment extends Segment {
        private final int reference;

        public ReferenceSegment(int reference) {
            this.reference = reference;
        }

        @Override
        public void writeOn(BufferWrangler buffer) {
            //Log.info("Writing reference to segment");

            if (reference < MAX_TOKEN_LENGTH) {
                final int dirt = reference | 0x40;
                buffer.put((byte) dirt);
            } else {
                final int dirt = MAX_TOKEN_LENGTH | 0x40;
                buffer.put((byte) dirt);
                buffer.put(Numbers.uvarint(reference - MAX_TOKEN_LENGTH));
            }
        }

        @Override
        public void readFrom(BufferWrangler buffer) {
            throw Throwables.notImplementedYet("ReferenceSegment::readFrom()");
        }
    }
}

