package com.flying_8lack.painmod.network;

import com.flying_8lack.painmod.BoonMod;
import com.flying_8lack.painmod.network.packet.PointsC2SPacket;
import com.flying_8lack.painmod.network.packet.PointsS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel instance;
    private static int PacketID = 0;
    private static int id(){
        return PacketID++;
    }

    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(BoonMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        instance = net;

        net.messageBuilder(PointsC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PointsC2SPacket::new)
                .encoder(PointsC2SPacket::toByte)
                .consumer(PointsC2SPacket::handle)
                .add();

        net.messageBuilder(PointsS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PointsS2CPacket::new)
                .encoder(PointsS2CPacket::toByte)
                .consumer(PointsS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        instance.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        instance.send(PacketDistributor.PLAYER.with(() -> player),
                message);
    }
}
