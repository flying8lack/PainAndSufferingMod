package com.flying_8lack.painmod.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.extensions.IForgeItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class hateFangItem extends Item implements IForgeItem {
    public hateFangItem(Properties pProperties) {
        super(pProperties);


    }



    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, tooltip, pIsAdvanced);
        String mama = "A powerful fang to bond to the hated one. "+
                "Left click (attack) using the fang to connect to the despised one. \n";
        if(pStack.hasTag()){
            mama = mama + ChatFormatting.RED + "The fang is connected and the hate rose. ";
        }


        tooltip.add(new TextComponent(mama));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if(entity instanceof LivingEntity) {
            CompoundTag BondData = new CompoundTag();
            BondData.putInt("Bond", entity.getId());
            stack.setTag(BondData);


        }

        return super.onLeftClickEntity(stack, player, entity);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player User, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide()) {

            List<Entity> M = pLevel.getEntitiesOfClass( Entity.class,
                    new AABB(User.getX()-10,User.getY()-10,User.getZ()-10
                    ,User.getX()+10,User.getY()+10,User.getZ()+10),
                    EntitySelector.LIVING_ENTITY_STILL_ALIVE);
            for (Entity i : M) {
                if (i instanceof LivingEntity && !(i instanceof Player)) {


                    i.hurt(DamageSource.playerAttack(User), 20.0f);






                }

            }
        }
        User.hurt(DamageSource.MAGIC,5.0f);

        return super.use(pLevel, User, pUsedHand);
    }
}
