package com.flying_8lack.painmod.overlay;

import com.flying_8lack.painmod.client.ClientPainData;
import com.flying_8lack.painmod.network.ModMessages;
import com.flying_8lack.painmod.network.packet.PointsC2SPacket;
import net.minecraftforge.client.gui.IIngameOverlay;

public class CustomOverlay {





    public static final IIngameOverlay POINTS = ((gui, poseStack, partialTick, width, height) -> {
        ModMessages.sendToServer(new PointsC2SPacket());

        String display = String.format("Pain Points: %d",
                ClientPainData.getPainPoint());
        gui.getFont().draw(poseStack, display, 10, 40, 0xf50707);

    });
}
