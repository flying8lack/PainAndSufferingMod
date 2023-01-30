package com.flying_8lack.painmod.blocks;

import com.flying_8lack.painmod.ModBlockEntity;
import com.flying_8lack.painmod.blocks.entity.tradingBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.world.ForgeChunkManager;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class tradingBlock extends BaseEntityBlock{
    protected tradingBlock(Properties pProperties) {

        super(pProperties);
        pProperties.sound(SoundType.WOOD);

    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel,
                                                                  BlockState pState,
                                                                  BlockEntityType<T> type) {
        //return super.getTicker(pLevel, pState, pBlockEntityType);
        return createTickerHelper(type, ModBlockEntity.TRADING_BLOCK.get(),tradingBlockEntity::tick);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new tradingBlockEntity(pPos, pState);
    }
}
