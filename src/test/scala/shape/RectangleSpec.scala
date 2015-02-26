package shape

import base.{Point, LineSegment, Fixtures}
import org.scalatest.WordSpec

/**
 * Created by bruno on 2/19/15.
 */
class RectangleSpec extends WordSpec {
  "A Rectangle" when {
    "when width=16 and height=16, located at Point (-8,-8)" should {
      val rectangle = Rectangle(16, 16)
      "be composed by the correct sides" in {
        assert(rectangle.sides.size == 4)
        assert(rectangle.sides.contains(LineSegment(Point(0,0),Point(16,0))))
        assert(rectangle.sides.contains(LineSegment(Point(16,0),Point(16,16))))
        assert(rectangle.sides.contains(LineSegment(Point(16,16),Point(0,16))))
        assert(rectangle.sides.contains(LineSegment(Point(0,16),Point(0,0))))
      }
      "contain the point (0,0)" in {
        assert(rectangle.contain(Point(0,0)))
      }
      "not contain the point (19,19)" in {
        assert(rectangle.contain(Point(19,19)) == false)
      }
      "contain the point (19,19) if scaled by 2" in {
        val rect2 = rectangle.scale(2)
        assert(rect2.contain(Point(19,19)))
      }
      "not contain the points (0,0) and (19,19) if translated to the point (20,20)" in {
        val transRect = Location(Point(20,20), rectangle)
        assert(transRect.contain(Point(0,0)) == false)
        assert(transRect.contain(Point(19,19)) == false)
      }
    }
  }
}
