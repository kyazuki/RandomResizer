package com.github.kyazuki.randomresizer.capabilities;

import com.github.kyazuki.randomresizer.RandomResizerConfig;

import java.util.Random;

public class Scale implements IScale {
  private static Random rand = new Random();
  private float scale;

  public Scale() {
    int p = rand.nextInt(100);
    double scale;
    if (p < 40) {
      scale = RandomResizerConfig.min_scale + rand.nextDouble() * (1.0d - RandomResizerConfig.min_scale);
    } else if (p < 60) {
      scale = RandomResizerConfig.mid_min_scale + rand.nextDouble() * (RandomResizerConfig.mid_max_scale - RandomResizerConfig.mid_min_scale);
    } else {
      scale = 1.0d + rand.nextDouble() * (RandomResizerConfig.max_scale - 1.0d);
    }
    this.scale = (float) scale;
  }

  @Override
  public void setScale(float scale) {
    this.scale = scale;
  }

  @Override
  public float getScale() {
    return scale;
  }
}