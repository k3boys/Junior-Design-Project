package com.k3.juniordesigndemo.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.Date;

public class Report {

    public Report() {}

    public Report(String address, double lat, double lon) {
        this.address = address;
        this.location = new LatLng(lat, lon);
        this.datetime = Calendar.getInstance().getTime();
        this.details = "";
    }

    public int calcId() {
        Integer addrHash = Math.abs(this.address.hashCode());
        Integer dtHash = Math.abs(this.datetime.hashCode());
        Integer locHash = Math.abs(this.location.hashCode());


        Integer android_id = Math.abs(ReportSingleton.getDevId().hashCode());
        String str1 = addrHash.toString();
        String str2 = dtHash.toString();
        String str3 = locHash.toString();
        String str4 = android_id.toString();

        String ccat = str1 + str2 + str3 + str4;

        return addrHash * dtHash * locHash * android_id;
    }

    public String address;
    public LatLng location;
    public Date datetime;

    public String details;

    public boolean isStreet_disrepair() {
        return street_disrepair;
    }

    public void setStreet_disrepair(boolean street_disrepair) {
        this.street_disrepair = street_disrepair;
    }

    public boolean isStreet_no_sidewalks() {
        return street_no_sidewalks;
    }

    public void setStreet_no_sidewalks(boolean street_no_sidewalks) {
        this.street_no_sidewalks = street_no_sidewalks;
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

    public Integer getHome_condition() {
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

    public boolean isHome_bars() {
        return home_bars;
    }

    public void setHome_bars(boolean home_bars) {
        this.home_bars = home_bars;
    }

    public Integer isHome_broken_windows() {
        return home_broken_windows;
    }

    public void setHome_broken_windows(int home_broken_windows) {
        this.home_broken_windows = home_broken_windows;
    }

    public boolean isHome_abandoned() {
        return home_abandoned;
    }

    public void setHome_abandoned(boolean home_abandoned) {
        this.home_abandoned = home_abandoned;
    }

    public boolean isHome_for_sale() {
        return home_for_sale;
    }

    public void setHome_for_sale(boolean home_for_sale) {
        this.home_for_sale = home_for_sale;
    }

    public boolean isHome_renovated() {
        return home_renovated;
    }

    public void setHome_renovated(boolean home_renovated) {
        this.home_renovated = home_renovated;
    }

    public boolean isHome_solar() {
        return home_solar;
    }

    public void setHome_solar(boolean home_solar) {
        this.home_solar = home_solar;
    }

    public Integer isYard_landscaping() {
        return yard_landscaping;
    }

    public void setYard_landscaping(int yard_landscaping) {
        this.yard_landscaping = yard_landscaping;
    }

    public boolean isYard_abandoned_appliance() {
        return yard_abandoned_appliance;
    }

    public void setYard_abandoned_appliance(boolean yard_abandoned_appliance) {
        this.yard_abandoned_appliance = yard_abandoned_appliance;
    }

    public boolean isYard_abandoned_equipment() {
        return yard_abandoned_equipment;
    }

    public void setYard_abandoned_equipment(boolean yard_abandoned_equipment) {
        this.yard_abandoned_equipment = yard_abandoned_equipment;
    }

    public boolean isYard_trash() {
        return yard_trash;
    }

    public void setYard_trash(boolean yard_trash) {
        this.yard_trash = yard_trash;
    }

    public boolean isYard_debris() {
        return yard_debris;
    }

    public void setYard_debris(boolean yard_debris) {
        this.yard_debris = yard_debris;
    }

    public boolean isYard_standing_water() {
        return yard_standing_water;
    }

    public void setYard_standing_water(boolean yard_standing_water) {
        this.yard_standing_water = yard_standing_water;
    }

    public boolean isYard_unused_green() {
        return yard_unused_green;
    }

    public void setYard_unused_green(boolean yard_unused_green) {
        this.yard_unused_green = yard_unused_green;
    }

    public boolean isYard_tree_loss() {
        return yard_tree_loss;
    }

    public void setYard_tree_loss(boolean yard_tree_loss) {
        this.yard_tree_loss = yard_tree_loss;
    }

    public boolean isVehicle_abandoned_street() {
        return vehicle_abandoned_street;
    }

    public void setVehicle_abandoned_street(boolean vehicle_abandoned_street) {
        this.vehicle_abandoned_street = vehicle_abandoned_street;
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

    public Integer getVehicle_parked() {
        return vehicle_parked;
    }

    public void setVehicle_parked(int vehicle_parked) {
        this.vehicle_parked = vehicle_parked;
    }

    public boolean isVehicle_oversized() {
        return vehicle_oversized;
    }

    public void setVehicle_oversized(boolean vehicle_oversized) {
        this.vehicle_oversized = vehicle_oversized;
    }

    public boolean isMisc_need_beautification() {
        return misc_need_beautification;
    }

    public void setMisc_need_beautification(boolean misc_need_beautification) {
        this.misc_need_beautification = misc_need_beautification;
    }

    public boolean isMisc_gentrification() {
        return misc_gentrification;
    }

    public void setMisc_gentrification(boolean misc_gentrification) {
        this.misc_gentrification = misc_gentrification;
    }

    public boolean isMisc_noise() {
        return misc_noise;
    }

    public void setMisc_noise(boolean misc_noise) {
        this.misc_noise = misc_noise;
    }

    public boolean isMisc_basketball_goals() {
        return misc_basketball_goals;
    }

    public void setMisc_basketball_goals(boolean misc_basketball_goals) {
        this.misc_basketball_goals = misc_basketball_goals;
    }

    public boolean isMisc_equipment_storage() {
        return misc_equipment_storage;
    }

    public void setMisc_equipment_storage(boolean misc_equipment_storage) {
        this.misc_equipment_storage = misc_equipment_storage;
    }

    public boolean isMisc_vacant_lot() {
        return misc_vacant_lot;
    }

    public void setOther_vacant_lot(boolean other_vacant_lot) {
        this.misc_vacant_lot = misc_vacant_lot;
    }

    public boolean isMisc_bus_stop() {
        return misc_bus_stop;
    }

    public void setMisc_bus_stop(boolean misc_bus_stop) {
        this.misc_bus_stop = misc_bus_stop;
    }

    public boolean isMisc_benchmark() {
        return misc_benchmark;
    }

    public void setMisc_benchmark(boolean misc_benchmark) {
        this.misc_benchmark = misc_benchmark;
    }

    boolean street_disrepair;
    boolean street_no_sidewalks;
    boolean street_drain_blocked;
    boolean street_hydrant_oos;

    int home_condition;
    boolean home_disrepair;
    boolean home_bars;
    int home_broken_windows;
    boolean home_abandoned;
    boolean home_for_sale;
    boolean home_renovated;
    boolean home_solar;

    int yard_landscaping;
    boolean yard_abandoned_appliance;
    boolean yard_abandoned_equipment;
    boolean yard_trash;
    boolean yard_debris;
    boolean yard_standing_water;
    boolean yard_unused_green;
    boolean yard_tree_loss;

    boolean vehicle_abandoned_street;
    boolean vehicle_abandoned_driveway;
    boolean vehicle_abandoned_other;
    int vehicle_parked;
    boolean vehicle_oversized;

    boolean misc_need_beautification;
    boolean misc_gentrification;
    boolean misc_noise;
    boolean misc_basketball_goals;
    boolean misc_equipment_storage;
    boolean misc_vacant_lot;
    boolean misc_bus_stop;
    boolean misc_benchmark;
}