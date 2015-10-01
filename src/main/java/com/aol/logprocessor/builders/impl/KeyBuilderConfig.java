package com.aol.logprocessor.builders.impl;

/**
 * Configuration meant to be used by {@link KeyBuilder} class
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface KeyBuilderConfig {
    /**
     * Get's the indexes of the fields used for building the key
     *
     * @return
     */
    int[] getKeyFieldIndexes();
}
