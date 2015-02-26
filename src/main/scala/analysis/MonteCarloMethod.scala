package analysis

import base.{BoundingBox, Point}
import shape.Shape

import scala.collection.immutable.IndexedSeq
import scala.util.Random

trait AnalysisMethod {
  def calculateArea(shape: Shape): Double
}

/**
 * Created by bruno on 2/9/15.
 */
object MonteCarloMethod extends AnalysisMethod {

  val shooterMultiplier = 1e+5

  def calculateArea(shape: Shape): Double = {
    val bb = BoundingBox(shape)
    val randomPoints = randomPointsShooter(bb)
    val totalRandomPoints: Double = randomPoints.size
    val totalInnerRandomPoints =
      randomPoints.filter(shape.contain(_)).size

    (totalInnerRandomPoints / totalRandomPoints) * bb.getArea()
  }
  
  private def randomPointsShooter(boundingBox: Shape): IndexedSeq[Point] = {
    val (minX, minY, maxX, maxY) = boundingBox.getExtremePoints()
    (1 to shooterMultiplier.toInt).map { index =>
      val randX = BigDecimal(minX + ((maxX - minX) * Random.nextDouble())).setScale(4, BigDecimal.RoundingMode.HALF_UP).toDouble
      val randY = BigDecimal(minY + ((maxY - minY) * Random.nextDouble())).setScale(4, BigDecimal.RoundingMode.HALF_UP).toDouble
      Point(randX, randY)
    }.distinct
  }
}
