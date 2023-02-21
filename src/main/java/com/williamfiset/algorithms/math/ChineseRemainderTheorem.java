/**
 * Use the chinese remainder theorem to solve a set of congruence equations.
 *
 * <p>The first method (eliminateCoefficient) is used to reduce an equation of the form cx≡a(mod
 * m)cx≡a(mod m) to the form x≡a_new(mod m_new)x≡anew(mod m_new), which gets rids of the
 * coefficient. A value of null is returned if the coefficient cannot be eliminated.
 *
 * <p>The second method (reduce) is used to reduce a set of equations so that the moduli become
 * pairwise co-prime (which means that we can apply the Chinese Remainder Theorem). The input and
 * output are of the form x≡a_0(mod m_0),...,x≡a_n−1(mod m_n−1)x≡a_0(mod m_0),...,x≡a_n−1(mod
 * m_n−1). Note that the number of equations may change during this process. A value of null is
 * returned if the set of equations cannot be reduced to co-prime moduli.
 *
 * <p>The third method (crt) is the actual Chinese Remainder Theorem. It assumes that all pairs of
 * moduli are co-prime to one another. This solves a set of equations of the form x≡a_0(mod
 * m_0),...,x≡v_n−1(mod m_n−1)x≡a_0(mod m_0),...,x≡v_n−1(mod m_n−1). It's output is of the form
 * x≡a_new(mod m_new)x≡a_new(mod m_new).
 *
 * @author Micah Stairs
 */
package com.williamfiset.algorithms.math;

import java.util.*;

public class ChineseRemainderTheorem {

  // eliminateCoefficient() takes cx≡a(mod m) and gives x≡a_new(mod m_new).
  public static long[] eliminateCoefficient(long c, long a, long m) {

    long d = egcd(c, m)[0];

    if (a % d != 0) return null;

    c /= d;
    a /= d;
    m /= d;

    long inv = egcd(c, m)[1];
    m = Math.abs(m);
    a = (((a * inv) % m) + m) % m;

    return new long[] {a, m};
  }

  // reduce() takes a set of equations and reduces them to an equivalent
  // set with pairwise co-prime moduli (or null if not solvable).
  public static long[][] reduce(long[] a, long[] m, boolean[] branches) {

    List<Long> aNew = new ArrayList<Long>();
    List<Long> mNew = new ArrayList<Long>();

    // Split up each equation into prime factors
    for (int i = 0; i < a.length; i++) {
      List<Long> factors = primeFactorization(m[i]);
      Collections.sort(factors);
      ListIterator<Long> iterator = factors.listIterator();
      while (iterator.hasNext()) {
        long val = iterator.next();
        long total = val;
        while (iterator.hasNext()) {
          long nextVal = iterator.next();
          if (nextVal == val) {
            branches[0] = true;
            total *= val;
          } else {
            branches[1] = true;
            iterator.previous();
            break;
          }
        }
        aNew.add(a[i] % total);
        mNew.add(total);
      }
    }

    // Throw away repeated information and look for conflicts
    for (int i = 0; i < aNew.size(); i++) {
      for (int j = i + 1; j < aNew.size(); j++) {
        if (mNew.get(i) % mNew.get(j) == 0 || mNew.get(j) % mNew.get(i) == 0) {
          branches[2] = true;
          if (mNew.get(i) > mNew.get(j)) {
            branches[3] = true;
            if ((aNew.get(i) % mNew.get(j)) == aNew.get(j)) {
              branches[4] = true;
              aNew.remove(j);
              mNew.remove(j);
              j--;
              continue;
            } else {
              branches[5] = true;
              return null;
            }
          } else {
            branches[6] = true;
            if ((aNew.get(j) % mNew.get(i)) == aNew.get(i)) {
              branches[7] = true;
              aNew.remove(i);
              mNew.remove(i);
              i--;
              break;
            } else{
              branches[8] = true;
              return null;
            }
          }
        }
      }
    }

    // Put result into an array
    long[][] res = new long[2][aNew.size()];
    for (int i = 0; i < aNew.size(); i++) {
      res[0][i] = aNew.get(i);
      res[1][i] = mNew.get(i);
    }

    return res;
  }

  public static long[] crt(long[] a, long[] m) {

    long M = 1;
    for (int i = 0; i < m.length; i++) M *= m[i];

    long[] inv = new long[a.length];
    for (int i = 0; i < inv.length; i++) inv[i] = egcd(M / m[i], m[i])[1];

    long x = 0;
    for (int i = 0; i < m.length; i++) {
      x += (M / m[i]) * a[i] * inv[i]; // Overflow could occur here
      x = ((x % M) + M) % M;
    }

    return new long[] {x, M};
  }

  private static ArrayList<Long> primeFactorization(long n) {
    ArrayList<Long> factors = new ArrayList<Long>();
    if (n <= 0) throw new IllegalArgumentException();
    else if (n == 1) return factors;
    PriorityQueue<Long> divisorQueue = new PriorityQueue<Long>();
    divisorQueue.add(n);
    while (!divisorQueue.isEmpty()) {
      long divisor = divisorQueue.remove();
      if (isPrime(divisor)) {
        factors.add(divisor);
        continue;
      }
      long next_divisor = pollardRho(divisor);
      if (next_divisor == divisor) {
        divisorQueue.add(divisor);
      } else {
        divisorQueue.add(next_divisor);
        divisorQueue.add(divisor / next_divisor);
      }
    }
    return factors;
  }

  private static long pollardRho(long n) {
    if (n % 2 == 0) return 2;
    // Get a number in the range [2, 10^6]
    long x = 2 + (long) (999999 * Math.random());
    long c = 2 + (long) (999999 * Math.random());
    long y = x;
    long d = 1;
    while (d == 1) {
      x = (x * x + c) % n;
      y = (y * y + c) % n;
      y = (y * y + c) % n;
      d = gcf(Math.abs(x - y), n);
      if (d == n) break;
    }
    return d;
  }

  // Extended euclidean algorithm
  private static long[] egcd(long a, long b) {
    if (b == 0) return new long[] {a, 1, 0};
    else {
      long[] ret = egcd(b, a % b);
      long tmp = ret[1] - ret[2] * (a / b);
      ret[1] = ret[2];
      ret[2] = tmp;
      return ret;
    }
  }

  private static long gcf(long a, long b) {
    return b == 0 ? a : gcf(b, a % b);
  }

  private static boolean isPrime(long n) {
    if (n < 2) return false;
    if (n == 2 || n == 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;

    int limit = (int) Math.sqrt(n);

    for (int i = 5; i <= limit; i += 6) if (n % i == 0 || n % (i + 2) == 0) return false;

    return true;
  }

  public static void main(String[] args){
    boolean[] branches = new boolean[9];
    Arrays.fill(branches, Boolean.FALSE);
    long[] a1 = {1, 13, 28};
    long[] m1 = {108, 40, 225};
    reduce(a1, m1, branches);
    for (int i = 0; i < branches.length; i++) {
      if (branches[i]) {
        System.out.printf("Branch %d was taken\n", i);
      } else {
        System.out.printf("Branch %d was not taken\n", i);
      }
    }

    Arrays.fill(branches, Boolean.FALSE);
    long[] a2 = {1, 2, 3};
    long[] m2 = {2, 4, 6};
    reduce(a2, m2, branches);
    for (int i = 0; i < branches.length; i++) {
      if (branches[i]) {
        System.out.printf("Branch %d was taken\n", i);
      } else {
        System.out.printf("Branch %d was not taken\n", i);
      }
    }
  }
}
