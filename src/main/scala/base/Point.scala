package base

/**
 * Created by bruno on 2/6/15.
 */
import scala.math._

case class Point(x: Double, y: Double) {
  /**
   * Determine the distance between this point and
   *  another Point
   *
   * @param anotherPoint the point to which the distance
   *                      should be calculated
   *
   * @return the distance between the points
   */
  def distanceTo(anotherPoint: Point): Double = {
    sqrt(
      pow((this.x - anotherPoint.x), 2) +
      pow((this.y - anotherPoint.y), 2)
    )
  }

  def +(point: Point): Point =
    Point(point.x + x, point.y + y)
}