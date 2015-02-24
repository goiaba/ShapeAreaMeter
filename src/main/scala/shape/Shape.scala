package shape

import base.{Point, LineSegment}

/**
 * Created by bruno on 2/8/15.
 */
trait Shape {

  /**
   * List of points that compose this shape.
   */
  val points: Seq[Point]

  /**
   * List of LineSegment's formed by each pair of points passed
   * as argument when creating this Polygon
   */
  val sides: List[LineSegment]

  /**
   * Scales a polygon by a given factor
   *
   * @param factor the factor to use to scale this Polygon
   * @return a new Polygon scaled by factor
   */
  def scale(factor: Double): Shape

  /**
   * Verify if a given Point is contained inside this Polygon
   *
   * @param point the point to be verified
   * @return An option containing true if the given Point
   *         is inside this Polygon, an option containing
   *         false otherwise
   */
  def contain(point: Point): Boolean

  /**
   * Calculate the a area of this Polygon using Monte Carlo
   *  method
   *
   * @return the area of this Shape
   */
  def getArea(): Double

  /**
   * Return a tuple containing the extreme points of this Polygon.
   *
   * @return a tuple containing the following information
   *          (minX, minY, maxX, maxY)
   */
  def getExtremePoints(): (Double, Double, Double, Double)

}
