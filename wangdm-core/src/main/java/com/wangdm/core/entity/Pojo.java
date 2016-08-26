package com.wangdm.core.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Pojo {
    
    public Map<String, Object> enumField(){
        Map<String,Object> fieldMap = new HashMap<String,Object>();
        
        Class<?> clazz = this.getClass();
        do{

            if(clazz.getName().equals("java.lang.Object")){
                break;
            }
            
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                try {
                    field.setAccessible(true);
                    if((field.getModifiers() & Modifier.STATIC)==0){
                        fieldMap.put(field.getName(), field.get(this));
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            
            clazz = clazz.getSuperclass();
        }while(true);
        
        return fieldMap;
    }

    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("{");
        Map<String,Object> fieldMap = this.enumField();
        Set<String> keys = fieldMap.keySet();
        boolean first = true;
        for(String key : keys){
            if(first){
                first = false;
                strBuilder.append(key).append(":").append(fieldMap.get(key));
            }else{
                strBuilder.append(",").append(key).append(":").append(fieldMap.get(key));
            }
        }
        strBuilder.append("}");
        return strBuilder.toString();
    }

//    @Override
//    public String toString() {
//        StringBuilder strBuilder = new StringBuilder();
//        strBuilder.append(this.getClass().getName()).append("{");
//        Map<String,Object> fieldMap = this.enumField();
//        Set<String> keys = fieldMap.keySet();
//        for(String key : keys){
//            strBuilder.append(key).append(":").append(fieldMap.get(key)).append(",");
//        }
//        strBuilder.append("}");
//        return strBuilder.toString();
//    }

}
