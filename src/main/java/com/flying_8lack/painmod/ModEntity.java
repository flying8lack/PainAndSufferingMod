package com.flying_8lack.painmod;

import com.flying_8lack.painmod.Entity.ThiefEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntity {

    public static final DeferredRegister<EntityType<?>> ENTITY_REGISTRY =
            DeferredRegister.create(ForgeRegistries.ENTITIES, PainMod.MOD_ID);

    public static RegistryObject<EntityType<ThiefEntity>> THIEF =
            ENTITY_REGISTRY.register("thief",
                    () -> EntityType.Builder.of(ThiefEntity::new, MobCategory.MONSTER)
                            .sized(0.8f, 1.8f)
                            .build(new ResourceLocation(PainMod.MOD_ID, "thief").toString())
            );

    public static void register(IEventBus eventbus){
        ENTITY_REGISTRY.register(eventbus);
    }
}
