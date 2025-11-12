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

public class Day07 implements Puzzle {

  private static final Logger LOGGER = LoggerFactory.getLogger(Day07.class);

  // private static final String FILE_NAME = "day07.txt";

  @Override
  public void solve() {
    var part1Notes = Arrays.asList("""
        Kazloris,Quenselor,Quenjorath,Kazselor,Urselor,Quenloris,Urjorath,Kazjorath,Urloris

        u > b
        r > j,a,i,b
        e > n,l
        U > r
        z > l,j,s
        K > a
        j > o
        n > l,j,s
        a > t,b
        i > s
        t > h
        s > e
        o > r
        l > o
        Q > u
                """.split("\n"));
    var nameList = Arrays.asList(part1Notes.getFirst().split(","));
    var ruleMap = part1Notes.subList(2, part1Notes.size()).stream()
        .collect(Collectors.toMap(s -> StringUtils.substringBefore(s, ">").trim(),
            s -> Arrays.asList(StringUtils.substringAfter(s, ">").trim().split(","))));
    LOGGER.debug("part1: {}", getValidNameFromRulesPart1(nameList, ruleMap));

    var part2Notes = Arrays.asList(
        """
            Feraris,Paltharis,Pyrurath,Havkryth,Qyraendris,Harkael,Ilmareon,Rynaris,Feroth,Palthkryth,Jaroth,Rynaelor,Rynketh,Havaris,Ilmaroth,Havoth,Ilmararis,Qyraurath,Pyrkael,Palthkael,Havoryx,Havketh,Haraelor,Rynurath,Harketh,Rynendris,Zalketh,Pyrendris,Ferendris,Zalkael,Qyraoth,Palthketh,Qyraaelor,Zaleon,Ilmarurath,Qyrakael,Ferkryth,Feraelor,Jareon,Jarkael,Ilmaroryx,Haroth,Ilmaraelor,Jarendris,Rynkryth,Qyraaris,Ferkael,Palthaelor,Havendris,Pyrketh,Jarketh,Harurath,Zaloth,Pyroth,Zalurath,Hararis,Qyraketh,Harendris,Harkryth,Zaloryx,Haroryx,Palthoryx,Palthurath,Ryneon,Pyroryx,Pyrkryth,Ilmarendris,Ilmarketh,Ilmarkael,Palthendris,Rynoth,Palthoth,Havkael,Qyraeon,Zalendris,Rynoryx,Jaraelor,Jarkryth,Zalaris,Qyraoryx,Fereon,Rynkael,Haveon,Ferketh,Hareon,Pyreon,Ilmarkryth,Pyraris,Zalkryth,Havurath,Qyrakryth,Havaelor,Jararis,Ferurath,Zalaelor,Paltheon,Feroryx,Jarurath,Jaroryx,Pyraelor

            e > o,l,n,t,v
            l > m,o,t,a,k,e,u,v
            k > r,a,e
            I > l
            n > a,k,o,e,d,u
            y > v,t,x
            m > a
            o > n,r,t
            Z > a
            d > r
            h > a,k,o,e,u
            u > r
            r > e,i,y,a,k,o,u
            J > a
            i > s
            Q > y
            v > a,k,o,e,u
            P > y,a
            R > y
            a > r,e,t,v,a,k,o,u
            F > e
            H > a
            t > h
                    """
            .split("\n"));
    nameList = Arrays.asList(part2Notes.getFirst().split(","));
    ruleMap = part2Notes.subList(2, part2Notes.size()).stream()
        .collect(Collectors.toMap(s -> StringUtils.substringBefore(s, ">").trim(),
            s -> Arrays.asList(StringUtils.substringAfter(s, ">").trim().split(","))));

    LOGGER.debug("part2: {}", getValidNameFromRulesPart2(nameList, ruleMap));

    var part3Notes = Arrays.asList(
        """
            Ny,Nyl,Nyth,Nyss,Nyrix,Sar,Vorn,Palth,Aer,Draz,Briv,Khar,Zar,Mav,Luth,Xar,Gorath,Thar,Thaz,Lar

            r > n,i,v,e,l,t,o,q,a
            T > h
            e > l,o,v
            a > r,v,z,t
            L > a,u
            z > v,n,e,l,t,o,q
            N > y
            K > h
            t > h
            n > o,i,v,n,e,l,t,q
            Z > a
            X > a
            o > r,n,v
            G > o
            A > e
            D > r
            s > s,v,n,e,l,t,o,q
            P > a
            B > r
            v > e,v,n,l,t,o,q
            x > v,n,e,l,t,o,q
            l > i,v,n,e,l,t,o,q
            S > a
            q > u
            i > s,t,x,n,v
            M > a
            y > v,r
            h > e,y,v,n,l,t,o,q
            V > o
            u > o,i,v
                    """
            .split("\n"));
    nameList = Arrays.asList(part3Notes.getFirst().split(","));
    ruleMap = part3Notes.subList(2, part3Notes.size()).stream()
        .collect(Collectors.toMap(s -> StringUtils.substringBefore(s, ">").trim(),
            s -> Arrays.asList(StringUtils.substringAfter(s, ">").trim().split(","))));

    LOGGER.debug("part3: {}", getValidNameFromRulesPart3(nameList, ruleMap));
  }

  private static String getValidNameFromRulesPart1(List<String> nameList,
      Map<String, List<String>> ruleMap) {
    for (var name : nameList) {
      boolean isPassed = true;
      for (int i = 0; i < name.length() - 1; i++) {
        var c1 = name.charAt(i);
        var c2 = name.charAt(i + 1);
        var rules = ruleMap.get(String.valueOf(c1));
        if (rules == null || !rules.contains(String.valueOf(c2))) {
          // Stop checking name if failed
          isPassed = false;
          break;
        }
      }
      if (isPassed) {
        return name;
      }
    }
    return "";
  }

  private static int getValidNameFromRulesPart2(List<String> nameList,
      Map<String, List<String>> ruleMap) {
    int results = 0;
    for (int i = 0; i < nameList.size(); i++) {
      var name = nameList.get(i);
      boolean isPassed = true;
      for (int j = 0; j < name.length() - 1; j++) {
        var c1 = name.charAt(j);
        var c2 = name.charAt(j + 1);
        var rules = ruleMap.get(String.valueOf(c1));
        if (rules == null || !rules.contains(String.valueOf(c2))) {
          // Stop checking name if failed
          isPassed = false;
          break;
        }
      }
      if (isPassed) {
        results += (i + 1);
      }
    }
    return results;
  }

  private static int getValidNameFromRulesPart3(List<String> nameList,
      Map<String, List<String>> ruleMap) {
    int results = 0;
    Set<String> uniqueNames = new HashSet<>();
    for (var name : nameList) {
      boolean isPassed = true;
      for (int i = 0; i < name.length() - 1; i++) {
        var c1 = name.charAt(i);
        var c2 = name.charAt(i + 1);
        var rules = ruleMap.get(String.valueOf(c1));
        if (rules == null || !rules.contains(String.valueOf(c2))) {
          // Stop checking name if failed
          isPassed = false;
          break;
        }
      }
      if (isPassed) {
        var ruleList = ruleMap.get(StringUtils.right(name, 1));
        for (var rule : ruleList) {
          results += recursivePart3(name + rule, ruleMap, uniqueNames);
        }
      }
    }
    return results;
  }

  private static int recursivePart3(String name, Map<String, List<String>> ruleMap,
      Set<String> uniqueNames) {
    if (name.length() > 11) {
      // Not valid name exceed 11
      return 0;
    }
    var ruleList = ruleMap.get(StringUtils.right(name, 1));
    if (ruleList == null) {
      if (name.length() < 7 || uniqueNames.contains(name)) {
        return 0;
      }
      // Still valid name if no rules found
      uniqueNames.add(name);
      return 1;
    }
    int result = 0;
    if (name.length() >= 7 && !uniqueNames.contains(name)) {
      result = 1; // Valid name
      uniqueNames.add(name);
      // LOGGER.debug("test {}", name);
    }
    for (var rule : ruleList) {
      result += recursivePart3(name + rule, ruleMap, uniqueNames);
    }
    return result;
  }
}
