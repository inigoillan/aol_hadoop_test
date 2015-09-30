package com.aol.logprocessor.parser.input;

import javax.annotation.Nonnull;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface RecordParser {
    @Nonnull
    String[] parse(String row);
}
