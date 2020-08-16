# Random Resizer
大きかったり小さかったり

## 対応バージョン
Minecraft: 1.16.x<br>
Minecraft Forge: 32.0.0以上<br>
http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.16.1.html

## 概説
Mobのサイズがランダムに表示されます。<br>
あくまで表示上なので、当たり判定は元のままです。

## configファイル
Modを導入した状態で一回でも起動すると、configフォルダ内にrandomresizer-common.tomlが生成されます。<br>
値の変更は次回の起動時に適用されます。
各scale値について、40%で下限～標準値が、20%で中間下限～中間上限値が、40%で標準～上限値が抽選されます。<br>
1.0が標準サイズにあたります。
- min_scale
  - Mobのサイズの下限値です。<br>
    0.0~1000.0の間で指定でき、デフォルトは0.5です。<br>

- mid_min_scale
  - Mobのサイズの中間下限値です。<br>
    0.0~1000.0の間で指定でき、デフォルトは0.8です。<br>
    
- mid_max_scale
  - Mobのサイズの中間上限値です。<br>
    1.0~1000.0の間で指定でき、デフォルトは1.5です。<br>
    
- max_scale
  - Mobのサイズの上限値です。<br>
    1.0~1000.0の間で指定でき、デフォルトは3.0です。<br>

## 注意点
- 特になし。