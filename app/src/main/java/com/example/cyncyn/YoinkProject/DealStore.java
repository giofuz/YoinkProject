package com.example.cyncyn.YoinkProject;

import java.util.List;


public class DealStore {

    private static DealStore instance = null;

    public static DealStore getInstance() {
        if (instance == null) {
            instance = new DealStore();
        }
        return instance;
    }

    private List<Deal> mDeals;

    private DealStore() {}

    public List<Deal> getDeals() {
        return mDeals;
    }

    public void setDeals(List<Deal> mDeals) {
        this.mDeals = mDeals;
    }
}
