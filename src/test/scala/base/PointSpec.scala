package base

import org.scalatest.WordSpec

/**
 * Created by bruno on 2/7/15.
 */
class PointSpec extends WordSpec {

  "The Point" when {
    "given x=1 and y=2" should {
      "create a point A(1,2)" in {
        assert(Fixtures.A.x == 1.0 && Fixtures.A.y == 2.0)
      }
    }
    "given A(1,2) and B(5,9)" should {
      "return the correct distance between these points" in {
        assert(Fixtures.A.distanceTo(Fixtures.B) == math.sqrt(65))
      }
    }
  }

}
