


package com.williamfiset.algorithms.geometry;

import static com.google.common.truth.Truth.assertThat;

import java.awt.geom.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
public class LineSegmentLineSegmentIntersectionTest {

    boolean[] branches = new boolean[7];
    @Before
    public void setUp() {
    for (int i = 0; i < branches.length; i++) {
        branches[i] = false;
    }
      }

    @Test
    //tests the case where the line segments are parallel and overlap
    public void testLineSegmentLineSegmentIntersection1() {
        
    //set all branches to false
    
        LineSegmentLineSegmentIntersection.Pt p1 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        LineSegmentLineSegmentIntersection.Pt p2 = new LineSegmentLineSegmentIntersection.Pt(1, 1);
        LineSegmentLineSegmentIntersection.Pt p3 = new LineSegmentLineSegmentIntersection.Pt(0.5, 0.5);
        LineSegmentLineSegmentIntersection.Pt p4 = new LineSegmentLineSegmentIntersection.Pt(1.5, 1.5);
        LineSegmentLineSegmentIntersection.Pt[] pts = LineSegmentLineSegmentIntersection.lineSegmentLineSegmentIntersection(p1, p2, p3, p4, branches);
        assertThat(pts.length).isEqualTo(2);
        assertThat(pts[0].x).isEqualTo(0.5);
        assertThat(pts[0].y).isEqualTo(0.5);
        assertThat(pts[1].x).isEqualTo(1);
        assertThat(pts[1].y).isEqualTo(1);
        printBranches(branches);
    }
    @Test
    //tests the case where the line segments do not intersect
    public void testLineSegmentLineSegmentIntersection2() {
        LineSegmentLineSegmentIntersection.Pt p1 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        LineSegmentLineSegmentIntersection.Pt p2 = new LineSegmentLineSegmentIntersection.Pt(1, 1);
        LineSegmentLineSegmentIntersection.Pt p3 = new LineSegmentLineSegmentIntersection.Pt(2, 2);
        LineSegmentLineSegmentIntersection.Pt p4 = new LineSegmentLineSegmentIntersection.Pt(3, 3);
        LineSegmentLineSegmentIntersection.Pt[] pts = LineSegmentLineSegmentIntersection.lineSegmentLineSegmentIntersection(p1, p2, p3, p4, branches);
        assertThat(pts.length).isEqualTo(0);
        printBranches(branches);
    }
    @Test
    // Both segments are a single point.
    public void testLineSegmentLineSegmentIntersection3() {
        LineSegmentLineSegmentIntersection.Pt p1 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        LineSegmentLineSegmentIntersection.Pt p2 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        LineSegmentLineSegmentIntersection.Pt p3 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        LineSegmentLineSegmentIntersection.Pt p4 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        LineSegmentLineSegmentIntersection.Pt[] pts = LineSegmentLineSegmentIntersection.lineSegmentLineSegmentIntersection(p1, p2, p3, p4, branches);
        assertThat(pts.length).isEqualTo(1);
        assertThat(pts[0].x).isEqualTo(0);
        assertThat(pts[0].y).isEqualTo(0);
        printBranches(branches);
    }
    // both segments are the same
    @Test
    public void testLineSegmentLineSegmentIntersection4() {
        LineSegmentLineSegmentIntersection.Pt p1 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        LineSegmentLineSegmentIntersection.Pt p2 = new LineSegmentLineSegmentIntersection.Pt(1, 1);
        LineSegmentLineSegmentIntersection.Pt p3 = new LineSegmentLineSegmentIntersection.Pt(0, 0);
        LineSegmentLineSegmentIntersection.Pt p4 = new LineSegmentLineSegmentIntersection.Pt(1, 1);
        LineSegmentLineSegmentIntersection.Pt[] pts = LineSegmentLineSegmentIntersection.lineSegmentLineSegmentIntersection(p1, p2, p3, p4, branches);
        assertThat(pts.length).isEqualTo(2);
        assertThat(pts[0].x).isEqualTo(0);
        assertThat(pts[0].y).isEqualTo(0);
        assertThat(pts[1].x).isEqualTo(1);
        assertThat(pts[1].y).isEqualTo(1);
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
