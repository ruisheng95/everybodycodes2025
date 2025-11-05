package com.everybodycodes2025.solver;

import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.everybodycodes2025.utils.CommonUtils;

public class Day01 implements Puzzle {

  private static final Logger LOGGER = LoggerFactory.getLogger(Day01.class);

  private static final String FILE_NAME = "day01.txt";

  @Override
  public void solve() {
    List<String> strList = CommonUtils.getFileStrList(FILE_NAME);
    String[] nameList = strList.getFirst().split(",");
    String[] moveList = strList.getLast().split(",");
    int pos = 0;
    for (var move : moveList) {
      if (move.startsWith("R")) {
        pos += NumberUtils.toInt(move.substring(1));
      } else if (move.startsWith("L")) {
        pos -= NumberUtils.toInt(move.substring(1));
      }
      pos = Math.max(pos, 0);
      pos = Math.min(pos, nameList.length - 1);
      // LOGGER.debug("part1 {}", nameList[pos]);
    }
    LOGGER.debug("part1 {}", nameList[pos]);

    pos = 0;
    // LOGGER.debug("part2 {}", nameList[pos]);
    for (var move : moveList) {
      if (move.startsWith("R")) {
        pos += NumberUtils.toInt(move.substring(1));
      } else if (move.startsWith("L")) {
        pos -= NumberUtils.toInt(move.substring(1));
      }
      pos = pos % (nameList.length);
      if (pos < 0) {
        pos += nameList.length;
      }
      // LOGGER.debug("part2 {}", nameList[pos]);
    }
    LOGGER.debug("part2 {}", nameList[pos]);

    for (var move : moveList) {
      pos = 0;
      if (move.startsWith("R")) {
        pos += NumberUtils.toInt(move.substring(1));
      } else if (move.startsWith("L")) {
        pos -= NumberUtils.toInt(move.substring(1));
      }
      pos = pos % (nameList.length);
      if (pos < 0) {
        pos += nameList.length;
      }
      swap(nameList, pos);
      // LOGGER.debug("part3 {}", nameList[0]);
    }
    LOGGER.debug("part3 {}", nameList[0]);
  }

  private static void swap(String[] nameList, int pos) {
    // Always swap with pos 0
    var temp = nameList[0];
    nameList[0] = nameList[pos];
    nameList[pos] = temp;
  }
}
