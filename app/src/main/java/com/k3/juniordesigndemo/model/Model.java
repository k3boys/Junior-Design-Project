package com.k3.juniordesigndemo.model;

public final class Model {
    public static final Model instance = new Model();

    private Model() {
    }

    public double getLastReportLat() {
        return 0.0;
    }

    public double getLastReportLong() {
        return 0.0;
    }

    public static Model instance() {
        return instance;
    }
}
