package com.example.hello.component;

import com.example.hello.model.type.IBaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class EnumConvertFactory implements ConverterFactory<String, IBaseEnum> {
    @Override
    public <T extends IBaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToIBaseEnum<>(targetType);
    }

    @SuppressWarnings("all")
    private static class StringToIBaseEnum<T extends IBaseEnum> implements Converter<String, T> {
        private Class<T> targetType;
        public StringToIBaseEnum(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            return (T) EnumConvertFactory.getIBaseEnum(this.targetType, source);
        }
    }

    public static <T extends IBaseEnum> Object getIBaseEnum(Class<T> targetType, String source) {
        for (T enumObj : targetType.getEnumConstants()) {
            if (source.equals(String.valueOf(enumObj.getIndex()))) {
                return enumObj;
            }
        }
        return null;
    }
}
