package shape

import base.{BoundingBox, LineSegment, Point}

/**
 * Created by bruno on 2/8/15.
 */
case class Location(originPoint: Point, shape: Shape) extends Shape {
  require(originPoint != null, "A point must be specified.")
  require(shape != null, "A shape must be specified.")

  /**
   * List of Point's that compose this Shape.
   */
  override val points = shape.points.map { p => p+originPoint }

  /**
   * List of LineSegment's formed by each pair of Point's passed
   * as argument when creating this Shape
   */
  override val sides = shape.sides.map {
      side => LineSegment(side.p1+originPoint, side.p2+originPoint)
    }

  /**
   * Calculate the a area of this Shape
   *
   * @return the area of this Shape
   */
  override def getArea(): Double = shape.getArea()

  /**
   * Scales a shape by a given factor
   *
   * @param factor the factor to use to scale this Shape
   * @return a new Shape scaled by factor
   */
  override def scale(factor: Double): Shape =
    Location(originPoint, shape.scale(factor))

  /**
   * Verify if a given Point is contained inside this Shape
   *
   * @param point the Point to be verified
   * @return true if the given Point is inside this Shape
   *          and false otherwise
   */
  override def contain(point: Point): Boolean =
    Polygon(shape.points.map(pt => pt + originPoint):_*).contain(point)

  /**
   * Return a tuple containing the extreme points of this Shape.
   *
   * @return a tuple containing the following information
   *          (minX, minY, maxX, maxY)
   */
  override def getExtremePoints(): (Double, Double, Double, Double) = {
    val (minX, minY, maxX, maxY) = shape.getExtremePoints()
    (minX + originPoint.x, minY + originPoint.y,
      maxX + originPoint.x, maxY + originPoint.y)
  }

}