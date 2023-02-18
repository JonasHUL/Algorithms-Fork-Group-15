package com.williamfiset.algorithms.dp;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.primitives.Ints;
import com.williamfiset.algorithms.utils.TestUtils;
import java.util.*;
import org.junit.*;

public class LongestCommonSubstringTest {

  /**
	 * Tests lcs on longest common substring between "1AL42XZF" and "CD8A4PQXMF",
   * which should return "A4XF"
	 * 
	 * @return Needs to return "A4XF"
	 */
  @Test
  public void lcsTest() {
    char[] firstString = "1AL42XZF".toCharArray();
    char[] secondString = "CD8A4PQXMF".toCharArray();

    String lcs = LongestCommonSubstring.lcs(firstString, secondString);

    assertThat(lcs).isEqualTo("A4XF");
  }
}
