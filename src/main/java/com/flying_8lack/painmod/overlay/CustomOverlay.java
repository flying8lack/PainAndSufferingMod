package com.flying_8lack.painmod.overlay;

import com.flying_8lack.painmod.client.ClientPainData;
import com.flying_8lack.painmod.network.ModMessages;
import com.flying_8lack.painmod.network.packet.PointsC2SPacket;
import net.minecraftforge.client.gui.IIngameOverlay;

public class CustomOverlay {





    public static final IIngameOverlay POINTS = ((gui, poseStack, partialTick, width, height) -> {
        ModMessages.sendToServer(new PointsC2SPacket());

        String display = String.format("Pain Credit: %d",
                ClientPainData.getPainPoint());
        gui.getFont().draw(poseStack, display, (float) (width/2), (float) (height*0.75), 0xf50707);

    });
}
