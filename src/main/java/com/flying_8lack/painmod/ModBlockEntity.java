package com.flying_8lack.painmod;

import com.flying_8lack.painmod.blocks.entity.healingBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntity {

    public static DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES,BoonMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<healingBlockEntity>> OG =
            BLOCK_ENTITIES.register("healing_block_entity",
                    () -> BlockEntityType.Builder.of(healingBlockEntity::new,
                            ModBlock.HEAL_BLOCK.get()).build(null));

    public static void register(IEventBus eventbus){
        BLOCK_ENTITIES.register(eventbus);
    }
}
