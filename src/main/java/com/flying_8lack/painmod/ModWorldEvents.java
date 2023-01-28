package com.flying_8lack.painmod;

import com.flying_8lack.painmod.Entity.ThiefEntity;
import com.flying_8lack.painmod.util.capabilities.PainCapability;
import com.flying_8lack.painmod.util.capabilities.PainCapabilityProvider;
import com.flying_8lack.painmod.util.capabilities.ThiefCapability;
import com.flying_8lack.painmod.util.capabilities.ThiefCapabilityProvider;
import com.flying_8lack.painmod.worldgen.ModFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PainMod.MOD_ID)
public class ModWorldEvents {



    @SubscribeEvent
    public static void wakeUPEvent(PlayerWakeUpEvent event){
        if(!event.getPlayer().getLevel().isClientSide() &&
        event.getPlayer().getLevel().getRandom().nextInt(10) < 2){
            ThiefEntity e = new ThiefEntity(ModEntity.THIEF.get(),event.getPlayer().getLevel());
            e.teleportTo(event.getPlayer().getX(),
                    event.getPlayer().getY(),
                    event.getPlayer().getZ());
            event.getPlayer().getLevel().addFreshEntity(e);


        }
    }

    @SubscribeEvent
    public static void generateTrees(final BiomeLoadingEvent event){
        ModFeature.generateTrees(event);
    }

    @SubscribeEvent
    public static void attachCapabilityToEntity(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(PainCapabilityProvider.PAIN).isPresent()){
                event.addCapability(new ResourceLocation(PainMod.MOD_ID, "properties"),
                        new PainCapabilityProvider());
            }
        }

        if(event.getObject() instanceof ThiefEntity){
            if(!event.getObject().getCapability(ThiefCapabilityProvider.THIEF).isPresent()){
                event.addCapability(new ResourceLocation(PainMod.MOD_ID, "properties"),
                        new ThiefCapabilityProvider());
            }
        }


    }










    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event){
        if(event.isWasDeath()){
            event.getOriginal().getCapability(PainCapabilityProvider.PAIN).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PainCapabilityProvider.PAIN).ifPresent(newStore -> {
                    newStore.setPainPoint(oldStore.getPainPoint());
                });
            });
        }
    }



    @SubscribeEvent
    public static void RegisterCapabilityEvent(RegisterCapabilitiesEvent event){
        event.register(PainCapability.class);
        event.register(ThiefCapability.class);
    }




}
