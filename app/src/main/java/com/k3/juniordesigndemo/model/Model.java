package com.k3.juniordesigndemo.model;

public final class Model {
    public static final Model instance = new Model();

    private Model() {
    }

    public double getLastReportLat() {
        return 33.75;
    }

    public double getLastReportLong() {
        return -84.38;
    }

    public static Model instance() {
        return instance;
    }
}
