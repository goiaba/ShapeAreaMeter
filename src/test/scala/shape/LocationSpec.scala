package shape

import base.{Point, LineSegment, Fixtures}
import org.scalatest.WordSpec

/**
 * Created by bruno on 2/19/15.
 */
class LocationSpec extends WordSpec {
  "A Location" when {
    "created with a null Point" should {
      "produce an exception" in {
        intercept[IllegalArgumentException] {
          Location(null, Fixtures.simplePolygon)
        }
      }
    }
    "created with a null Shape" should {
      "produce an exception" in {
        intercept[IllegalArgumentException] {
          Location(Fixtures.A, null)
        }
      }
    }
    "created with the points (0,0), (2,2) and (2,0) and transposed to point (10,10)" should {
      val location = Location(Fixtures.locationPoint, Fixtures.simplePolygon)
      "consist of three LineSegment's" in {
        assert(location.sides.size == 3)
        assert(location.sides.contains(LineSegment(Point(10,10),Point(12,12))))
        assert(location.sides.contains(LineSegment(Point(12,12),Point(12,10))))
        assert(location.sides.contains(LineSegment(Point(12,10),Point(10,10))))
      }
      "return the extreme points" in {
        assert(location.getExtremePoints() == (10,10,12,12))
      }
      "contain the point (11,11)" in {
        assert(location.contain(Point(11,11)))
      }
    }
    "created with the points (0,0), (2,2) and (2,0), transposed to point (10,10) and" +
      "scaled by 3" should {
      val location = Location(Fixtures.locationPoint, Fixtures.simplePolygon).scale(3)
      "consist of the following LineSegment's" in {
        assert(location.sides.size == 3)
        assert(location.sides.contains(LineSegment(Point(10,10),Point(16,16))))
        assert(location.sides.contains(LineSegment(Point(16,16),Point(16,10))))
        assert(location.sides.contains(LineSegment(Point(16,10),Point(10,10))))
      }
      "contain the point (15,13)" in {
        assert(location.contain(Point(15,13)))
      }
      "have an area of approximately 162" in {
        val conceptualArea = 162
        val computedArea = location.scale(3).getArea()
        val error = math.abs(computedArea - conceptualArea) / conceptualArea
        assert(error <= Fixtures.epsilon)
      }
    }
  }
}
