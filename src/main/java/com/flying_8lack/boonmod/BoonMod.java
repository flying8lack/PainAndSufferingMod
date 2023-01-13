package com.flying_8lack.boonmod;


import com.flying_8lack.boonmod.keys.keyBind;
import com.flying_8lack.boonmod.network.ModMessages;
import com.flying_8lack.boonmod.overlay.CustomOverlay;
import com.flying_8lack.boonmod.worldgen.ModStructures;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BoonMod.MOD_ID)
public class BoonMod
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "boonmod";

    public BoonMod()
    {
        // Register the setup method for modloading
        IEventBus eventbus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItem.register(eventbus);
        ModBlock.register(eventbus);
        ModBlockEntity.register(eventbus);
        ModStructures.register(eventbus);

        eventbus.addListener(this::setup);
        eventbus.addListener(this::setupClient);
        //eventbus.addListener(this::Join_world);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }



    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("Let us start some pain and suffering");
        ModMessages.register();


    }

    private void setupClient(final FMLClientSetupEvent event)
    {
        //ClientRegistry.registerKeyBinding(keyBind.test_bind);
        // some preinit code
        //ItemBlockRenderTypes.setRenderLayer(ModBlock.HEAL_BLOCK.get(),
                //RenderType.translucent());
        OverlayRegistry.registerOverlayTop("PainPoints",CustomOverlay.POINTS);




    }

}
