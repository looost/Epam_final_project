package by.training.model.form;

import java.util.List;

public class SearchQuery {
    private StringBuilder sql;
    private List<String> params;

    public SearchQuery() {
    }

    public SearchQuery(StringBuilder sql, List<String> params) {
        this.sql = sql;
        this.params = params;
    }

    public StringBuilder getSql() {
        return sql;
    }

    public void setSql(StringBuilder sql) {
        this.sql = sql;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
