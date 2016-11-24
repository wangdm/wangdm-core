package com.wangdm.core.hibernate;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class MySQLTouchDialect extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
