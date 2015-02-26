package base

/**
 * Created by bruno on 2/6/15.
 */
import scala.math._

case class Point(x: Double, y: Double) {
  /**
   * Determine the distance between this Point and
   *  another Point
   *
   * @param anotherPoint the Point to which the distance
   *                      should be calculated
   *
   * @return the distance between the Point's
   */
  def distanceTo(anotherPoint: Point): Double = {
    sqrt(
      pow((this.x - anotherPoint.x), 2) +
      pow((this.y - anotherPoint.y), 2)
    )
  }

  /**
   * Sum two Point's
   *
   * @param point the Point to be added to this Point
   * @return a new Point representing the sum of this
   *          Point with a given one
   */
  def +(point: Point): Point =
    Point(point.x + x, point.y + y)
}