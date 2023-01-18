package com.flying_8lack.painmod.util.capabilities;

import net.minecraft.nbt.CompoundTag;

public class PainCapability implements IPainCapability{

    private int PainPoint = 120;
    private int MaxPainPoint = 600;
    private int MinPainPoint = 0;

    private int LastEffectCoolDown = 0;
    private boolean HasEffect = false;


    public void setLastEffectCoolDown(int cooldown){
        LastEffectCoolDown = cooldown;
    }

    public void tickUpdate(){

        LastEffectCoolDown -= 1;
        if(LastEffectCoolDown < 1){
            setLastEffectCoolDown(0);
            setHasEffect(false);
        }

    }

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

        PainPoint = points >= MaxPainPoint ? MaxPainPoint : points;
    }

    @Override
    public void addPainPoint(int amount) {

        this.PainPoint = this.PainPoint + amount >= MaxPainPoint ? MaxPainPoint : this.PainPoint + amount;
    }

    @Override
    public void subPainPoint(int amount) {

        this.PainPoint = this.PainPoint - amount <= this.MinPainPoint? this.MinPainPoint : this.PainPoint - amount;

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
