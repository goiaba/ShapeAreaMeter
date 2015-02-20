package ui

import java.awt.Graphics2D

import shape.{Location, Polygon, Rectangle, Shape}

/**
 * Created by bruno on 2/19/15.
 */
class Draw(g: Graphics2D) {
  def apply(shape: Shape): Unit = shape match {
    case Rectangle(p1, p2, p3, p4) =>
      val points = List(p1, p2, p3, p4)
      val x = points.minBy(_.x).x.toInt
      val y = points.minBy(_.y).y.toInt
      val width = (points.maxBy(_.x).x - x).toInt
      val height = (points.maxBy(_.y).y - y).toInt
      g.drawRect(x, y, width, height)
    case Polygon(points@_*) =>
      val nPoints = points.size
      val xPoints = points.map(p => p.x.toInt).toArray
      val yPoints = points.map(p => p.y.toInt).toArray
      g.drawPolygon(xPoints, yPoints, nPoints)
    case Location(point, shape) =>
      apply(shape)
    case _ =>
  }
}
