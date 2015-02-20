package analysis

import base.{BoundingBox, Point}
import shape.{Shape, Polygon}

import scala.collection.immutable.IndexedSeq
import scala.util.Random

trait AnalysisMethod {
  def calculateArea(polygon: Polygon): Double
}

/**
 * Created by bruno on 2/9/15.
 */
object MonteCarloMethod extends AnalysisMethod {

  val shooterMultiplier = 1e+6

  def calculateArea(polygon: Polygon): Double = {
    val bb = BoundingBox(polygon)
    val randomPoints = randomPointsShooter(bb)
    val totalRandomPoints: Double = randomPoints.size
    val totalInnerRandomPoints =
      randomPoints.filter(polygon.contain(_)).size

    (totalInnerRandomPoints / totalRandomPoints) * bb.getArea()
  }
  
  private def randomPointsShooter(boundingBox: Shape): IndexedSeq[Point] = {
    val (minX, minY, maxX, maxY) = boundingBox.getExtremePoints()
    val shoots = (boundingBox.getArea() * shooterMultiplier).toInt
    val bX = minX + (maxX - minX)
    val bY = minY + (maxY - minY)

    (1 to shooterMultiplier.toInt).map { index =>
      val randX = BigDecimal(bX * Random.nextDouble()).setScale(4, BigDecimal.RoundingMode.HALF_UP).toDouble
      val randY = BigDecimal(bY * Random.nextDouble()).setScale(4, BigDecimal.RoundingMode.HALF_UP).toDouble
      Point(randX, randY)
    }.distinct
  }
}
