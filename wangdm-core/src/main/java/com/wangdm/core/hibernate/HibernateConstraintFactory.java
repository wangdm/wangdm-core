package com.wangdm.core.hibernate;

import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.ConstraintFactory;

public class HibernateConstraintFactory implements ConstraintFactory {

    @Override
    public Constraint createConstraint(Class<?> clazz) {
        Constraint constraint = new HibernateConstraint();
        constraint.setEntityClass(clazz);
        return constraint;
    }

}
