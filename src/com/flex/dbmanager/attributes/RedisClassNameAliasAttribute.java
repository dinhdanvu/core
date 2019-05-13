package com.flex.dbmanager.attributes;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
  public  @interface  RedisClassNameAliasAttribute
  {

	String name();
	
  }
