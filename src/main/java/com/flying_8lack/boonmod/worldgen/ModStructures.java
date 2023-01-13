package com.flying_8lack.boonmod.worldgen;

import com.flying_8lack.boonmod.BoonMod;
import com.flying_8lack.boonmod.worldgen.structures.scaryStructure;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModStructures {

    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE =
            DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, BoonMod.MOD_ID);

    public static final RegistryObject<StructureFeature<?>> scary_place =
            DEFERRED_REGISTRY_STRUCTURE.register("scary_place", scaryStructure::new);
    public static void register(IEventBus eventBus) {
        DEFERRED_REGISTRY_STRUCTURE.register(eventBus);
    }
}
