package com.everybodycodes2025.solver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day08 implements Puzzle {

  private static final Logger LOGGER = LoggerFactory.getLogger(Day08.class);

  // private static final String FILE_NAME = "day08.txt";

  @Override
  public void solve() {
    var part1Notes = Arrays.stream("1,32,16,32,16,2,18,4,21,6,22,9,26,10,29,15,30,12,29,13,30,16,32,14,30,18,2,18,31,19,3,19,3,19,3,19,3,17,2,21,5,17,1,17,31,18,32,16,28,12,29,13,28,12,32,16,32,16,32,16,30,14,2,18,31,15,29,16,32,16,32,13,1,16,29,13,29,13,29,13,29,16,32,13,29,13,29,13,29,13,29,16,32,16,32,15,32,16,32,15".split(",")).map(Integer::valueOf).toList();
    var nailCount = 32;

    LOGGER.debug("part1: {}", getPart1Count(part1Notes, nailCount));

  }

  private static int getPart1Count(List<Integer> nailSequenceList, int nailCount) {
    int results = 0;
    for (int i = 0; i < nailSequenceList.size() - 1; i++) {
      var curr = nailSequenceList.get(i);
      var next = nailSequenceList.get(i + 1);
      if (Math.abs(curr - next) == nailCount / 2) {
        results++;
      }
    }
    return results;
  }
}
