package com.flying_8lack.painmod.util;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ThiefCapabilityProvider implements ICapabilityProvider,
        ICapabilitySerializable<CompoundTag> {

    public static Capability<ThiefCapability> THIEF = CapabilityManager.get(
            new CapabilityToken<ThiefCapability>() {});

    private ThiefCapability thiefSystem = null;

    private final LazyOptional<ThiefCapability> optional = LazyOptional.of(this::CreateThiefSystem);

    private ThiefCapability CreateThiefSystem() {
        if(this.thiefSystem == null){
            this.thiefSystem = new ThiefCapability();
        }

        return thiefSystem;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == this.THIEF){
            return optional.cast();
        }

        return optional.empty();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == this.THIEF){
            return optional.cast();
        }

        return optional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        return this.CreateThiefSystem().saveNBTData(nbt);
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.CreateThiefSystem().loadNBTData(nbt);
    }
}
