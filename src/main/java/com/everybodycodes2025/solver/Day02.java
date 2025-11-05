package com.everybodycodes2025.solver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.everybodycodes2025.record.NodeLong;

public class Day02 implements Puzzle {

  private static final Logger LOGGER = LoggerFactory.getLogger(Day02.class);

  // private static final String FILE_NAME = "day02.txt";

  @Override
  public void solve() {
    var start = new NodeLong(156, 52);

    var result = new NodeLong(0, 0);
    for (int i = 0; i < 3; i++) {
      result = multiply(result, result);
      result = divide(result, new NodeLong(10, 10));
      result = add(result, start);
    }
    LOGGER.debug("part1: {}", result);

    start = new NodeLong(-4521, 67892);
    int count = 0;
    // var end = add(start, new NodeLong(1000, 1000));
    for (int i = 0; i < 101; i++) {
      for (int j = 0; j < 101; j++) {
        var coordinate = add(start, new NodeLong(i * 10, j * 10));
        result = new NodeLong(0, 0);
        for (int k = 0; k < 100; k++) {
          result = multiply(result, result);
          result = divide(result, new NodeLong(100000, 100000));
          result = add(result, coordinate);
        }
        if (result.x() < 1000000 && result.y() < 1000000 && result.x() > -1000000
            && result.y() > -1000000) {
          count++;
        }
      }
    }
    LOGGER.debug("part2: {}", count);

    start = new NodeLong(-4521, 67892);
    count = 0;
    for (int i = 0; i < 1001; i++) {
      for (int j = 0; j < 1001; j++) {
        var coordinate = add(start, new NodeLong(i, j));
        result = new NodeLong(0, 0);
        for (int k = 0; k < 100; k++) {
          result = multiply(result, result);
          result = divide(result, new NodeLong(100000, 100000));
          result = add(result, coordinate);
        }
        if (result.x() < 1000000 && result.y() < 1000000 && result.x() > -1000000
            && result.y() > -1000000) {
          count++;
        }
      }
    }
    LOGGER.debug("part3: {}", count);
  }

  private NodeLong add(NodeLong a, NodeLong b) {
    return new NodeLong(a.x() + b.x(), a.y() + b.y());
  }

  private NodeLong multiply(NodeLong a, NodeLong b) {
    return new NodeLong(a.x() * b.x() - a.y() * b.y(), a.x() * b.y() + a.y() * b.x());
  }

  private NodeLong divide(NodeLong a, NodeLong b) {
    return new NodeLong(a.x() / b.x(), a.y() / b.y());
  }
}
