package base

import org.scalatest.WordSpec

/**
 * Created by bruno on 2/8/15.
 */
class RaySpec extends WordSpec {
  "The Ray" when {
    "given a ray OP[(-6,5), (0,5)]" should {
      "assure that the ray OP does not intersect the line segments " +
        "MN[(9,1), (-1,-9)], GH[(-9,1), (1,-9)], CD[(-8,8), (8,8)], " +
        "CE[(-8,8), (-8,-8)] and EF[(-8,-8), (8,-8)]" in {
        assert(Fixtures.OP.intersect(Fixtures.MN).isEmpty)
        assert(Fixtures.OP.intersect(Fixtures.GH).isEmpty)
        assert(Fixtures.OP.intersect(Fixtures.CD).isEmpty)
        assert(Fixtures.OP.intersect(Fixtures.CF).isEmpty)
        assert(Fixtures.OP.intersect(Fixtures.EF).isEmpty)
      }

      "assure that the ray OP does intersect the line segments " +
        "KL[(1,9), (-9,-1)], IJ[(-1,9), (9,-1)] and DF[(8,8), (8,-8)]" in {
        val p1 = Fixtures.OP.intersect(Fixtures.KL)
        val p2 = Fixtures.OP.intersect(Fixtures.IJ)
        val p3 = Fixtures.OP.intersect(Fixtures.DE)
        assert(p1.isDefined && p1.get == Point(-3.0, 5.0))
        assert(p2.isDefined && p2.get == Point(3.0, 5.0))
        assert(p3.isDefined && p3.get == Point(8.0, 5.0))
      }
    }
  }
}
