/**
 * This file contains an implementation of finding the Longest Common Substring (LCS) between two
 * strings using dynamic programming.
 *
 * <p>Time Complexity: O(nm)
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 */
package com.williamfiset.algorithms.dp;

import java.util.Arrays;

public class LongestCommonSubstring {

  // Returns a non unique Longest Common Substring
  // between the strings str1 and str2 in O(nm)
  public static String lcs(char[] A, char[] B, boolean[] branches) {

    branches[0] = true;
    if (A == null || B == null) {
      branches[1] = true;
      return null;
    }

    final int n = A.length;
    final int m = B.length;

    if (n == 0 || m == 0) {
      branches[2] = true;
      return null;
    }

    int[][] dp = new int[n + 1][m + 1];

    // Suppose A = a1a2..an-1an and B = b1b2..bn-1bn
    for (int i = 1; i <= n; i++) {
      branches[3] = true;
      for (int j = 1; j <= m; j++) {
        branches[4] = true;

        // If ends match the LCS(a1a2..an-1an, b1b2..bn-1bn) = LCS(a1a2..an-1, b1b2..bn-1) + 1
        if (A[i - 1] == B[j - 1]) {
          branches[5] = true;
          dp[i][j] = dp[i - 1][j - 1] + 1;
        }

        // If the ends do not match the LCS of a1a2..an-1an and b1b2..bn-1bn is
        // max( LCS(a1a2..an-1, b1b2..bn-1bn), LCS(a1a2..an-1an, b1b2..bn-1) )
        else {
          branches[6] = true;
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    int lcsLen = dp[n][m];
    char[] lcs = new char[lcsLen];
    int index = 0;

    // Backtrack to find a LCS. We search for the cells
    // where we included an element which are those with
    // dp[i][j] != dp[i-1][j] and dp[i][j] != dp[i][j-1])
    int i = n, j = m;
    while (i >= 1 && j >= 1) {
      branches[7] = true;

      int v = dp[i][j];

      // The order of these may output different LCSs
      while (i > 1 && dp[i - 1][j] == v) {
        branches[8] = true;
        i--;
      }
      while (j > 1 && dp[i][j - 1] == v) {
        branches[9] = true;
        j--;
      }

      // Make sure there is a match before adding
      if (v > 0) {
        branches[10] = true;
        lcs[lcsLen - index++ - 1] = A[i - 1]; // or B[j-1];
      }

      i--;
      j--;
    }

    return new String(lcs, 0, lcsLen);
  }

  public static void main(String[] args) {
    boolean[] branches = new boolean[12];
    Arrays.fill(branches, Boolean.FALSE);

    char[] A = {'A', 'X', 'B', 'C', 'Y'};
    char[] B = {'Z', 'A', 'Y', 'W', 'B', 'C'};
    System.out.println(lcs(A, B, branches)); // ABC

    Arrays.fill(branches, Boolean.FALSE);
    A = new char[] {'3', '9', '8', '3', '9', '7', '9', '7', '0'};
    B = new char[] {'3', '3', '9', '9', '9', '1', '7', '2', '0', '6'};
    System.out.println(lcs(A, B, branches)); // 339970

    for (int i = 0; i < branches.length; i++) {
      if (branches[i]) {
        System.out.printf("Branch %d was taken\n", i);
      } else {
        System.out.printf("Branch %d was not taken\n", i);
      }
    } 
  }
}
