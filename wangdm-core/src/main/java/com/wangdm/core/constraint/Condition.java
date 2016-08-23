package com.wangdm.core.constraint;

public interface Condition {
    
    public Object buildConstraint(Object constraint);

    public void addEqualCondition(String prop, Object value);
    
    public void addNonCondition(String prop, Object value);

    public void addGreaterCondition(String prop, Object value);

    public void addLessCondition(String prop, Object value);
    
    public void addBetweenCondition(String prop, Object min, Object max);

    public void addLikeCondition(String prop, String value);

}
