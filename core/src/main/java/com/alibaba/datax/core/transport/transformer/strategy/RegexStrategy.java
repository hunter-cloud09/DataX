/*
 * Classname RegexStrategy
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
 * @Date 2023/6/13 17:24
 */
public class RegexStrategy implements Strategy{
    @Override
    public boolean execute(Column column, String regex) {
        return column.asString().matches(regex);
    }
}
