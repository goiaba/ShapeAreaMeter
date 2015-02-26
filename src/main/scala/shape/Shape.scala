package shape

import base.{Ray, Point, LineSegment}

import scala.collection.mutable.ListBuffer

/**
 * Created by bruno on 2/8/15.
 */
trait Shape {

  /**
   * List of Point's that compose this Shape.
   */
  val points: Seq[Point]

  /**
   * List of LineSegment's formed by each pair of Point's passed
   * as argument when creating this Shape
   */
  val sides: List[LineSegment]

  /**
   * Scales a shape by a given factor
   *
   * @param factor the factor to use to scale this Shape
   * @return a new Shape scaled by factor
   */
  def scale(factor: Double): Shape

  /**
   * Verify if a given Point is contained inside this Shape
   *
   * @param point the Point to be verified
   * @return true if the given Point is inside this Shape
   *          and false otherwise
   */
  def contain(point: Point): Boolean = {
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
   * Calculate the a area of this Shape
   *
   * @return the area of this Shape
   */
  def getArea(): Double

  /**
   * Return a tuple containing the extreme points of this Shape.
   *
   * @return a tuple containing the following information
   *          (minX, minY, maxX, maxY)
   */
  def getExtremePoints(): (Double, Double, Double, Double)

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
