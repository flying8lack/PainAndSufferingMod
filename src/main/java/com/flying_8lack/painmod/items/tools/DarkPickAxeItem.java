package com.flying_8lack.painmod.items.tools;

import com.flying_8lack.painmod.Entity.Rocket;
import com.flying_8lack.painmod.ModEntity;
import com.flying_8lack.painmod.items.ModTier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;

public class DarkPickAxeItem extends PickaxeItem {
    public DarkPickAxeItem(Properties pProperties) {
        super(ModTier.DARK, 2, 0.2f, pProperties);


    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        Rocket r = new Rocket(ModEntity.ROCKET.get(), pLevel);
        r.setTargetPos(pPlayer.getOnPos());
        r.teleportTo(pPlayer.getX()+6, pPlayer.getY()+40, pPlayer.getZ()+6);
        pLevel.addFreshEntity(r);

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
