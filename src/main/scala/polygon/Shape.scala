package polygon

import base.{Point, LineSegment}

/**
 * Created by bruno on 2/8/15.
 */
trait Shape {
  /**
   * Creates a list of LineSegment's formed by each pair of points passed
   *  as argument when creating this Shape
   *
   * @return an Iterator containing the LineSegment's that compose this
   *          Shape
   */
  def sides(): Iterator[LineSegment]

  /**
   * Verify if a given Point is contained inside this Shape
   *
   * @param point the point to be verified
   * @return An option containing true if the given Point
   *          is inside this Shape, an option containing
   *          false otherwise
   */
  def contain(point: Point): Option[Boolean]

  /**
   * Scales a shape by a given factor
   * 
   * @param factor the factor to use to scale this Shape
   * @return a new Shape scaled by factor
   */
  def scale(factor: Double): Shape

  /**
   * Calculate the a area of this Shape using Monte Carlo
   *  method
   *
   * @return the area of this Shape
   */
  def getArea(): Double
}
