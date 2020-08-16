package com.github.kyazuki.randomresizer.events;

import com.github.kyazuki.randomresizer.capabilities.ScaleProvider;
import com.github.kyazuki.randomresizer.network.CapabilityPacket;
import com.github.kyazuki.randomresizer.network.PacketHandler;
import com.github.kyazuki.randomresizer.RandomResizer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RandomResizer.MODID)
public class CommonEventHandler {
  public static final ResourceLocation SCALE_CAP_RESOURCE = new ResourceLocation(RandomResizer.MODID, "capabilities");

  // Server

  @SubscribeEvent
  public static void onStartTracking(PlayerEvent.StartTracking event) {
    if (!(event.getTarget() instanceof LivingEntity) || event.getTarget() instanceof PlayerEntity) return;

    PacketHandler.sendTo(new CapabilityPacket(event.getTarget().getEntityId(), event.getTarget().getCapability(ScaleProvider.SCALE_CAP).orElseThrow(IllegalArgumentException::new).getScale()), event.getPlayer());
  }

  // Server & Client

  @SubscribeEvent
  public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
    if (!(event.getObject() instanceof LivingEntity) || event.getObject() instanceof PlayerEntity) return;

    event.addCapability(SCALE_CAP_RESOURCE, new ScaleProvider());
  }
}
