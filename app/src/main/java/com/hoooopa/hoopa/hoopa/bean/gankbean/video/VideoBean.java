package com.hoooopa.hoopa.hoopa.bean.gankbean.video;

import java.util.List;

public class VideoBean {

    private boolean error;

    private List<VideoResults> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<VideoResults> getResults() {
        return results;
    }

    public void setResults(List<VideoResults> results) {
        this.results = results;
    }
}
