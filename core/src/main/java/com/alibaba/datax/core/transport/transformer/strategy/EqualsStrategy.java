/*
 * Classname EqualsStrategy
 *
 * Version info
 *
 * Copyright notice
 */
package com.alibaba.datax.core.transport.transformer.strategy;

import com.alibaba.datax.common.element.Column;

public class EqualsStrategy implements Strategy {
    @Override
    public boolean execute(Column column, String sourceValue) {
        return column.asString().equals(sourceValue);
    }
}
