package com.aol.logprocessor.transformations;

import javax.annotation.Nonnull;

/**
 * Generic transformation class
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface Transformation {
    /**
     * Performns a generic transformation on a given input
     *
     * @param input
     * @return
     * @throws TransformationException
     */
    @Nonnull
    String[] transform(String[] input) throws TransformationException;
}
