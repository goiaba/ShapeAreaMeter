package base

import polygon.{Polygon, Shape}

/**
 * Created by bruno on 2/8/15.
 */
object BoundingBox {
  /**
   * Define the bounding box of a given polygon. A bounding
   *  box is the smallest rectangle parallel to the axes that
   *  encloses the entire polygon.
   * @param p the Shape for which we want to define the
   *           bounding box
   * @return the bounding box of the given rectangle and
   *          its area
   */
  def apply(p: Shape): (Shape, Double) = {
    val (minX, minY, maxX, maxY) = boundaries(p)
    val area = (maxX - minX) * (maxY - minY)
    val boundingBox =
      Polygon(Point(minX,maxY), Point(maxX, maxY), Point(maxX, minY), Point(minX, minY))
    (boundingBox, area)
  }

  /**
   * Return a tuple containing the boundaries of the bounding
   *  box of the given Shape.
   * @param p the Shape for which we want to define the
   *           bounding box boundaries
   * @return a tuple containing the following information
   *          (minX, minY, maxX, maxY)
   */
  def boundaries(p: Shape): (Double, Double, Double, Double) = {
    val points = p.sides().map(s => Point(s.p1.x, s.p1.y)).toSeq
    (points.minBy(_.x).x, points.minBy(_.y).y,
     points.maxBy(_.x).x, points.maxBy(_.y).y)
  }
}
