package com.aol.logprocessor.parser.input;

/**
 * Configuration meant to be used with the {@link CSVParser} class
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface CSVParserConfig {
    /**
     * Get's the separator to be used by the parser
     *
     * @return
     */
    String getInputSeparator();
}
