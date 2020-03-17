# Random Resizer
大きかったり小さかったり

## 対応バージョン
Minecraft: 1.15.2<br>
Minecraft Forge: 31.0.0以上<br>
http://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.15.2.html

## 概説
Mobのサイズがランダムに表示されます。<br>
あくまで表示上なので、当たり判定は元のままです。<br>
また、ワールドに入りなおすたびにサイズが再抽選されます。

## configファイル
Modを導入した状態で一回でも起動すると、configフォルダ内にrandomresizer-client.tomlが生成されます。<br>
値の変更は次回の起動時に適用されます。
- min_scale
  - Mobのサイズの下限値です。1.0が標準サイズにあたります。<br>
    0.0~1000.0の間で指定でき、デフォルトは0.5です。<br>
    なお、小数点第2位以下は無視されます。
    
- max_scale
  - Mobのサイズの上限値です。1.0が標準サイズにあたります。<br>
    0.0~1000.0の間で指定でき、デフォルトは3.0です。<br>
    なお、小数点第2位以下は無視されます。

## 注意点
- シングルプレイのみ対応です。