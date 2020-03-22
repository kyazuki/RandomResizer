package com.github.kyazuki.randomresizer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(RandomResizer.MODID)
@Mod.EventBusSubscriber
public class RandomResizer {
  public static final String MODID = "randomresizer";
  public static final Logger LOGGER = LogManager.getLogger(MODID);

  public static final Random rand = new Random();
  public static final String SCORE_NAME = "RenderScale";

  public RandomResizer() {
    LOGGER.debug("Random Resizer Loaded!");
    ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, RandomResizerConfig.CLIENT_SPEC);
    if (RandomResizerConfig.min_scale > RandomResizerConfig.mid_min_scale) {
      RandomResizerConfig.min_scale = RandomResizerConfig.mid_min_scale;
      LOGGER.error("Config Error. Set smaller \"min_scale\" than \"mid_min_scale\"");
    }
    if (RandomResizerConfig.max_scale < RandomResizerConfig.mid_max_scale) {
      RandomResizerConfig.max_scale = RandomResizerConfig.mid_max_scale;
      LOGGER.error("Config Error. Set larger \"max_scale\" than \"mid_max_scale\"");
    }
  }

  @SubscribeEvent
  public static void onRenderMobs(RenderLivingEvent event) {
    if (!(event.getEntity() instanceof PlayerEntity)) {
      Scoreboard scoreboard = event.getEntity().getEntityWorld().getScoreboard();
      if (scoreboard.getObjective(SCORE_NAME) == null) {
        scoreboard.addObjective(SCORE_NAME, ScoreCriteria.DUMMY, new StringTextComponent(SCORE_NAME), ScoreCriteria.DUMMY.getRenderType());
      }
      String id = event.getEntity().getCachedUniqueIdString();
      ScoreObjective objective = scoreboard.getObjective(SCORE_NAME);
      if (!scoreboard.entityHasObjective(id, objective)) {
        Score score = scoreboard.getOrCreateScore(id, objective);
        int p = rand.nextInt(100);
        int scaleScore;
        if (p < 40) {
          scaleScore = rand.nextInt((int) ((1.0 - RandomResizerConfig.min_scale) * 10 + 1)) + (int) (RandomResizerConfig.min_scale * 10);
          LOGGER.debug(event.getEntity() + " min-scale: " + scaleScore / 10.0f);
        } else if (p < 60) {
          scaleScore = rand.nextInt((int) ((RandomResizerConfig.mid_max_scale - RandomResizerConfig.mid_min_scale) * 10 + 1)) + (int) (RandomResizerConfig.mid_min_scale * 10);
          LOGGER.debug(event.getEntity() + " mid-scale: " + scaleScore / 10.0f);
        } else {
          scaleScore = rand.nextInt((int) ((RandomResizerConfig.max_scale - 1.0) * 10 + 1)) + 10;
          LOGGER.debug(event.getEntity() + " max-scale: " + scaleScore / 10.0f);
        }
        score.setScorePoints(scaleScore);
      }
      float scale = scoreboard.getOrCreateScore(id, objective).getScorePoints() / 10.0f;
      event.getMatrixStack().scale(scale, scale, scale);
    }
  }
}