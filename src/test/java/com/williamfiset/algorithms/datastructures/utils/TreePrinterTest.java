package com.williamfiset.algorithms.datastructures.utils;

import org.junit.Test;

import java.util.Arrays;

import static com.google.common.truth.Truth.assertThat;
import static com.williamfiset.algorithms.datastructures.utils.TreePrinter.getTreeDisplay;

public class TreePrinterTest {
    boolean[] branches = new boolean[9];
    TreePrinter.PrintableNode nodeSecondLevelLeft = new TreePrinter.PrintableNode() {
        @Override
        public TreePrinter.PrintableNode getLeft() {
            return null;
        }

        @Override
        public TreePrinter.PrintableNode getRight() {
            return null;
        }

        @Override
        public String getText() {
            return "Node Second Level Left";
        }
    };
    TreePrinter.PrintableNode nodeSecondLevelRight = new TreePrinter.PrintableNode() {
        @Override
        public TreePrinter.PrintableNode getLeft() {
            return null;
        }

        @Override
        public TreePrinter.PrintableNode getRight() {
            return null;
        }

        @Override
        public String getText() {
            return "Node Second Level Right";
        }
    };
    TreePrinter.PrintableNode nodeRoot = new TreePrinter.PrintableNode() {
        @Override
        public TreePrinter.PrintableNode getLeft() {
            return nodeSecondLevelLeft;
        }

        @Override
        public TreePrinter.PrintableNode getRight() {
            return nodeSecondLevelRight;
        }

        @Override
        public String getText() {
            return "Root node";
        }
    };
    TreePrinter.PrintableNode loopNode = new TreePrinter.PrintableNode() {
        @Override
        public TreePrinter.PrintableNode getLeft() {
            return loopNode;
        }

        @Override
        public TreePrinter.PrintableNode getRight() {
            return loopNode;
        }

        @Override
        public String getText() {
            return "Loop node";
        }
    };
    @Test(expected = OutOfMemoryError.class)
    public void testTreeDisplayError() {
        Arrays.fill(branches, false);
        getTreeDisplay(loopNode, branches);
        printBranches(branches);
    }
    @Test
    public void testTreeDisplay0LevelsDeep() {
        Arrays.fill(branches, false);
        assertThat(getTreeDisplay(null, branches)).isEqualTo("    \n");
        printBranches(branches);
    }
    @Test
    public void testTreeDisplay2LevelsDeep() {
        Arrays.fill(branches, false);
        assertThat(getTreeDisplay(nodeRoot, branches)).isEqualTo(
                "                        Root node                       \n"
                + "              #############################             \n"
                + "   Node Second Level Left      Node Second Level Right  \n");
        printBranches(branches);
    }
    @Test
    public void testTreeDisplay1LevelDeep() {
        Arrays.fill(branches, false);
        assertThat(getTreeDisplay(nodeSecondLevelLeft, branches)).isEqualTo("  Node Second Level Left  \n");
        printBranches(branches);
    }

    private void printBranches(boolean[] branches) {
        for (int i = 0; i < branches.length; i++) {
            if (branches[i]) {
                System.out.printf("Branch %d was taken\n", i);
            } else {
                System.out.printf("Branch %d was not taken\n", i);
            }
        }
    }
}
