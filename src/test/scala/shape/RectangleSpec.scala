package shape

import base.{Point, LineSegment, Fixtures}
import org.scalatest.WordSpec

/**
 * Created by bruno on 2/19/15.
 */
class RectangleSpec extends WordSpec {
  "A Rectangle" when {
    "when created with the points [-8,8], [8,8], [-8,-8], [8,-8]" should {
      val rectangle = Rectangle(Fixtures.C, Fixtures.D, Fixtures.E, Fixtures.F)
      "be composed by the correct sides" in {
        assert(rectangle.sides.size == 4)
        assert(rectangle.sides.contains(LineSegment(Point(-8,8),Point(8,8))))
        assert(rectangle.sides.contains(LineSegment(Point(8,8),Point(-8,-8))))
        assert(rectangle.sides.contains(LineSegment(Point(-8,-8),Point(8,-8))))
        assert(rectangle.sides.contains(LineSegment(Point(8,-8),Point(-8,8))))
      }
      "contain the point [0,0]" in {
        assert(rectangle.contain(Point(0,0)))
      }
      "not contain the point [9,9]" in {
        assert(rectangle.contain(Point(9,9)) == false)
      }
      "contain the point [9,9] if scaled by 2" in {
        val rect2 = rectangle.scale(2)
        assert(rect2.contain(Point(9,9)))
      }
      "not contain the points [0,0] and [9,9] if translated to the point [20,20]" in {
        val transRect = Location(Point(20,20), rectangle)
        assert(transRect.contain(Point(0,0)) == false)
        assert(transRect.contain(Point(9,9)) == false)
      }
    }
  }

}
