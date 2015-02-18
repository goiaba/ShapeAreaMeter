package base

import scala.math._

/**
 * Created by bruno on 2/6/15.
 */
case class LineSegment(p1:Point, p2: Point) {
  require(p1 != null && p2 != null, "At least two points are required to define a line.")
  require(p1 != p2, "Two different points are required to define a line.")

  /**
   * Determine if the Point of intersection between this LineSegment
   * and another LineSegment is contained in this line segment.
   *
   * @param lineSegment the other LineSegment
   * @return None if the lines are parallel, or if the point
   *         of intersection is not contained in this line
   *         segment, otherwise return an Option containing
   *         the Point of intersection
   */
  def intersect(lineSegment: LineSegment): Option[Point] = {
    for ((ua: Double, ub: Double, point: Point) <- testIntercept(lineSegment)
         if ((ua >= 0 && ua <= 1) && (ub >= 0 && ub <= 1))
    ) yield point
  }

  /**
    * Determine the point of intersection between this LineSegment and
   *  a given Ray
   *
   * @param ray the Ray
   * @return None if there is no intersection between this LineSegment
   *          and the given ray, otherwise return an Option containing
   *          the point of intersection
   */
  def intersect(ray: Ray): Option[Point] = {
    for ((ua: Double, ub: Double, point: Point) <- testIntercept(ray.ls)
         if ((ua >= 0 && ua <= 1) && ub >= 0)
    ) yield point
  }

  /**
   * Determine if a Point is contained in this LineSegment
   *
   * @param point the Point
   * @return true if the Point is contained in this LineSegment,
   *          otherwise return false
   */
  def contain(point: Option[Point]): Boolean = {
    (for (p <- point;
         p1ToPoint = p1.distanceTo(p);
         p2ToPoint = p2.distanceTo(p);
         p1ToP2 = p1.distanceTo(p2);
         contain = abs(p1ToPoint + p2ToPoint - p1ToP2) <= Constants.epsilon)
    yield contain).get
  }

  //FIXME write documentation
  /**
   * Determines the point of intercept between a given lineSegment
   *  and this.
   * @param lineSegment the given lineSegment to verify
   * @return a tuple of (ua: Double, ub: Double, p: Point)
   *         Given two LineSegments defined as A(A1, A2)
   *         and B(B1, B2), where A1, A2, B1 and B2 are Points,
   *         ua and ub define the point of intersection between
   *         the lines to which those segments belongs to, based
   *         on the value of the first point that define the
   *         LineSegments (A1 and B1).
   */
  private def testIntercept(lineSegment: LineSegment): Option[(Double, Double, Point)] = {
    val D: Double = ((lineSegment.p2.y - lineSegment.p1.y) * (p2.x - p1.x)) -
      ((lineSegment.p2.x - lineSegment.p1.x) * (p2.y - p1.y))
    if (math.abs(0 - D) <= Constants.epsilon)
      None
    else {
      val ua = (((lineSegment.p2.x - lineSegment.p1.x) * (p1.y - lineSegment.p1.y)) -
        ((lineSegment.p2.y - lineSegment.p1.y) * (p1.x - lineSegment.p1.x))) / D
      val ub = (((p2.x - p1.x) * (p1.y - lineSegment.p1.y)) -
        ((p2.y - p1.y) * (p1.x - lineSegment.p1.x))) / D

      val x = (((lineSegment.p1.x - lineSegment.p2.x) * ((p1.x * p2.y) - (p1.y * p2.x))) -
        ((p1.x - p2.x) * ((lineSegment.p1.x * lineSegment.p2.y) - (lineSegment.p1.y * lineSegment.p2.x)))) / D
      val y = (((lineSegment.p1.y - lineSegment.p2.y) * ((p1.x * p2.y) - (p1.y * p2.x))) -
        ((p1.y - p2.y) * ((lineSegment.p1.x * lineSegment.p2.y) - (lineSegment.p1.y * lineSegment.p2.x)))) / D
      Some(ua, ub, Point(x, y))
    }
  }
}
