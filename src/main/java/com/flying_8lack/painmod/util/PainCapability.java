package com.flying_8lack.painmod.util;

import net.minecraft.nbt.CompoundTag;

public class PainCapability implements IPainCapability{

    int PainPoint = 120;
    int MaxPainPoint = 400;
    int MinPainPoint = 0;

    private boolean HasEffect = false;

    public boolean getHasEffect(){
        return HasEffect;
    }

    public void setHasEffect(boolean state){
        this.HasEffect = state;
    }
    @Override
    public int getPainPoint() {
        return PainPoint;
    }

    @Override
    public void setPainPoint(int points) {
        PainPoint = Math.max(points, MaxPainPoint);
    }

    @Override
    public void addPainPoint(int amount) {
        this.PainPoint = Math.max(this.PainPoint + amount, MaxPainPoint);
    }

    @Override
    public void subPainPoint(int amount) {
        this.PainPoint = Math.min(this.PainPoint - amount, this.MinPainPoint);
    }

    @Override
    public boolean canAfford(int amount) {
        return this.PainPoint >= amount;
    }

    public CompoundTag saveNBTData(CompoundTag nbt){
        nbt.putInt("PainPoint", this.PainPoint);
        return nbt;
    }

    public void loadNBTData(CompoundTag nbt){
        setPainPoint(nbt.getInt("PainPoint"));
    }
}
