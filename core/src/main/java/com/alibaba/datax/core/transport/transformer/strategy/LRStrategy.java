/*
 * Classname LRStrategy
 *
 * Version info
 *
 * Copyright notice
 */
package com.alibaba.datax.core.transport.transformer.strategy;

import com.alibaba.datax.common.element.Column;

public class LRStrategy implements Strategy {
    @Override
    public boolean execute(Column column, String sourceValue) {
        return column.asLong() < Integer.parseInt(sourceValue);
    }
}
