package com.alibaba.datax.core.transport.transformer;

import com.alibaba.datax.common.element.Column;
import com.alibaba.datax.common.element.Record;
import com.alibaba.datax.common.element.StringColumn;
import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.core.transport.transformer.strategy.*;
import com.alibaba.datax.transformer.Transformer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * no comments.
 * Created by liqiang on 16/3/4.
 */
public class ReplaceTransformer extends Transformer {
    public ReplaceTransformer() {
        setTransformerName("dx_replace");
    }

    //TODO 根据判断条件 将原来的字符串替换会为指定字符串
    //TODO 直接改下面的代码 让在daas中使用这一个函数就行
    //TODO 给三个参数 1、下标 2、方法 3、判断的值 4、替换的值 5、sink下标
    @Override
    public Record evaluate(Record record, Object... paras) {

        int columnIndex;
        String sinkColumnIndex;
        String replaceString;
        String method;
        String sourceValue;
        try {
            if (paras.length != 5) {
                throw new RuntimeException("dx_replace paras must be 5");
            }

            columnIndex = (Integer) paras[0];
            method = (String) paras[1];
            sourceValue = (String) paras[2];
            replaceString = (String) paras[3];
            sinkColumnIndex = (String) paras[4];
        } catch (Exception e) {
            throw DataXException.asDataXException(TransformerErrorCode.TRANSFORMER_ILLEGAL_PARAMETER, "paras:" + Arrays.asList(paras).toString() + " => " + e.getMessage());
        }

        Column column = record.getColumn(columnIndex);
        try {

            Map<String, Strategy> strategyMap = new HashMap<>();
            strategyMap.put("equals", new EqualsStrategy());
            strategyMap.put("notEquals",new NotEqualsStrategy());
            strategyMap.put("GR", new GRStrategy());
            strategyMap.put("GRE", new GREStrategy());
            strategyMap.put("LR", new LRStrategy());
            strategyMap.put("LRE", new LREStrategy());
            strategyMap.put("isNull",new IsNullStrategy());
            strategyMap.put("isNotNull",new IsNotNullStrategy());
            strategyMap.put("regex",new RegexStrategy());
            strategyMap.put("isNullString",new IsNullStrategy());
            strategyMap.put("isNotNullString",new IsNotNullStringStrategy());


            Strategy strategy = strategyMap.get(method);
            if (strategy != null && strategy.execute(column, sourceValue)) {
                column = new StringColumn(replaceString);
            }


            record.setColumn(Integer.parseInt(sinkColumnIndex), column);

        } catch (Exception e) {
            throw DataXException.asDataXException(TransformerErrorCode.TRANSFORMER_RUN_EXCEPTION, e.getMessage(), e);
        }
        return record;
    }
}
