package com.aol.logprocessor.parser.input;

import javax.annotation.Nonnull;

/**
 * Generic record parser
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface RecordParser {
    /**
     * Parses the given row and breaks it down into a bunch of {@link String}s
     * @param row
     * @return
     */
    @Nonnull
    String[] parse(String row);
}
