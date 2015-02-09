package base

import scala.math._

/**
 * Created by bruno on 2/6/15.
 */
case class Ray(ls: LineSegment) {
  def this(p1: Point, p2: Point) = this(LineSegment(p1,p2))

  /**
   * Determine the Point of intersection between this Ray
   *  and a given LineSegment
   *
   * @param lineSegment the LineSegment
   * @return None if this ray and the line are parallel, otherwise return
   *          the an Option containing the Point of intersection
   */
  def intersect(lineSegment: LineSegment): Option[Point] = {
    lineSegment.intersect(this)
  }
}