package com.aol.logprocessor.parser.input;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface RecordParser {
    String[] parse(String row);
}
