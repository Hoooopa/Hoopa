package com.hoooopa.hoopa.hoopa.bean.gankbean.ios;

import java.util.List;

public class IosBean {

    private boolean error;

    private List<IosResults> results;

    public List<IosResults> getResults() {
        return results;
    }

    public void setResults(List<IosResults> results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
