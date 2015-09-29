package com.aol.logprocessor.transformations;

import javax.annotation.Nonnull;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface Transformation {
    @Nonnull
    String[] transform(String[] input) throws TransformationException;
}
