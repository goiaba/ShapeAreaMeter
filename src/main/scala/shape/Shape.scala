package shape

import base.{Point, LineSegment}

/**
 * Created by bruno on 2/8/15.
 */
trait Shape {

  val points: Seq[Point]

  val sides: List[LineSegment]

  def scale(factor: Double): Shape

  def contain(point: Point): Boolean

  def getArea(): Double

  def getExtremePoints(): (Double, Double, Double, Double)

}
