    package com.williamfiset.algorithms.math;
    import org.junit.Before;
    import org.junit.Test;
    import static com.google.common.truth.Truth.assertThat;
    import java.util.*;
    public class ChineseRemainderTheoremTest {
        private ChineseRemainderTheorem test;
        private boolean[] branches = new boolean[9];
        @Before
        public void setup() {
            test = new ChineseRemainderTheorem();
        }

        // input has already co-prime
        // return the original formulas
        @Test
        public void testNoChange(){
            long[] a = {2,3,1};
            long[] m = {3,4,5};
            long[][] result = {{2,3,1},{3,4,5}};
            assertThat(test.reduce(a,m,branches)).isEqualTo(result);
            System.out.println("Result for testNoChange:");
            printBranches(branches);
        }

        // input can be co-prime
        // return the reduced formula
        @Test
        public void testCanReduce(){
            long[] a = {1, 13, 28};
            long[] m = {108, 40, 225};
            long[][] result = {{1, 5, 3},{27, 8, 25}};
            assertThat(test.reduce(a,m,branches)).isEqualTo(result);
            System.out.println("Result for testCanReduce:");
            printBranches(branches);
        }

        // input cannot be co-prime
        // return null
        @Test
        public void testCannotReduce(){
            long[] a = {1, 2, 3};
            long[] m = {2, 4, 6};
            assertThat(test.reduce(a,m,branches)).isNull();
            System.out.println("Result for testCannotReduce:");
            printBranches(branches);
        }

        // input are the same
        // reduce to one formula
        @Test
        public void testSameFormula(){
            long[] a = {2, 2};
            long[] m = {3, 3};
            long[][] answer = {{2},{3}};
            assertThat(test.reduce(a,m,branches)).isEqualTo(answer);
            System.out.println("Result for testSameFormula:");
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
