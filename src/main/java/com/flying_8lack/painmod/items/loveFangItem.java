package com.flying_8lack.painmod.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class loveFangItem extends Item implements IForgeItem {
    public loveFangItem(Properties pProperties) {

        super(pProperties);
        pProperties.stacksTo(1);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, tooltip, pIsAdvanced);
        String mama = "You will never die as long as bonded entity is alive. "+
                "Left click (attack) using the fang to bond the loved one. ";
        if(pStack.hasTag()){
            mama = mama + ChatFormatting.RED + "The fang is connected to the loved one. ";
        }


        tooltip.add(new TextComponent(mama));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if(entity instanceof LivingEntity) {
            CompoundTag BondData = new CompoundTag();
            BondData.putInt("Bond", entity.getId());
            stack.setTag(BondData);

            return true;
        } else {
            return super.onLeftClickEntity(stack, player, entity);
        }



    }





    @Override
    public boolean isFoil(ItemStack pStack) {
        return pStack.hasTag();
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, @NotNull Entity pEntity, int pSlotId, boolean pIsSelected) {
        if(pStack.hasTag() && !pLevel.isClientSide()) {
            LivingEntity bonded = (LivingEntity) pLevel.getEntity(pStack.getTag().getInt("Bond"));

            if(bonded != null){
                ((LivingEntity)pEntity).setHealth(bonded.getHealth());
                //bonded.setHealth(((LivingEntity)pEntity).getHealth());

                if(((LivingEntity)pEntity).isOnFire()){
                    bonded.setSecondsOnFire(1);
                }



                if(bonded.isOnFire()){
                    ((LivingEntity)pEntity).setSecondsOnFire(1);
                }

                if(bonded.isDeadOrDying()){
                    ((LivingEntity)pEntity).die(DamageSource.MAGIC);
                }



            } else {
                pStack.getTag().remove("Bond");
                pEntity.sendMessage(new TextComponent("The fang is no longer attached"), pEntity.getUUID());
            }

        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }
}
