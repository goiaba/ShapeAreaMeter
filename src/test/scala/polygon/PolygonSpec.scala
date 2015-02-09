package polygon

import base.{LineSegment, Fixtures, Point}
import org.scalatest.WordSpec

/**
 * Created by bruno on 2/8/15.
 */
class PolygonSpec extends WordSpec {
  "A Polygon" when {
    "given less then three point" should {
      "produce an exception" in {
        intercept[IllegalArgumentException] {
          Polygon(Point(0,0), Point(1,1))
        }
      }
    }
    "given three point but only two different points" should {
      "produce an exception" in {
        intercept[IllegalArgumentException] {
          Polygon(Point(0,0), Point(1,1), Point(1,1))
        }
      }
    }
    "created with the points [0,0], [2,2] and [2,0]" should {
      "consist of three LineSegment's" in {
        val polygon = Fixtures.simplePolygon
        assert(polygon.sides().size == 3)
        assert(polygon.sides().contains(LineSegment(Point(0,0),Point(2,2))))
        assert(polygon.sides().contains(LineSegment(Point(2,2),Point(2,0))))
        assert(polygon.sides().contains(LineSegment(Point(2,0),Point(0,0))))
      }
    }
    "created with the points [0,0], [2,2] and [2,0] and queried" +
      "if the point [1.5,1] is contained on it" should {
      "return true" in {
        val polygon = Fixtures.simplePolygon
        val point = Point(1.5,1)
        val contain = polygon.contain(point)
        assert(contain.isDefined && contain.get == true)
      }
    }
    "created with the points [0,0], [2,2] and [2,0] " +
      "and scaled by 0.75" should {
      "consist of three scaled LineSegment's" in {
        val polygon = Fixtures.simplePolygon.scale(0.75)
        assert(polygon.sides().size == 3)
        assert(polygon.sides().contains(LineSegment(Point(0,0),Point(2*.75,2*.75))))
        assert(polygon.sides().contains(LineSegment(Point(2*.75,2*.75),Point(2*.75,0))))
        assert(polygon.sides().contains(LineSegment(Point(2*.75,0),Point(0,0))))
      }
    }
//    "created with 7 sides and queried" +
//      "if the point [-1,1] is contained on it" should {
//      "return false" in {
//        val polygon = Fixtures.simplePolygon
//        val point = Point(-1,1)
//        val contain = polygon.contain(point)
//        assert(contain.isDefined && contain.get == false)
//      }
//    }
  }

}
