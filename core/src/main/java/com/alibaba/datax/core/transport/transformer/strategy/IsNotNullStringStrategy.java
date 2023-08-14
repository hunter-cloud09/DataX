/*
 * Classname IsNotNullStringStrategy
 *
 * Version info
 *
 * Copyright notice
 */
package com.alibaba.datax.core.transport.transformer.strategy;

import com.alibaba.datax.common.element.Column;

/**
 * @author hunter
 * @Description TODO
 * @Date 2023/6/13 17:23
 */
public class IsNotNullStringStrategy implements Strategy{
    @Override
    public boolean execute(Column column, String sourceValue) {
        return !"".equals(column.asString());
    }
}