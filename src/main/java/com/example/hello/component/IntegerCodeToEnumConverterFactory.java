package com.example.hello.component;

import com.example.hello.model.type.IBaseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class IntegerCodeToEnumConverterFactory implements ConverterFactory<Integer, IBaseEnum> {
    private static final Map<Class, Converter> CONVERTERS = new HashMap<Class, Converter>();
    @Override
    public <T extends IBaseEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        Converter<Integer, T> converter = CONVERTERS.get(targetType);
        if (Objects.isNull(converter)) {
            converter = new IntegerToEnumConverter<>(targetType);
            CONVERTERS.put(targetType, converter);
        }
        return converter;
    }

    @SuppressWarnings("all")
    private static class IntegerToEnumConverter<T extends IBaseEnum> implements Converter<Integer, T> {
        private Map<Integer, T> enumMap = new HashMap<>();

        public IntegerToEnumConverter(Class<T> enumType) {
            T[] enums = enumType.getEnumConstants();
            Arrays.stream(enums).forEach(p -> enumMap.put((Integer)p.getIndex(), p));
        }

        @Override
        public T convert(Integer source) {
            T t = enumMap.get(source);
            if (Objects.isNull(t)) {
                log.error("ERROR", new IllegalArgumentException("无法匹配对应的枚举类型"));
            }
            return t;
        }
    }
}
