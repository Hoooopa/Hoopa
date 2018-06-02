package com.hoooopa.hoopa.hoopa.bean.gankbean.android;

import java.util.List;

/**
 * Created by Pray on 2018/6/1.
 */

public class Androidbean {

    private boolean error;

    private List<AndroidResults> results;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<AndroidResults> getResults() {
        return results;
    }

    public void setResults(List<AndroidResults> results) {
        this.results = results;
    }
}
