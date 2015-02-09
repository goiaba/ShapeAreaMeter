package polygon

import base.{BoundingBox, Ray, LineSegment, Point}
import montecarlo.MonteCarloMethodHelper

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

  override def sides(): Iterator[LineSegment] =
    points.:+(points.head).sliding(2).map(x =>
      LineSegment(x.head,x.last)
    )

  override def contain(point: Point): Option[Boolean] = {
    val ray = new Ray(point, Point(point.x+1, point.y))
    val contain = sides().foldLeft(0) {(acc, n) =>
      if (ray.intersect(n).isDefined) acc+1 else acc
    } % 2 != 0
    Some(contain)
  }

  override def scale(factor: Double): Shape = {
    require(factor > 0,
      "The value to be used to scale the polygon must be positive.")
    Polygon(sides().map { lineSeg =>
      Point(lineSeg.p1.x * factor, lineSeg.p1.y * factor)
    }.toSeq:_*)
  }

  override def getArea(): Double =
    MonteCarloMethodHelper.calculateArea(this)

}
