package shape

import base.{LineSegment, Point}

/**
 * Created by bruno on 2/19/15.
 */
case class Rectangle(width: Double, height: Double) extends Shape {

  /**
   * List of Point's that compose this Shape.
   */
  override val points = Seq(Point(0,0), Point(width,0),
    Point(width,height), Point(0,height))

  /**
   * List of LineSegment's formed by each pair of Point's passed
   * as argument when creating this Shape
   */
  override val sides = points.:+(points.head).sliding(2).map(x =>
    LineSegment(x.head, x.last)).toList

  /**
   * Calculate the a area of this Shape
   *
   * @return the area of this Shape
   */
  override def getArea(): Double = width * height

  /**
   * Scales a shape by a given factor
   *
   * @param factor the factor to use to scale this Shape
   * @return a new Shape scaled by factor
   */
  override def scale(factor: Double): Shape =
    Rectangle(width * factor, height * factor)

  /**
   * Return a tuple containing the extreme points of this Shape.
   *
   * @return a tuple containing the following information
   *         (minX, minY, maxX, maxY)
   */
  override def getExtremePoints(): (Double, Double, Double, Double) =
    (0, 0, width, height)

  /**
   * Verify if a given Point is contained inside this Shape
   *
   * @param point the Point to be verified
   * @return true if the given Point is inside this Shape
   *         and false otherwise
   */
  override def contain(point: Point): Boolean =
    Polygon(points:_*).contain(point)

}
