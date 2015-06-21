package com.easyfx.model;

import java.util.List;
import java.util.Map;

public class Controller {
	
private String name;
private String className;
private Map<String,String> resultSet;
private Map<String,String> eventSet;
private List<String> interceptorSet;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getClassName() {
	return className;
}
public void setClassName(String className) {
	this.className = className;
}
public Map<String, String> getResultSet() {
	return resultSet;
}
public void setResultSet(Map<String, String> resultSet) {
	this.resultSet = resultSet;
}
public Map<String,String> getEventSet() {
	return eventSet;
}
public void setEventSet(Map<String,String> eventSet) {
	this.eventSet = eventSet;
}
public List<String>  getInterceptorSet() {
	return interceptorSet;
}
public void setInterceptorSet(List<String> interceptorSet) {
	this.interceptorSet = interceptorSet;
}
}
