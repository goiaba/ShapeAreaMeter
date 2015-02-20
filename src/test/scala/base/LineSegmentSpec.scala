package base

import org.scalatest.WordSpec

/**
 * Created by bruno on 2/7/15.
 */
class LineSegmentSpec extends WordSpec {
  "The LineSegment" when {
    "given a null point" should {
      "produce an exception" in {
        intercept[IllegalArgumentException] {
          LineSegment(null, Fixtures.A)
        }
      }
    }
    "given two equal points" should {
      "throw an exception" in {
        intercept[IllegalArgumentException] {
          LineSegment(Fixtures.A, Fixtures.A)
        }
      }
    }
    "given two points I(-1,9) and J(9,-1)" should {
      "create a LineSegment formed by those points" in {
        val I = Fixtures.IJ.p1
        val J = Fixtures.IJ.p2
        assert(I == Fixtures.I && J == Fixtures.J)
      }
    }
    "given a point A(1,2) and a line segment formed by the " +
      "points I(-1,9) and J(9,-1)" should {
      "assure that IJ does not contain A" in {
        assert(Fixtures.IJ.contain(Some(Fixtures.A)) == false)
      }
    }
    "given two line segments IJ[(-1,9), (9,-1)] and " +
      "KL[(1,9), (-9,-1)]" should {
      "assure that IJ intersects KL in the point (0,8)" in {
        Fixtures.IJ.intersect(Fixtures.KL).foreach(p => assert(p == Point(0.0,8.0)))
      }
    }
    "given two parallel line segments CD[(-8,8), (8,8)] and " +
      "EF[(-8,-8), (8,-8)]" should {
      "assure that CD and EF does not intersect" in {
        assert(Fixtures.CD.intersect(Fixtures.EF).isEmpty)
      }
    }
    "given the same two parallel line segments CD[(-8,8), (8,8)] and " +
      "EF[(-8,-8), (8,-8)]" should {
      "assure that EF and CD does not intersect" in {
        assert(Fixtures.EF.intersect(Fixtures.CD).isEmpty)
      }
    }
  }
}
