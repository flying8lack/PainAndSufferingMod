package com.flying_8lack.painmod;

import com.flying_8lack.painmod.util.PainCapability;
import com.flying_8lack.painmod.util.PainCapabilityProvider;
import com.flying_8lack.painmod.worldgen.ModFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BoonMod.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void generateTrees(final BiomeLoadingEvent event){
        ModFeature.generateTrees(event);
    }

    @SubscribeEvent
    public static void attachCapabilityToPlayer(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(PainCapabilityProvider.PAIN).isPresent()){
                event.addCapability(new ResourceLocation(BoonMod.MOD_ID, "properties"),
                        new PainCapabilityProvider());
            }
        }
    }




    @SubscribeEvent
    public static void onPlayerAttackEntity(AttackEntityEvent event){
        if(!event.getEntityLiving().getClassification(false).isFriendly()) {
            event.getPlayer().getCapability(PainCapabilityProvider.PAIN).ifPresent(m -> {
                m.addPainPoint(1);
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
    public static void onSleepEvent(PlayerSleepInBedEvent event){



        int X = (int)Math.floor(event.getPlayer().getLevel().random.nextGaussian() * 6)
                + event.getPlayer().getBlockX();
        int Z = (int)Math.floor(event.getPlayer().getLevel().random.nextGaussian() * 6)
                + event.getPlayer().getBlockZ();
        //int Y = event.getPlayer().getLevel().getHeight(Heightmap.Types.WORLD_SURFACE,
                //X, Z);


        event.getPlayer().getLevel().playLocalSound(X,event.getPlayer().getY(),Z, SoundEvents.AMBIENT_CAVE,
                SoundSource.MASTER, 6.0f, 0.9f, false);



        //event.getPlayer().stopSleeping();


    }



}
