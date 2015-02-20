package ui

import java.awt.{Color, Graphics2D}

import base.{BoundingBox, Point}
import shape.{Location, Polygon, Rectangle, Shape}

import scala.swing.Swing._
import scala.swing._
import scala.swing.event._

/**
 * Font: https://cyberlotus.wordpress.com/2011/04/04/drawing-concentric-circles-using-scala-and-swing/
 */

/**
 * Created by bruno on 2/19/15.
 */
class ShapeDrawer(shape: Shape) extends Frame {
  title = "[COMP471] Project 2"

  val xStart = 9
  val yStart = 14
  contents = new Panel {
    val (minX, minY, maxX, maxY) = BoundingBox(shape).getExtremePoints()
    preferredSize = (if (maxX.toInt < 250) 250 else maxX.toInt, maxY.toInt + 80)
    opaque = true

    override def paint(g: Graphics2D) = {
      g.drawString("0",0,26)
      g.drawLine(xStart,0,xStart,size.getHeight.toInt)
      g.drawString("0",12,12)
      g.drawLine(0,yStart,size.getWidth.toInt,yStart)
      g.translate(xStart,yStart)

      val b @ Location(point, Rectangle(p1,p2,p3,p4)) = BoundingBox(shape)
      g.setBackground(Color.WHITE)
      val draw = new Draw(g)
      g.setColor(Color.RED)
      draw(b)
      g.setColor(Color.BLACK)
      val area = "Area of the Polygon = " + shape.getArea().toInt.toString
      g.drawString(area, xStart, size.getHeight.toInt - 20)
      draw(shape)
    }

    centerOnScreen
    listenTo(this)
    reactions += {
      case WindowClosing(e) => {
        println("Exiting...")
        System.exit(0)
      }
    }
  }
}

object drawer extends SimpleSwingApplication {
  val p1 = Point(20, 10)
  val p2 = Point(40, 30)
  val p3 = Point(20, 50)
  val p4 = Point(50, 70)
  val p5 = Point(80, 50)
  val p6 = Point(80, 30)
  val p7 = Point(60, 20)
  val p8 = Point(60, 0)
  val p9 = Point(40, 20)

  val polygon = Location(Point(0,0),Polygon(p1, p2, p3, p4, p5, p6, p7, p8, p9))
//  val polygon = Location(Point(0,0),Polygon(Point(10,10), Point(90,120), Point(60,200), Point(60,180)))
  def top = new ShapeDrawer(polygon)
}
