package com.github.kyazuki.randomresizer.events;

import com.github.kyazuki.randomresizer.RandomResizer;
import com.github.kyazuki.randomresizer.capabilities.ScaleProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RandomResizer.MODID, value = Dist.CLIENT)
public class ClientEventHandler {
  @SubscribeEvent
  public static void onRenderMobs(RenderLivingEvent event) {
    if (!(event.getEntity() instanceof PlayerEntity)) {
      float scale = event.getEntity().getCapability(ScaleProvider.SCALE_CAP).orElseThrow(IllegalArgumentException::new).getScale();
      event.getMatrixStack().scale(scale, scale, scale);
    }
  }
}
