package base

import org.scalatest.WordSpec
import shape.{Location, Rectangle}

/**
 * Created by bruno on 2/19/15.
 */
class BoundingBoxSpec extends WordSpec {
  "A BoundingBox" when {
    "calculated to a given Rectangle" should {
      "produce the correct BoundingBox which contains a shape that equals the rectangle" in {
        val rectangle = Rectangle(16, 16)
        val bb = BoundingBox(rectangle)
        assert(bb.shape == rectangle)
      }
    }
    "calculated to a given Polygon" should {
      "produce the correct BoundingBox" in {
        val bb = BoundingBox(Fixtures.simplePolygon)
        assert(bb.shape.sides.contains(LineSegment(Point(0,0), Point(2,0))))
        assert(bb.shape.sides.contains(LineSegment(Point(2,0), Point(2,2))))
        assert(bb.shape.sides.contains(LineSegment(Point(2,2), Point(0,2))))
        assert(bb.shape.sides.contains(LineSegment(Point(0,2), Point(0,0))))
      }
    }
    "calculated to a given Location" should {
      "produce the correct BoundingBox" in {
        val location = Location(Fixtures.locationPoint, Fixtures.simplePolygon)
        val bb = BoundingBox(location)
        assert(bb.sides.contains(LineSegment(Point(10,10), Point(12,10))))
        assert(bb.sides.contains(LineSegment(Point(12,10), Point(12,12))))
        assert(bb.sides.contains(LineSegment(Point(12,12), Point(10,12))))
        assert(bb.sides.contains(LineSegment(Point(10,12), Point(10,10))))
      }
    }
    "calculated to a given Group" should {
      "produce the correct BoundingBox" in {
        val bb = BoundingBox(Fixtures.complexGroup)
        assert(bb.originPoint == Point(-6,-4))
        assert(bb.getArea() == 80)
      }
    }
  }

}
