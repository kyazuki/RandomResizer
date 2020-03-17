package com.github.kyazuki.randomresizer;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = RandomResizer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RandomResizerConfig {
  public static final ClientConfig CLIENT;
  public static final ForgeConfigSpec CLIENT_SPEC;

  static {
    final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
    CLIENT_SPEC = specPair.getRight();
    CLIENT = specPair.getLeft();
  }

  public static double min_scale;
  public static double max_scale;

  @SubscribeEvent
  public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {
    if (configEvent.getConfig().getSpec() == RandomResizerConfig.CLIENT_SPEC) {
      bakeConfig();
    }
  }

  public static void bakeConfig() {
    min_scale = CLIENT.min_scale.get();
    max_scale = CLIENT.max_scale.get();
  }

  public static class ClientConfig {

    public final ForgeConfigSpec.DoubleValue min_scale;
    public final ForgeConfigSpec.DoubleValue max_scale;

    public ClientConfig(ForgeConfigSpec.Builder builder) {
      builder.push("RandomResizer Config");
      min_scale = builder
              .comment("Minimum scale value.")
              .translation(RandomResizer.MODID + ".config." + "min_scale")
              .defineInRange("min_scale", 0.5, 0.0, 1000.0);
      max_scale = builder
              .comment("Maximum scale value.")
              .translation(RandomResizer.MODID + ".config." + "max_scale")
              .defineInRange("max_scale", 3.0, 0.0, 1000.0);
      builder.pop();
    }
  }
}