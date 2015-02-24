package shape

import base.{BoundingBox, Point, Fixtures}
import org.scalatest.WordSpec

/**
 * Created by bruno on 2/23/15.
 */
class GroupSpec extends WordSpec {
  "A Group" when {
    "Created as a simple Shape" should {
      "return the same extreme points" in {
        assert(Fixtures.simpleGroup.getExtremePoints() == Fixtures.simplePolygon.getExtremePoints())
      }
      "have the same list of sides" in {
        assert(Fixtures.simpleGroup.sides == Fixtures.simplePolygon.sides)
      }
      "have the same list of points" in {
        assert(Fixtures.simpleGroup.points == Fixtures.simplePolygon.points)
      }
      "create the same scaled Shape when scaled" in {
        assert(Fixtures.simpleGroup.scale(2) == Group(Fixtures.simplePolygon.scale(2)))
      }
      "return false when queried if the point [-1,1]" +
        "is contained in a Shape of the Group" in {
        val point = Point(-1,1)
        assert(Fixtures.simpleGroup.contain(point) == false)
      }
      "return true when queried if the point [1,1]" +
        "is contained in a Shape of the Group" in {
        val point = Point(1,1)
        assert(Fixtures.simpleGroup.contain(point))
      }
      "return approximately the same area as the one calculated to the simple shape" in {
        val simplePolygonComputedArea = Fixtures.simplePolygon.getArea()
        val groupComputedArea = Fixtures.simpleGroup.getArea()
        val error = math.abs(simplePolygonComputedArea - groupComputedArea) / simplePolygonComputedArea
        assert(error <= Fixtures.epsilon)
      }
    }
    "Created as a complex Group of Shape's" should {
      val anotherComplexPolygon: Polygon = Polygon(Fixtures.C, Fixtures.D, Fixtures.E, Fixtures.F)
      "return the correct extreme points" in {
        val minX = Fixtures.C.x
        val minY = Fixtures.E.y
        val maxX = Fixtures.D.x
        val maxY = Fixtures.D.y
        assert(Fixtures.moreComplexGroup.getExtremePoints() == (minX, minY, maxX, maxY))
      }
      "have a list of sides that include the sides of all its Shape's" in {
        assert(Fixtures.moreComplexGroup.sides == Fixtures.simplePolygon.sides :::
          anotherComplexPolygon.sides ::: Fixtures.complexPolygon.sides)
      }
      "have a list of points that include the points of all its Shape's" in {
        assert(Fixtures.moreComplexGroup.points == Fixtures.simplePolygon.points ++
          anotherComplexPolygon.points ++ Fixtures.complexPolygon.points)
      }
      "create the same scaled shape's as they were scaled separately" in {
        assert(Fixtures.moreComplexGroup.scale(2) == Group(Fixtures.simplePolygon.scale(2),
          Group(anotherComplexPolygon.scale(2), Group(Fixtures.complexPolygon.scale(2)))))
      }
      "return true when queried if the point [-1,1]" +
        "is contained in a Shape of the Group" in {
        val point = Point(-1,1)
        assert(Fixtures.moreComplexGroup.contain(point))
      }
      "return false when queried if the point [60,0]" +
        "is contained in a Shape of the Group" in {
        val point = Point(60,0)
        assert(Fixtures.moreComplexGroup.contain(point) == false)
      }
      "return the sum of the areas of the Shape's that compose the group" in {
        val groupComputedArea = Fixtures.moreComplexGroup.getArea()
        val sumOfSingleAreas = Fixtures.simplePolygon.getArea() +
          anotherComplexPolygon.getArea() +
          Fixtures.complexPolygon.getArea()
        val error = math.abs(sumOfSingleAreas - groupComputedArea) / sumOfSingleAreas
        assert(error <= Fixtures.epsilon)
      }
    }
  }
}
