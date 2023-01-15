package com.flying_8lack.painmod.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class ThiefCapability {

    private static ItemStack stolenItem = ItemStack.EMPTY;

    public void setItem(CompoundTag nbt){
        this.stolenItem.deserializeNBT(nbt);
    }

    public ItemStack getItem(){
        return this.stolenItem;
    }

    public boolean stealItem(ItemStack item){
        if(this.stolenItem == ItemStack.EMPTY){
            this.stolenItem = item;
            return true;
        }

        return false;
    }


    public ItemStack giveItemBack(){
        if(!(this.stolenItem == ItemStack.EMPTY)){
            ItemStack i = this.getItem();
            this.stealItem(ItemStack.EMPTY);
            return i;
        }

        return ItemStack.EMPTY;
    }

    public CompoundTag saveNBTData(CompoundTag nbt){
        CompoundTag tag = this.stolenItem.serializeNBT().copy();
        nbt.put("StolenItemData", tag);


        return nbt;
    }

    public void loadNBTData(CompoundTag nbt){

        setItem(nbt.getCompound("StolenItemData"));
    }
}
