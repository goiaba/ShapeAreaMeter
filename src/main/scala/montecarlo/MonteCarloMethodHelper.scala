package montecarlo

import base.{BoundingBox, Point}
import polygon.Shape

import scala.collection.immutable.IndexedSeq
import scala.util.Random

/**
 * Created by bruno on 2/9/15.
 */
object MonteCarloMethodHelper {

  val shooterMultiplier = 1e+7

  def calculateArea(shape: Shape): Double = {
    val (bb, bbArea) = BoundingBox(shape)
    val randomPoints = randomPointsShooter(bb, bbArea)
    val totalRandomPoints: Double = randomPoints.size
    val totalInnerRandomPoints = randomPoints.filter { point =>
      val contained = shape.contain(point)
      contained.isDefined && contained.get == true
    }.size

    (totalInnerRandomPoints / totalRandomPoints) * bbArea
  }
  
  private def randomPointsShooter(boundingBox: Shape, area: Double): IndexedSeq[Point] = {
    val (minX, minY, maxX, maxY) = BoundingBox.boundaries(boundingBox)
    val shoots = (area * shooterMultiplier).toInt

    (1 to shoots).map {index =>
      val randX = minX + (maxX - minX) * Random.nextDouble()
      val randY = minY + (maxY - minY) * Random.nextDouble()
      Point(randX, randY)
    }
  }
}
