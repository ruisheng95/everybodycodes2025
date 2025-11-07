package com.everybodycodes2025.solver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.everybodycodes2025.record.Node;

public class Day04 implements Puzzle {

  private static final Logger LOGGER = LoggerFactory.getLogger(Day04.class);

  // private static final String FILE_NAME = "day04.txt";

  @Override
  public void solve() {
    var gears = Arrays.stream("""
        1000
        972
        946
        940
        938
        913
        889
        861
        848
        833
        813
        803
        787
        781
        764
        762
        761
        748
        726
        706
        681
        661
        641
        620
        594
        568
        539
        522
        503
        496
        469
        447
        423
        405
        404
        390
        382
        356
        332
        313
        284
        279
        259
        250
        248
        234
        230
        229
        218
        196
                            """.split("\n")).map(Integer::parseInt).toList();
    BigDecimal gearRatio = BigDecimal.ONE;
    for (int i = 0; i < gears.size() - 1; i++) {
      var currGear = gears.get(i);
      var nextGear = gears.get(i + 1);
      gearRatio =
          gearRatio.multiply(BigDecimal.valueOf(currGear).divide(BigDecimal.valueOf(nextGear), 8,
              RoundingMode.HALF_UP)).setScale(8, RoundingMode.HALF_UP);
    }
    LOGGER.debug("part1: {}", gearRatio.multiply(new BigDecimal("2025")));

    long turnToTest = 10000000000000L;
    gears = Arrays.stream("""
        902
        890
        880
        861
        845
        838
        819
        814
        810
        801
        774
        745
        722
        720
        709
        689
        676
        655
        651
        638
        624
        606
        589
        571
        551
        533
        529
        519
        490
        481
        474
        472
        455
        453
        449
        427
        409
        399
        393
        382
        371
        370
        360
        357
        356
        336
        319
        312
        311
        277
                """.split("\n")).map(Integer::parseInt).toList();
    gearRatio = BigDecimal.ONE;
    for (int i = 0; i < gears.size() - 1; i++) {
      var currGear = gears.get(i);
      var nextGear = gears.get(i + 1);
      // Very specific scale of 13 to pass Part 2
      gearRatio = gearRatio.multiply(BigDecimal.valueOf(currGear)
          .divide(BigDecimal.valueOf(nextGear), 13, RoundingMode.HALF_UP));
    }
    // Part 2 answer round to decimal half up
    LOGGER.debug("part2: {}",
        BigDecimal.valueOf(turnToTest).divide(gearRatio, 4, RoundingMode.HALF_UP));

    var nodeGears = Arrays.stream("""
        617
        603|603
        594|2376
        575|575
        565|2260
        549|549
        544|544
        525|525
        524|524
        509|509
        497|994
        492|492
        477|954
        469|469
        464|464
        458|458
        450|900
        434|434
        433|866
        422|422
        414|1242
        413|413
        400|1600
        398|398
        389|1167
        373|373
        356|1424
        349|349
        344|1376
        336|336
        329|987
        319|319
        302|604
        296|296
        283|849
        264|264
        249|747
        236|236
        234|702
        217|217
        210|840
        209|209
        205|205
        193|193
        182|546
        179|179
        170|510
        162|162
        146|146
        97
                """.split("\n")).map(s -> {
      if (s.contains("|")) {
        var gearArr = s.split("\\|");
        // x for left gear, y for right gear
        return new Node(Integer.parseInt(gearArr[0]), Integer.parseInt(gearArr[1]));
      }
      return new Node(Integer.parseInt(s), 0);
    }).toList();
    gearRatio = BigDecimal.ONE;
    int scale = 16;
    for (int i = 0; i < nodeGears.size() - 1; i++) {
      var currGear = nodeGears.get(i);
      var nextGear = nodeGears.get(i + 1);
      if (currGear.y() == 0) {
        gearRatio = gearRatio.multiply(BigDecimal.valueOf(currGear.x())
            .divide(BigDecimal.valueOf(nextGear.x()), scale, RoundingMode.HALF_UP))
            .setScale(scale, RoundingMode.HALF_UP);
      } else {
        gearRatio = gearRatio.multiply(BigDecimal.valueOf(currGear.y())
            .divide(BigDecimal.valueOf(nextGear.x()), scale, RoundingMode.HALF_UP))
            .setScale(scale, RoundingMode.HALF_UP);
      }
    }

    // Part 3 answer is rounding by truncating???????????
    LOGGER.debug("part3: {}",
        gearRatio.multiply(new BigDecimal("100")).setScale(4, RoundingMode.HALF_UP));
  }
}
