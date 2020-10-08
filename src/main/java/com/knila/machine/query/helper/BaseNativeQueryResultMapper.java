package com.knila.machine.query.helper;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.expression.Fields;

import com.fasterxml.jackson.annotation.JsonFormat;

@Service
public class BaseNativeQueryResultMapper<T> {

	@SuppressWarnings("deprecation")
	public  List<T> mapWithColumnNames(final List<Map<String,Object>> rsMapList,final Class<T> genericType) throws Exception{
		if(!genericType.isAnnotationPresent(BaseNativeQueryEntity.class)) {
			throw new Exception();
		}
		final List<T> ret =new ArrayList<>();
		final List<Field> mappingFields=getNativeQueryResultColumnAnnotatedFieldNames(genericType);
		try {
			for(Map<String,Object> rsMap :rsMapList) {
				final T t=genericType.newInstance();
				for(Field currField:mappingFields) {
					final BaseQueryResultColumn nqrc=currField.getAnnotation(BaseQueryResultColumn.class);
					final Class clazz=nqrc.expectedDataType();
					if(rsMap.get(nqrc.columnName())!=null) {
						final Object obj=convert(rsMap.get(nqrc.columnName()),clazz,currField);
						BeanUtils.setProperty(t, currField.getName(), obj);
					}
				}
				ret.add(t);
			}
			}catch(Exception e) {
				
			}
		
	
		return ret;
}

	private List<Field> getNativeQueryResultColumnAnnotatedFieldNames(final Class<T> genericType) {
		final Field[] fields=getSuperClassFields(genericType,genericType.getDeclaredFields());
		
		final List<Field> orderedFields=new ArrayList<Field>();
		for(final Field field :fields) {
			if(field.isAnnotationPresent(BaseQueryResultColumn.class)) {
				orderedFields.add(field);
			}
		}
		return orderedFields;
	}
	
	private Field[] getSuperClassFields(final Class<T> genericType,Field[] fields) {
		if(genericType.getSuperclass()==null) {
			return fields;
		}
		return ArrayUtils.addAll(fields,genericType.getSuperclass().getDeclaredFields());
	}
	
	private Object convert(final Object o,final Class clazz,final Field f) throws Exception {
		if(o==null||clazz==null) {
			throw new Exception();
		}
		if(clazz==String.class) {
			return String.valueOf(o);
		}else if(clazz==Integer.class) {
			return Integer.parseInt(String.valueOf(o));
		}else if(clazz==Long.class) {
			return Long.parseLong(String.valueOf(o));
		}else if(clazz==Double.class) {
			return ((BigDecimal)o).doubleValue();
		}else if(clazz==BigDecimal.class) {
			return (o);
		}else if(clazz==Boolean.class) {
			return "Y".equalsIgnoreCase(String.valueOf(o));
		}else {
			return (o);		}
	}
	
}


