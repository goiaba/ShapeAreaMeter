package shape

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
    "given any number of points (greater then 3) but with a null value contained " +
      "in the list of points" should {
      "produce an exception" in {
        intercept[IllegalArgumentException] {
          Polygon(Fixtures.A, Fixtures.B, Fixtures.C, null)
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
    "created with the points (0,0), (2,2) and (2,0)" should {
      "consist of three LineSegment's" in {
        val polygon = Fixtures.simplePolygon
        assert(polygon.sides.size == 3)
        assert(polygon.sides.contains(LineSegment(Point(0,0),Point(2,2))))
        assert(polygon.sides.contains(LineSegment(Point(2,2),Point(2,0))))
        assert(polygon.sides.contains(LineSegment(Point(2,0),Point(0,0))))
      }
    }
    "created with the points (0,0), (2,2) and (2,0) and queried " +
      "if the point (1,1) (over one side) is contained on it" should {
      "return true" in {
        val polygon = Fixtures.simplePolygon
        val point = Point(1,1)
        assert(polygon.contain(point))
      }
    }
    "created with the points (0,0), (2,2) and (2,0) and queried " +
      "if the point (1.5,1) is contained on it" should {
      "return true" in {
        val polygon = Fixtures.simplePolygon
        val point = Point(1.5,1)
        assert(polygon.contain(point))
      }
    }
    "created with the points (0,0), (2,2) and (2,0) " +
      "and scaled by 0.75" should {
      "consist of three scaled LineSegment's" in {
        val polygon = Fixtures.simplePolygon.scale(0.75)
        assert(polygon.sides.size == 3)
        assert(polygon.sides.contains(LineSegment(Point(0,0),Point(2*.75,2*.75))))
        assert(polygon.sides.contains(LineSegment(Point(2*.75,2*.75),Point(2*.75,0))))
        assert(polygon.sides.contains(LineSegment(Point(2*.75,0),Point(0,0))))
      }
    }
    "created with the points (0,0), (2,2) and (2,0) " +
      "and scaled by a negative value" should {
      "throw an exception" in {
        intercept[IllegalArgumentException] {
          Fixtures.simplePolygon.scale(-1)
        }
      }

    }
    "created with the points (0,0), (2,2) and (2,0)" should {
      "have an area of approximately 2" in {
        val conceptualArea = 2
        val polygon = Fixtures.simplePolygon
        val computedArea = polygon.getArea()
        val error = math.abs(computedArea - conceptualArea) / conceptualArea
        assert(error <= Fixtures.epsilon)
      }
    }
    "created with 7 sides and queried" +
      "if the point (-1,1) is contained on it" should {
      "return false" in {
        val polygon = Fixtures.simplePolygon
        val point = Point(-1,1)
        assert(polygon.contain(point) == false)
      }
    }
    "created with the points (0,0), (10,5), (20,0), (0,10), (10,15), (20,10)" should {
      val p1 = Point(0, 0)
      val p2 = Point(10, 5)
      val p3 = Point(20, 0)
      val p4 = Point(20, 10)
      val p5 = Point(10, 15)
      val p6 = Point(0, 10)
      val pl = Polygon(p1, p2, p3, p4, p5, p6)

      "not contain the points (10,0)" in {
        val pout = Point(10, 0)
        assert(pl.contain(pout) == false)
      }
      "contain the point (10,10)" in {
        val pin = Point(10, 10)
        assert(pl.contain(pin))
      }
      "contain the point (0,10) that coincides with a vertex" in {
            val pin = Point(0, 10)
            assert(pl.contain(pin))
          }
          "contain the point (0,0) that coincides with a vertex" in {
            val pin = Point(0, 0)
            assert(pl.contain(pin))
          }
    }
    "created with the points (20,10), (40,30), (20,50), (50,70), (80,50), (80,30), (60,20), (60,0), (40,20)" should {
      val p1 = Point(20, 10)
      val p2 = Point(40, 30)
      val p3 = Point(20, 50)
      val p4 = Point(50, 70)
      val p5 = Point(80, 50)
      val p6 = Point(80, 30)
      val p7 = Point(60, 20)
      val p8 = Point(60, 0)
      val p9 = Point(40, 20)

      val polygon = Polygon(p1, p2, p3, p4, p5, p6, p7, p8, p9)
      "not contain the points (0,0) and (10,20)" in {
        val pout1 = Point(10, 20)
        val pout2 = Point(0, 0)
        assert(polygon.contain(pout1) == false)
        assert(polygon.contain(pout2) == false)
      }
      "contain the Point (30,20)" in {
        val pin1 = Point(30, 20)
        assert(polygon.contain(pin1))
      }
      "return its correct area" in {

      }
    }
    "created with the points (-4,6), (-3,3), (1,3), (-4,-4), (-6,3)" should {
      val complexPolygon = Fixtures.complexPolygon
      "return its correct area" in {
        val conceptualArea = 29
	      val computedArea = complexPolygon.getArea()
        val error = math.abs(computedArea - conceptualArea) / conceptualArea
        assert(Fixtures.isGoodEnough(conceptualArea, computedArea))
      }
    }
  }

}
