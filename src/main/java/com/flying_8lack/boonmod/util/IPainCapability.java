package com.flying_8lack.boonmod.util;

public interface IPainCapability {


    int getPainPoint();
    void setPainPoint(int point);

    void addPainPoint(int amount);
    void subPainPoint(int amount);

    boolean canAfford(int amount);
}
