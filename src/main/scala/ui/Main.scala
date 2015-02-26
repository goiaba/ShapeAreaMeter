package ui

import base.Point
import shape.{Shape, Group, Location, Polygon}

import scala.swing.{Frame, SimpleSwingApplication}

/**
 * Created by bruno on 2/20/15.
 */
object Main extends SimpleSwingApplication {

  val p1 = Point(20, 10)
  val p2 = Point(40, 30)
  val p3 = Point(20, 50)
  val p4 = Point(50, 70)
  val p5 = Point(80, 50)
  val p6 = Point(80, 30)
  val p7 = Point(60, 20)
  val p8 = Point(60, 0)
  val p9 = Point(40, 20)
  val A = Point(0,0)
  val B = Point(2,2)
  val C = Point(2,0)
  val D = Point(-4,6)
  val E = Point(-3,3)
  val F = Point(1,3)
  val G = Point(-4,-4)
  val H = Point(-6,3)
  val I = Point(-8,8)
  val J = Point(8,8)
  val K = Point(8,-8)
  val L = Point(-8,-8)
  val M = Point(-4,-2)
  val N = Point(9,12)
  val O = Point(6,20)
  val P = Point(6,18)
  val Q = Point(20,20)

  val simplePolygon = Polygon(A, B, C).scale(10)
  val complexPolygon = Polygon(D, E, F, G, H).scale(10)
  val complexPolygon2 = Group(Polygon(p1, p2, p3, p4, p5, p6, p7, p8, p9)).scale(2)
  val anotherPolygon = Polygon(I, J, K, L)
  val polygon = Location(Q,Polygon(M, N, O, P)).scale(10)
  val complexGroup = Group(simplePolygon, Group(anotherPolygon, Group(complexPolygon, polygon)))

  println("\nPolygon area calculation using Monte Carlo method.")
  println("\nChoose one of the examples to run:")
  println("(1) Simple Polygon (triangle)")
  println("(2) Simple Polygon (square)")
  println("(3) Complex Polygon")
  println("(4) Another Complex Polygon")
  println("(5) Location")
  println("(6) Complex Group of Polygons\n")
  print("Type the number of the option: ")

  val shapeToDraw = scala.io.StdIn.readInt() match {
    case 1 => simplePolygon
    case 2 => anotherPolygon
    case 3 => complexPolygon
    case 4 => complexPolygon2
    case 5 => polygon
    case 6 => complexGroup
  }
  println()

  override def top: Frame = new ShapeDrawer(shapeToDraw)

}
