package base

import shape.{Location, Polygon, Rectangle, Shape, Group}

/**
 * Created by bruno on 2/8/15.
 */
object BoundingBox {
  /**
   * Define the bounding box of a given polygon. A bounding box is the
   *  smallest rectangle parallel to the axes that encloses the entire
   *  polygon.
   *
   * @param shape the Polygon for which we want to define the bounding
   *               box
   * @return a Location representing the BoundingBox of the given
   *          shape
   */
   def apply(shape: Shape): Location = shape match {
    case Rectangle(p1, p2, p3, p4) =>
      Location(Point(0, 0), Rectangle(p1, p2, p3, p4))
    case Polygon(points @ _*) =>
      val (minX, minY, maxX, maxY) =
        Polygon(points:_*).getExtremePoints()
      Location(Point(minX, minY),
        Rectangle(Point(minX, minY), Point(maxX, minY),
          Point(maxX, maxY), Point(minX, maxY))
      )
    case Location(point, shape) =>
      val rectangle = BoundingBox(shape)
      Location(point, rectangle.shape)
    case Group(shapes @ _*) =>
      val (minX, minY, maxX, maxY) =
        Group(shapes:_*).getExtremePoints()
      Location(Point(minX, minY),
        Rectangle(Point(minX, minY), Point(maxX, minY),
        Point(maxX, maxY), Point(minX, maxY))
      )
  }

}
