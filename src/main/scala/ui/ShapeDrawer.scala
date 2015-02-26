package ui

import java.awt.{Color, Graphics2D}
import javax.swing.WindowConstants

import base.BoundingBox
import shape._

import scala.swing.Swing._
import scala.swing._

/**
 * Created by bruno on 2/19/15.
 */
class ShapeDrawer(shape: Shape) extends Frame {
  title = "[COMP471] Project 2"
  peer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  contents = new Panel {
    preferredSize = (800,600)
    opaque = true
    centerOnScreen
    listenTo(this)

    val b = BoundingBox(shape)
    val area = "Approximate Total Area (unit²): " + shape.getArea().toInt.toString
    println(area)

    override def paint(g: Graphics2D) = {
      val xOrigin: Int = size.width / 2
      val yOrigin: Int = size.height / 2
      g.translate(xOrigin,yOrigin)
      g.drawLine(0,-size.height,0,size.height)
      g.drawLine(-size.width,0,size.width,0)
      val draw = new Draw(g)
      g.setColor(Color.RED)
      draw(b)
      g.setColor(Color.BLACK)
      draw(shape)
      g.drawString(area, 5, yOrigin-10)
    }
  }
}
