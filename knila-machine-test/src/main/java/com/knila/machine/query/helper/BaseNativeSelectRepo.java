package com.knila.machine.query.helper;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@SuppressWarnings("unchecked")
public class BaseNativeSelectRepo<U> {
@Autowired
private EntityManager entityManager;

@SuppressWarnings("rawtypes")
@Autowired
private BaseNativeQueryResultMapper baseNativeQueryResultMapper;


@Autowired
private JdbcTemplate jdbcTemplate;

protected List<U> executeNativeQueryColumnMapper(final BaseNativeCriteriaBuilder creteria,final Class<U> genericType) throws Exception{
	List<Map<String,Object>> resultSetMap =jdbcTemplate.query(creteria.getCriteriaSQL(), new ColumnMapRowMapper());
return baseNativeQueryResultMapper.mapWithColumnNames(resultSetMap,genericType);
}
}
