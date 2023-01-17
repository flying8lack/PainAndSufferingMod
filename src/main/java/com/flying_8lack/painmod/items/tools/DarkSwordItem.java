package com.flying_8lack.painmod.items.tools;

import com.flying_8lack.painmod.items.ModTier;
import com.flying_8lack.painmod.util.PainCapabilityProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

public class DarkSwordItem extends SwordItem {
    public DarkSwordItem(Properties pProperties) {
        super(ModTier.DARK, 7, 1.5f, pProperties);

    }



    @Override
    public InteractionResult useOn(UseOnContext pContext) {


            for(int i = 0; i < 8; i++){
                BlockPos pos = new BlockPos(
                        Mth.floor(pContext.getClickLocation().x() + i*pContext.getPlayer().getLookAngle().x),
                        pContext.getClickedPos().getY(),
                        Mth.floor(pContext.getClickLocation().z() + i*pContext.getPlayer().getLookAngle().z));
               //pContext.getLevel().setBlock(pos, Blocks.MAGMA_BLOCK.defaultBlockState(), 2 | 1 | 8);
                if(pContext.getLevel().getBlockState(pos).getBlock() != Blocks.AIR) {
                    pContext.getLevel().setBlockAndUpdate(pos, Blocks.MAGMA_BLOCK.defaultBlockState());
                } else {
                    pContext.getLevel().setBlockAndUpdate(pos.below(), Blocks.MAGMA_BLOCK.defaultBlockState());
                }
            }
        return super.useOn(pContext);
    }


}
