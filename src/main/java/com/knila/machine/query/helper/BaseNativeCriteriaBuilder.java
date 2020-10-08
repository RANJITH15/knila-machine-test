package com.knila.machine.query.helper;

public interface BaseNativeCriteriaBuilder {
String queryAction();

String getCriteriaSQL();

String getCriteriaFunction();
}
