package com.flying_8lack.painmod.util;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PainCapabilityProvider implements ICapabilityProvider,
        ICapabilitySerializable<CompoundTag> {
    public static Capability<PainCapability> PAIN = CapabilityManager.get(
            new CapabilityToken<PainCapability>() {});

    private PainCapability PainCap = null;
    private final LazyOptional<PainCapability> optional = LazyOptional.of(this::CreatePainPoint);

    public PainCapability CreatePainPoint(){
        if(PainCap == null){
            PainCap = new PainCapability();
        }

        return this.PainCap;
    }
    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == this.PAIN){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == this.PAIN){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        return CreatePainPoint().saveNBTData(nbt);
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        CreatePainPoint().loadNBTData(nbt);
    }
}
