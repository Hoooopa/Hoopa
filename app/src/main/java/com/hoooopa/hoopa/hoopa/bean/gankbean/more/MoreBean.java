package com.hoooopa.hoopa.hoopa.bean.gankbean.more;

import java.util.List;

public class MoreBean {

    private boolean error;

    private List<MoreResults> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<MoreResults> getResults() {
        return results;
    }

    public void setResults(List<MoreResults> results) {
        this.results = results;
    }
}
