package com.aol.logprocessor.builders.impl;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;

/**
 * Configuration meant to be used by {@link CountersBuilder} class
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface CountersBuilderConfig {
    /**
     *
     * @return
     */
    @Nonnull List<String> getCountersValues();

    /**
     *
     * @return
     */
    @Nonnegative int getCounterField();
}
