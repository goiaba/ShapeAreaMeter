package ui

import java.awt.Graphics2D

import shape._
import base.Point

/**
 * Created by bruno on 2/19/15.
 */
class Draw(g: Graphics2D) {
  def apply(shape: Shape): Unit = shape match {
    case Rectangle(w, h) =>
      g.drawRect(0, -h.toInt, w.toInt, h.toInt)
    case Polygon(points@_*) =>
      val nPoints = points.size
      val xPoints = points.map(p => p.x.toInt).toArray
      val yPoints = points.map(p => -p.y.toInt).toArray
      g.drawPolygon(xPoints, yPoints, nPoints)
    case Location(point, shape) =>
      g.translate(point.x, -point.y)
      apply(shape)
      g.translate(-point.x, point.y)
    case Group(shapes @ _*) =>
      shapes.foreach(apply)
  }
}
