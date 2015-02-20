package shape

import base.{LineSegment, Point}

/**
 * Created by bruno on 2/19/15.
 */
case class Rectangle(p1: Point, p2: Point, p3: Point, p4: Point) extends Shape {

  override val points = Seq(p1, p2, p3, p4)

  /**
   * List of LineSegment's formed by each pair of points passed
   * as argument when creating this Polygon
   */
  override val sides = points.:+(points.head).sliding(2).map(x =>
    LineSegment(x.head, x.last)).toList

  override def getArea(): Double = {
    val (minX, minY, maxX, maxY) = getExtremePoints()
    (maxX - minX) * (maxY - minY)
  }

  override def scale(factor: Double): Shape =
    Polygon(points:_*).scale(factor)

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

  override def contain(point: Point): Boolean =
    Polygon(points:_*).contain(point)
}
