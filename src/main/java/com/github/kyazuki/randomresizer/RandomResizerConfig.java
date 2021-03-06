package com.github.kyazuki.randomresizer;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = RandomResizer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RandomResizerConfig {
  public static final CommonConfig COMMON;
  public static final ForgeConfigSpec COMMON_SPEC;

  static {
    final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
    COMMON_SPEC = specPair.getRight();
    COMMON = specPair.getLeft();
  }

  public static double min_scale;
  public static double mid_min_scale;
  public static double mid_max_scale;
  public static double max_scale;

  @SubscribeEvent
  public static void onModConfigEvent(final ModConfig.ModConfigEvent configEvent) {
    if (configEvent.getConfig().getSpec() == RandomResizerConfig.COMMON_SPEC) {
      bakeConfig();
    }
  }

  public static void bakeConfig() {
    min_scale = COMMON.min_scale.get();
    mid_min_scale = COMMON.mid_min_scale.get();
    mid_max_scale = COMMON.mid_max_scale.get();
    max_scale = COMMON.max_scale.get();
  }

  public static class CommonConfig {

    public final ForgeConfigSpec.DoubleValue min_scale;
    public final ForgeConfigSpec.DoubleValue mid_min_scale;
    public final ForgeConfigSpec.DoubleValue mid_max_scale;
    public final ForgeConfigSpec.DoubleValue max_scale;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
      builder.push("RandomResizer Config");
      min_scale = builder
              .comment("Minimum scale value.")
              .translation(RandomResizer.MODID + ".config." + "min_scale")
              .defineInRange("min_scale", 0.5, 0.0, 1000.0);
      mid_min_scale = builder
              .comment("Mid-Minimum scale value.")
              .translation(RandomResizer.MODID + ".config." + "mid_min_scale")
              .defineInRange("mid_min_scale", 0.8, 0.0, 1000.0);
      mid_max_scale = builder
              .comment("Mid-Maximum scale value.")
              .translation(RandomResizer.MODID + ".config." + "mid_max_scale")
              .defineInRange("mid_max_scale", 1.5, 1.0, 1000.0);
      max_scale = builder
              .comment("Maximum scale value.")
              .translation(RandomResizer.MODID + ".config." + "max_scale")
              .defineInRange("max_scale", 3.0, 1.0, 1000.0);
      builder.pop();
    }
  }
}