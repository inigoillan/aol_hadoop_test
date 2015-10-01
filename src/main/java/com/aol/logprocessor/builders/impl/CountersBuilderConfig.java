package com.aol.logprocessor.builders.impl;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface CountersBuilderConfig {
    @Nonnull List<String> getCountersValues();
    @Nonnegative int getCounterField();
}
