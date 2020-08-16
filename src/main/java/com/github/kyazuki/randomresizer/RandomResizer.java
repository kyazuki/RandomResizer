package com.github.kyazuki.randomresizer;

import com.github.kyazuki.randomresizer.capabilities.IScale;
import com.github.kyazuki.randomresizer.capabilities.Scale;
import com.github.kyazuki.randomresizer.capabilities.ScaleStorage;
import com.github.kyazuki.randomresizer.network.PacketHandler;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(RandomResizer.MODID)
public class RandomResizer {
  public static final String MODID = "randomresizer";
  public static final Logger LOGGER = LogManager.getLogger(MODID);

  public RandomResizer() {
    LOGGER.debug("Random Resizer Loaded!");
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RandomResizerConfig.COMMON_SPEC);
    if (RandomResizerConfig.min_scale > RandomResizerConfig.mid_min_scale) {
      RandomResizerConfig.min_scale = RandomResizerConfig.mid_min_scale;
      LOGGER.error("Config Error. Set smaller \"min_scale\" than \"mid_min_scale\"");
    }
    if (RandomResizerConfig.max_scale < RandomResizerConfig.mid_max_scale) {
      RandomResizerConfig.max_scale = RandomResizerConfig.mid_max_scale;
      LOGGER.error("Config Error. Set larger \"max_scale\" than \"mid_max_scale\"");
    }
    FMLJavaModLoadingContext.get().getModEventBus().addListener(RandomResizer::onFMLCommonSetup);
    PacketHandler.register();
  }

  public static void onFMLCommonSetup(FMLCommonSetupEvent event) {
    CapabilityManager.INSTANCE.register(IScale.class, new ScaleStorage(), Scale::new);
  }
}
