package shape

import base.{LineSegment, Point}
import analysis.MonteCarloMethod

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
   * List of LineSegment's formed by each pair of Point's passed
   * as argument when creating this Shape
   */
  override val sides = points.:+(points.head).sliding(2).map(x =>
    LineSegment(x.head, x.last)).toList

  /**
   * Scales a shape by a given factor
   *
   * @param factor the factor to use to scale this Shape
   * @return a new Shape scaled by factor
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
   * Calculate the a area of this Shape
   *
   * @return the area of this Shape
   */
  override def getArea(): Double =
    MonteCarloMethod.calculateArea(this)

  /**
   * Return a tuple containing the extreme points of this Shape.
   *
   * @return a tuple containing the following information
   *          (minX, minY, maxX, maxY)
   */
  override def getExtremePoints(): (Double, Double, Double, Double) = {
    (points.minBy(_.x).x, points.minBy(_.y).y,
      points.maxBy(_.x).x, points.maxBy(_.y).y)
  }

}
