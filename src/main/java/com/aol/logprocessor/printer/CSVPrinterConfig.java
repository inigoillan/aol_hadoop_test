package com.aol.logprocessor.printer;

/**
 * Configuration meant to be used with {@link CSVPrinter} class
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface CSVPrinterConfig {
    /**
     * Get's the separator to be used in the printers output
     *
     * @return
     */
    String getOutputSeparator();
}
