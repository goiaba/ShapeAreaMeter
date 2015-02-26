package shape

import base.{LineSegment, Point}

import scala.collection.immutable.List
import scala.collection.immutable.Seq

/**
 * Created by bruno on 2/22/15.
 */
case class Group(shapes: Shape*) extends Shape {

  /**
   * List of Point's that compose this Shape.
   */
  override val points =
    shapes.flatMap(shape => shape.points)

  /**
   * List of LineSegment's formed by each pair of Point's passed
   * as argument when creating this Shape
   */
  override val sides: List[LineSegment] =
    shapes.map(shape => shape.sides).flatten.toList

  /**
   * Return a tuple containing the extreme points of this Shape.
   *
   * @return a tuple containing the following information
   *          (minX, minY, maxX, maxY)
   */
  override def getExtremePoints() =
    (points.minBy(point => point.x).x, points.minBy(point => point.y).y,
      points.maxBy(point => point.x).x, points.maxBy(point => point.y).y)

  /**
   * Scales a shape by a given factor
   *
   * @param factor the factor to use to scale this Shape
   * @return a new Shape scaled by factor
   */
  override def scale(factor: Double): Shape =
    Group(shapes.map(shape => shape.scale(factor)):_*)

  /**
   * Verify if a given Point is contained into one of the
   *  Shape's that compose this Group.
   *
   * @param point the point to be verified
   * @return An option containing true if the given Point
   *         is inside one of the Shape's contained by this
   *         Group, an option containing false otherwise
   */
  override def contain(point: Point): Boolean =
    shapes.exists(shape => shape.contain(point))

  /**
   * Calculate the a area of this Shape
   *
   * @return the sum of the areas of each Shape contained
   *          in this Group
   */
  override def getArea(): Double =
    shapes.foldLeft(0d)((acc, shape) => acc + shape.getArea())
}
