package shape

import base.{LineSegment, Point}

import scala.collection.immutable.List
import scala.collection.immutable.Seq

/**
 * Created by bruno on 2/22/15.
 */
case class Group(shapes: Shape*) extends Shape {

  override val points: Seq[Point] = shapes.map(shape => shape.points).flatten.toList

  override val sides: List[LineSegment] = shapes.map(shape => shape.sides).flatten.toList

  /**
   * Return a tuple containing the extreme points of this Group.
   *
   * @return a tuple containing the following information
   *          (minX, minY, maxX, maxY)
   */
  override def getExtremePoints() =
    (points.minBy(point => point.x).x, points.minBy(point => point.y).y,
      points.maxBy(point => point.x).x, points.maxBy(point => point.y).y)

  /**
   * Scale a group of shapes by scaling each shape contained in the
   *  group.
   *
   * @param factor the factor to use to scale this Group of Shape's
   * @return a new Group scaled by factor
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
    shapes.exists(s => s.contain(point))

  override def getArea(): Double =
    shapes.foldLeft(0d)((acc, el) => acc + el.getArea())
}
