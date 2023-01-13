package com.flying_8lack.boonmod;

import com.flying_8lack.boonmod.util.PainCapability;
import com.flying_8lack.boonmod.util.PainCapabilityProvider;
import com.flying_8lack.boonmod.worldgen.ModFeature;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.commands.PlaySoundCommand;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.event.ContainerScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.event.sound.PlaySoundSourceEvent;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import java.util.ArrayList;

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
