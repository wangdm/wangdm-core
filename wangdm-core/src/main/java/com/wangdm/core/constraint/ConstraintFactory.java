package com.wangdm.core.constraint;

public interface ConstraintFactory {
    
    public Constraint createConstraint(Class<?> clazz);

}
