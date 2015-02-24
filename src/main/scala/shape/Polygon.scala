package shape

import base.{BoundingBox, Ray, LineSegment, Point}
import analysis.MonteCarloMethod

import scala.collection.mutable.ListBuffer

/**
 * Created by bruno on 2/8/15.
 */
case class Polygon(points: Point*) extends Shape {
  require(!points.contains(null),
    "Null points are not allowed when creation a polygon.")
  require(points.size >= 3,
    "At least three not collinear points are required to create a polygon.")
  require(points.diff(points.distinct).isEmpty,
    "Repeated points in the list. Please verify.")

  /**
   * List of LineSegment's formed by each pair of points passed
   * as argument when creating this Polygon
   */
  override val sides = points.:+(points.head).sliding(2).map(x =>
    LineSegment(x.head, x.last)).toList

  /**
   * Verify if a given Point is contained inside this Polygon
   *
   * @param point the point to be verified
   * @return An option containing true if the given Point
   *         is inside this Polygon, an option containing
   *         false otherwise
   */
  override def contain(point: Point): Boolean = {
    if (!sides.filter(_.contain(Some(point))).isEmpty) true
    else {
      val ray = new Ray(point, Point(point.x + 1, point.y))
      val vertexAlreadyTreated = new ListBuffer[Point]()
      val contain = sides.foldLeft(0) { (acc, n) =>
        val intersectionPoint = ray.intersect(n)
        if (intersectionPoint.isDefined &&
          treatVertexCase(ray, vertexAlreadyTreated, intersectionPoint.get))
          acc + 1
        else acc
      } % 2 != 0
      contain
    }
  }

  /**
   * Scales a polygon by a given factor. The polygon is moved to the
   *  origin, scaled by the factor and then translated to its original
   *  position back.
   *
   * @param factor the factor to use to scale this Polygon
   * @return a new Polygon scaled by factor
   */
  override def scale(factor: Double): Shape = {
    require(factor > 0,
      "The value to be used to scale the polygon must be positive.")
    val oP = points.head
    val newPoints = oP +: points.tail.map(
      p => Point(((p.x-oP.x)*factor)+oP.x, ((p.y-oP.y)*factor)+oP.y))
    Polygon(newPoints:_*)
  }

  /**
   * Calculate the a area of this Polygon using Monte Carlo
   *  method
   *
   * @return the area of this Shape
   */
  override def getArea(): Double =
    MonteCarloMethod.calculateArea(this)

  /**
   * Return a tuple containing the extreme points of this Polygon.
   *
   * @return a tuple containing the following information
   *          (minX, minY, maxX, maxY)
   */
  override def getExtremePoints(): (Double, Double, Double, Double) = {
    (points.minBy(_.x).x, points.minBy(_.y).y,
      points.maxBy(_.x).x, points.maxBy(_.y).y)
  }

  private def treatVertexCase(ray: Ray, vertexAlreadyTreated: ListBuffer[Point], pointOfIntersection: Point):Boolean = {
    if (isVertex(pointOfIntersection)) {
      if (notYetTreated(pointOfIntersection, vertexAlreadyTreated)) {
        val result = sides.filter(ls =>
          ls.p1.equals(pointOfIntersection) || ls.p2.equals(pointOfIntersection))
          .flatMap(ls => List(ls.p1, ls.p2))
          .filter(p => !p.equals(pointOfIntersection))
          .map(p => p.y)

        val y1 = result(0)
        val y2 = result(1)

        (y1 > ray.ls.p1.y && y2 < ray.ls.p1.y) ||
          (y1 < ray.ls.p1.y && y2 > ray.ls.p1.y)
      } else false
    } else true
  }

  private def notYetTreated(point: Point, list: ListBuffer[Point]): Boolean = {
    val notContain = !list.contains(point)
    list += point
    notContain
  }

  private def isVertex(point: Point) = points.contains(point)

}
