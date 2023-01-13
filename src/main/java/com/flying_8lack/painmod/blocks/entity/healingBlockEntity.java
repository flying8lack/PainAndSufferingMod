package com.flying_8lack.painmod.blocks.entity;

import com.flying_8lack.painmod.ModBlockEntity;
import com.flying_8lack.painmod.util.PainCapabilityProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.extensions.IForgeBlockEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class healingBlockEntity extends BlockEntity implements IForgeBlockEntity {
    public healingBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntity.OG.get(),pPos, pBlockState);
    }


    @Override
    public void setChanged() {
        this.setChanged();

    }

    @Override
    public void load(CompoundTag pTag) {

        super.load(pTag);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
    }



    public static void tick(Level level, BlockPos p, BlockState pState, BlockEntity pBlockEntity) {
        if(!level.isClientSide()) {
            for (Entity i : level.getEntities(null, new AABB(p.getX() - 8, p.getY() - 2, p.getZ() - 8,
                    p.getX() + 8, p.getY() + 2, p.getZ() + 8))) {
                if (i instanceof Player) {
                    i.getCapability(PainCapabilityProvider.PAIN).ifPresent(pain -> {
                        if (!pain.getHasEffect()) {
                            if (pain.canAfford(10)) {
                                ((Player) i).addEffect(new MobEffectInstance(MobEffects.REGENERATION,
                                        100, 2));
                                ((Player) i).addEffect(new MobEffectInstance(MobEffects.SATURATION,
                                        100, 2));

                                pain.subPainPoint(10);
                                pain.setHasEffect(true);
                                pain.setLastEffectCoolDown(140);
                                i.sendMessage(new TextComponent("Added an effect"), i.getUUID());


                            }
                        } else {

                            pain.tickUpdate();
                        }

                    });


                }
            }
        }
    }


}
