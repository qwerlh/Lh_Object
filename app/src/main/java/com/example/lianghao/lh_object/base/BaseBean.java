package com.example.lianghao.lh_object.base;

import com.example.lianghao.lh_object.entity.MeiziBean;

import java.util.List;

/**
 * Created by LiangHao on 2016/11/30.
 */

public class BaseBean <T> {
    private boolean error;
    private List<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
