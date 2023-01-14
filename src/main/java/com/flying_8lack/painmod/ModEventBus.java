package com.flying_8lack.painmod;

import com.flying_8lack.painmod.Entity.ThiefEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PainMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBus {

    @SubscribeEvent
    public static void entityAttributesEvent(EntityAttributeCreationEvent event){
        event.put(ModEntity.THIEF.get(), ThiefEntity.setAttributes());
    }
}
