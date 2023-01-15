package com.flying_8lack.painmod;

import com.flying_8lack.painmod.Entity.ThiefEntity;
import com.flying_8lack.painmod.util.PainCapability;
import com.flying_8lack.painmod.util.PainCapabilityProvider;
import com.flying_8lack.painmod.util.ThiefCapabilityProvider;
import com.flying_8lack.painmod.worldgen.ModFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
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
        } else if(event.getObject() instanceof ThiefEntity){
            if(!event.getObject().getCapability(ThiefCapabilityProvider.THIEF).isPresent()){
                event.addCapability(new ResourceLocation(PainMod.MOD_ID, "properties"),
                        new ThiefCapabilityProvider());
            }
        }


    }



    @SubscribeEvent
    public static void onPlayerAttackEntity(AttackEntityEvent event){
        if(event.getTarget().isAlive() &&
                (event.getPlayer().distanceTo(event.getTarget()) <= 8)) {
            event.getPlayer().getCapability(PainCapabilityProvider.PAIN).ifPresent(m -> {
                m.addPainPoint(10);
            });
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
    }
    @SubscribeEvent
    public static void onSleepEvent(PlayerWakeUpEvent event){



        int X = (int)Math.floor(event.getPlayer().getLevel().random.nextGaussian() * 32)
                + event.getPlayer().getBlockX();
        int Z = (int)Math.floor(event.getPlayer().getLevel().random.nextGaussian() * 32)
                + event.getPlayer().getBlockZ();
        int Y = event.getPlayer().getLevel().getHeight(Heightmap.Types.WORLD_SURFACE,
                X, Z);
        //event.getPlayer().getLevel().playLocalSound(X, event.getPlayer().getY(), Z, SoundEvents.ENDERMAN_STARE,
        //                    SoundSource.MASTER, 6.0f, 0.9f, false);
        if(event.getPlayer().getLevel().random.nextInt(10) <= 2) {
            event.getPlayer().teleportTo(X, Y, Z);
        }



        //event.getPlayer().stopSleeping();


    }



}
