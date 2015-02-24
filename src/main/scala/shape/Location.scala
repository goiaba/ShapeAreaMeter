package shape

import base.{BoundingBox, LineSegment, Point}

/**
 * Created by bruno on 2/8/15.
 */
case class Location(point: Point, shape: Shape) extends Shape {
  require(point != null, "A point must be specified.")
  require(shape != null, "A shape must be specified.")

  override val points = shape.points.map { p => p+point }

  override val sides = shape.sides.map {
      side => LineSegment(side.p1+point, side.p2+point)
    }

  override def getArea(): Double = shape.getArea()

  override def scale(factor: Double): Shape =
    Location(point, shape.scale(factor))

  override def contain(p: Point): Boolean =
    Polygon(shape.points.map(pt => pt + point):_*).contain(p)

  override def getExtremePoints(): (Double, Double, Double, Double) = {
    val (minX, minY, maxX, maxY) = shape.getExtremePoints()
    (minX + point.x, minY + point.y, maxX + point.x, maxY + point.y)
  }

}