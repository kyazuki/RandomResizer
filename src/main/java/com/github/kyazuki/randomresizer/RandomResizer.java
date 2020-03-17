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
        score.setScorePoints(rand.nextInt((int) (RandomResizerConfig.max_scale - RandomResizerConfig.min_scale) * 10 + 1) + (int) RandomResizerConfig.min_scale * 10);
      }
      float scale = scoreboard.getOrCreateScore(id, objective).getScorePoints() / 10.0f;
      event.getMatrixStack().scale(scale, scale, scale);
    }
  }
}