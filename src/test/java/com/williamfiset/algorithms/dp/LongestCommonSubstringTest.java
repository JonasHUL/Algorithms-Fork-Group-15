package com.williamfiset.algorithms.dp;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.primitives.Ints;
import com.williamfiset.algorithms.utils.TestUtils;
import java.util.*;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


public class LongestCommonSubstringTest {

  boolean[] branches = new boolean[12];

  /**
	 * Tests lcs on longest common substring between "1AL42XZF" and "CD8A4PQXMF",
   * which should return "A4XF"
	 * 
	 * @return Needs to return "A4XF"
	 */
  @Test
  public void lcsTest1() {
    branches[0] = true;
    System.out.println(branches[0]);
    char[] firstString = "1AL42XZF".toCharArray();
    char[] secondString = "CD8A4PQXMF".toCharArray();

    String lcs = LongestCommonSubstring.lcs(firstString, secondString);

    assertThat(lcs).isEqualTo("A4XF");
    printBranches(branches);
  }

  

  /**
	 * Tests lcs on one string being null, which should return null
	 * 
	 * @return Needs to return null
	 */
  @Test
  public void lcsTest2() {
    System.out.println(branches[0]);

    char[] firstString = null;
    char[] secondString = "CD8A4PQXMF".toCharArray();

    String lcs = LongestCommonSubstring.lcs(firstString, secondString);

    assertThat(lcs).isEqualTo(null);
    printBranches(branches);
  }

  /**
	 * Tests lcs on one string being of 0 length, which should return null
	 * 
	 * @return Needs to return null
	 */
  @Test
  public void lcsTest3() {
    char[] firstString = "".toCharArray();
    char[] secondString = "CD8A4PQXMF".toCharArray();

    String lcs = LongestCommonSubstring.lcs(firstString, secondString);

    assertThat(lcs).isEqualTo(null);
    printBranches(branches);
  }

  /**
	 * Tests lcs on two strings that are exactly the same, which should
   * return the same string
	 * 
	 * @return Needs to return the input
	 */
  @Test
  public void lcsTest4() {
    char[] firstString = "AKJBKJA21KFA".toCharArray();
    char[] secondString = "AKJBKJA21KFA".toCharArray();

    String lcs = LongestCommonSubstring.lcs(firstString, secondString);

    assertThat(lcs).isEqualTo("AKJBKJA21KFA");
    printBranches(branches);
  }
  
  public void printBranches(boolean[] branches) {
    for (int i = 0; i < branches.length; i++) {
      if (branches[i]) {
        System.out.printf("Branch %d was taken\n", i);
      } else {
        System.out.printf("Branch %d was not taken\n", i);
      }
    } 
  }
}
