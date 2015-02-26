package base

import shape.{Location, Polygon, Rectangle, Shape, Group}

/**
 * Created by bruno on 2/8/15.
 */
object BoundingBox {
  /**
   * Define the BoundingBox of a given Shape. A bounding box is the
   *  smallest rectangle parallel to the axes that encloses the entire
   *  polygon.
   *
   * @param shape the Shape for which we want to define the bounding
   *               box
   * @return a Location representing the BoundingBox of the given
   *          Shape
   */
   def apply(shape: Shape): Location = shape match {
    case Rectangle(w, h) =>
      Location(Point(0, 0), Rectangle(w, h))
    case Polygon(points @ _*) =>
      val (minX, minY, maxX, maxY) =
        Polygon(points:_*).getExtremePoints()
      Location(Point(minX, minY),
        Rectangle(maxX-minX, maxY-minY))
    case Location(point, shape) =>
      val rectangle = BoundingBox(shape)
      Location(rectangle.originPoint+point, rectangle.shape)
    case Group(shapes @ _*) =>
      val (minX, minY, maxX, maxY) =
        Group(shapes:_*).getExtremePoints()
      Location(Point(minX, minY),
        Rectangle(maxX-minX, maxY-minY))
  }

}
