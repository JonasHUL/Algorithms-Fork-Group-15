package com.williamfiset.algorithms.datastructures.utils;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.williamfiset.algorithms.datastructures.utils.TreePrinter.getTreeDisplay;

public class TreePrinterTest {
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
        getTreeDisplay(loopNode);
    }
    @Test
    public void testTreeDisplay0LevelsDeep() {
        assertThat(getTreeDisplay(null)).isEqualTo("    \n");
    }
    @Test
    public void testTreeDisplay2LevelsDeep() {
        assertThat(getTreeDisplay(nodeRoot)).isEqualTo(
                "                        Root node                       \n"
                + "              #############################             \n"
                + "   Node Second Level Left      Node Second Level Right  \n");
    }
    @Test
    public void testTreeDisplay1LevelDeep() {
        assertThat(getTreeDisplay(nodeSecondLevelLeft)).isEqualTo("  Node Second Level Left  \n");
    }
}
