package com.flying_8lack.painmod.blocks;

import com.flying_8lack.painmod.PainMod;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

import java.util.ArrayList;
import java.util.Optional;


public class bigHouseBlock extends Block {

    private static final ResourceLocation STRUCT = new ResourceLocation(PainMod.MOD_ID,
            "structures/scary_place");
    private ResourceManager resourceManager;
    public bigHouseBlock(Properties pProperties) {
        super(pProperties);
    }

    private CompoundTag getBlocks(ResourceManager r){
        try {
            Optional<Resource> rs = Optional.of(r.getResource(bigHouseBlock.STRUCT));

            return NbtIo.readCompressed(
                    rs.get().getInputStream());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }



    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if(!level.isClientSide()) {
            CompoundTag nbt = getBlocks(resourceManager);
            ListTag blockList = nbt.getList("blocks", 10);
            ArrayList<BlockState> palette = getBuildingPalette(nbt);
            for (int i = 0; i < blockList.size(); i++) {
                CompoundTag blockNBT = blockList.getCompound(i);
                ListTag blockPos = blockNBT.getList("pos", 3);
                level.setBlockAndUpdate(
                        new BlockPos(blockPos.getInt(0) + pos.getX(),
                                blockPos.getInt(1) + pos.getY(),
                                blockPos.getInt(2) + pos.getZ()),
                        palette.get(blockNBT.getInt("state"))
                );

            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    private ArrayList<BlockState> getBuildingPalette(CompoundTag nbt) {
        ArrayList<BlockState> palette = new ArrayList<>();
        ListTag paletteNbt = nbt.getList("palette", 10);
        for(int i = 0; i < paletteNbt.size(); i++)
            palette.add(NbtUtils.readBlockState(paletteNbt.getCompound(i)));
        return palette;
    }


}
