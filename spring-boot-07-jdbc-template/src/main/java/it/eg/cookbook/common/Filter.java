package it.eg.cookbook.common;

public class Filter {

    String sql;

    public Filter(String sql) {
        this.sql = sql;
    }

    public String getWhereCondition() {
        return sql;
    }

}