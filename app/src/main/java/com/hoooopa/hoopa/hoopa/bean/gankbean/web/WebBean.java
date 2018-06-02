package com.hoooopa.hoopa.hoopa.bean.gankbean.web;

import java.util.List;

public class WebBean {

    private boolean error;

    private List<WebResults> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<WebResults> getResults() {
        return results;
    }

    public void setResults(List<WebResults> results) {
        this.results = results;
    }
}
