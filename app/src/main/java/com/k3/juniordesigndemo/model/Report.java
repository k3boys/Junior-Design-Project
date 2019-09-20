package com.k3.juniordesigndemo.model;

import android.location.Location;

import java.util.Calendar;
import java.util.Date;

public class Report {

    public Report() {}

    public Report(String address) {
        this.address = address;
        this.location = new Location(address);
        this.datetime = Calendar.getInstance().getTime();
        this.details = "";
    }

    public int calcId() {
        int addrHash = this.address.hashCode();
        int dtHash = this.datetime.hashCode();
        int locHash = this.location.hashCode();
        return addrHash * dtHash * locHash;
    }

    String address;
    Location location;
    Date datetime;

    public String details;

    public boolean isStreet_disrepair() {
        return street_disrepair;
    }

    public void setStreet_disrepair(boolean street_disrepair) {
        this.street_disrepair = street_disrepair;
    }

    public boolean isStreet_drain_blocked() {
        return street_drain_blocked;
    }

    public void setStreet_drain_blocked(boolean street_drain_blocked) {
        this.street_drain_blocked = street_drain_blocked;
    }

    public boolean isStreet_hydrant_oos() {
        return street_hydrant_oos;
    }

    public void setStreet_hydrant_oos(boolean street_hydrant_oos) {
        this.street_hydrant_oos = street_hydrant_oos;
    }

    public boolean isStreet_no_sidewalks() {
        return street_no_sidewalks;
    }

    public void setStreet_no_sidewalks(boolean street_no_sidewalks) {
        this.street_no_sidewalks = street_no_sidewalks;
    }

    public boolean isHome_abandoned() {
        return home_abandoned;
    }

    public void setHome_abandoned(boolean home_abandoned) {
        this.home_abandoned = home_abandoned;
    }

    public boolean isHome_bars() {
        return home_bars;
    }

    public void setHome_bars(boolean home_bars) {
        this.home_bars = home_bars;
    }

    public boolean isHome_broken_windows() {
        return home_broken_windows;
    }

    public void setHome_broken_windows(boolean home_broken_windows) {
        this.home_broken_windows = home_broken_windows;
    }

    public int getHome_condition() {
        return home_condition;
    }

    public void setHome_condition(int home_condition) {
        this.home_condition = home_condition;
    }

    public boolean isHome_disrepair() {
        return home_disrepair;
    }

    public void setHome_disrepair(boolean home_disrepair) {
        this.home_disrepair = home_disrepair;
    }

    public boolean isHome_renovated() {
        return home_renovated;
    }

    public void setHome_renovated(boolean home_renovated) {
        this.home_renovated = home_renovated;
    }

    public boolean isHome_selling() {
        return home_selling;
    }

    public void setHome_selling(boolean home_selling) {
        this.home_selling = home_selling;
    }

    public boolean isHome_solar() {
        return home_solar;
    }

    public void setHome_solar(boolean home_solar) {
        this.home_solar = home_solar;
    }

    public boolean isYard_abandoned_junk() {
        return yard_abandoned_junk;
    }

    public void setYard_abandoned_junk(boolean yard_abandoned_junk) {
        this.yard_abandoned_junk = yard_abandoned_junk;
    }

    public boolean isYard_back_junk() {
        return yard_back_junk;
    }

    public void setYard_back_junk(boolean yard_back_junk) {
        this.yard_back_junk = yard_back_junk;
    }

    public boolean isYard_landscaping() {
        return yard_landscaping;
    }

    public void setYard_landscaping(boolean yard_landscaping) {
        this.yard_landscaping = yard_landscaping;
    }

    public boolean isVehicle_abandoned_driveway() {
        return vehicle_abandoned_driveway;
    }

    public void setVehicle_abandoned_driveway(boolean vehicle_abandoned_driveway) {
        this.vehicle_abandoned_driveway = vehicle_abandoned_driveway;
    }

    public boolean isVehicle_abandoned_other() {
        return vehicle_abandoned_other;
    }

    public void setVehicle_abandoned_other(boolean vehicle_abandoned_other) {
        this.vehicle_abandoned_other = vehicle_abandoned_other;
    }

    public boolean isVehicle_abandoned_street() {
        return vehicle_abandoned_street;
    }

    public void setVehicle_abandoned_street(boolean vehicle_abandoned_street) {
        this.vehicle_abandoned_street = vehicle_abandoned_street;
    }

    public boolean isVehicle_oversized() {
        return vehicle_oversized;
    }

    public void setVehicle_oversized(boolean vehicle_oversized) {
        this.vehicle_oversized = vehicle_oversized;
    }

    public int getVehicle_parked() {
        return vehicle_parked;
    }

    public void setVehicle_parked(int vehicle_parked) {
        this.vehicle_parked = vehicle_parked;
    }

    public boolean isEnvironment_debris() {
        return environment_debris;
    }

    public void setEnvironment_debris(boolean environment_debris) {
        this.environment_debris = environment_debris;
    }

    public boolean isEnvironment_standing_water() {
        return environment_standing_water;
    }

    public void setEnvironment_standing_water(boolean environment_standing_water) {
        this.environment_standing_water = environment_standing_water;
    }

    public boolean isEnvironment_trash() {
        return environment_trash;
    }

    public void setEnvironment_trash(boolean environment_trash) {
        this.environment_trash = environment_trash;
    }

    public boolean isEnvironment_tree_loss() {
        return environment_tree_loss;
    }

    public void setEnvironment_tree_loss(boolean environment_tree_loss) {
        this.environment_tree_loss = environment_tree_loss;
    }

    public boolean isEnvironment_unused_green() {
        return environment_unused_green;
    }

    public void setEnvironment_unused_green(boolean environment_unused_green) {
        this.environment_unused_green = environment_unused_green;
    }

    public boolean isMisc_abandoned_eqpt() {
        return misc_abandoned_eqpt;
    }

    public void setMisc_abandoned_eqpt(boolean misc_abandoned_eqpt) {
        this.misc_abandoned_eqpt = misc_abandoned_eqpt;
    }

    public boolean isMisc_gentrification() {
        return misc_gentrification;
    }

    public void setMisc_gentrification(boolean misc_gentrification) {
        this.misc_gentrification = misc_gentrification;
    }

    public boolean isMisc_need_beautification() {
        return misc_need_beautification;
    }

    public void setMisc_need_beautification(boolean misc_need_beautification) {
        this.misc_need_beautification = misc_need_beautification;
    }

    public boolean isMisc_noise() {
        return misc_noise;
    }

    public void setMisc_noise(boolean misc_noise) {
        this.misc_noise = misc_noise;
    }

    public boolean isOther_basketball_goals() {
        return other_basketball_goals;
    }

    public void setOther_basketball_goals(boolean other_basketball_goals) {
        this.other_basketball_goals = other_basketball_goals;
    }

    public boolean isOther_benchmark() {
        return other_benchmark;
    }

    public void setOther_benchmark(boolean other_benchmark) {
        this.other_benchmark = other_benchmark;
    }

    public boolean isOther_bus_stop() {
        return other_bus_stop;
    }

    public void setOther_bus_stop(boolean other_bus_stop) {
        this.other_bus_stop = other_bus_stop;
    }

    public boolean isOther_eqpt_storage() {
        return other_eqpt_storage;
    }

    public void setOther_eqpt_storage(boolean other_eqpt_storage) {
        this.other_eqpt_storage = other_eqpt_storage;
    }

    public boolean isOther_solar() {
        return other_solar;
    }

    public void setOther_solar(boolean other_solar) {
        this.other_solar = other_solar;
    }

    public boolean isOther_vacant_lot() {
        return other_vacant_lot;
    }

    public void setOther_vacant_lot(boolean other_vacant_lot) {
        this.other_vacant_lot = other_vacant_lot;
    }

    boolean street_disrepair;
    boolean street_drain_blocked;
    boolean street_hydrant_oos;
    boolean street_no_sidewalks;

    boolean home_abandoned;
    boolean home_bars;
    boolean home_broken_windows;
    int home_condition;
    boolean home_disrepair;
    boolean home_renovated;
    boolean home_selling;
    boolean home_solar;

    boolean yard_abandoned_junk;
    boolean yard_back_junk;
    boolean yard_landscaping;

    boolean vehicle_abandoned_driveway;
    boolean vehicle_abandoned_other;
    boolean vehicle_abandoned_street;
    boolean vehicle_oversized;
    int vehicle_parked;

    boolean environment_debris;
    boolean environment_standing_water;
    boolean environment_trash;
    boolean environment_tree_loss;
    boolean environment_unused_green;

    boolean misc_abandoned_eqpt;
    boolean misc_gentrification;
    boolean misc_need_beautification;
    boolean misc_noise;

    boolean other_basketball_goals;
    boolean other_benchmark;
    boolean other_bus_stop;
    boolean other_eqpt_storage;
    boolean other_solar;
    boolean other_vacant_lot;

}
