package base

import polygon.Polygon

/**
 * Created by bruno on 2/7/15.
 */
object Fixtures {

  //points
  val A = Point(1,2)
  val B = Point(5,9)

  val C = Point(-8,8)
  val D = Point(8,8)
  val E = Point(-8,-8)
  val F = Point(8,-8)

  val G = Point(-9,1)
  val H = Point(1,-9)
  val I = Point(-1,9)
  val J = Point(9,-1)
  val K = Point(1,9)
  val L = Point(-9,-1)
  val M = Point(9,1)
  val N = Point(-1,-9)

  val O = Point(-6,5)
  val P = Point(0,5)
  val Q = Point(2,-2)
  val R = Point(3,-2)

  //complex polygon points
  val S = Point(-10,3)
  val T = Point(-6,5)
  val U = Point(-3,9)
  val V = Point(3,11)
  val W = Point(7,8)
  val X = Point(4,-3)
  val Z = Point(-5,-2)

  //parallel horizontal
  val CD = LineSegment(C, D)
  val EF = LineSegment(E, F)

  //parallel vertical
  val CE = LineSegment(C, E)
  val DF = LineSegment(D, F)

  //upward slanted
  val GH = LineSegment(G, H)
  val IJ = LineSegment(I, J)

  //downward slanted
  val KL = LineSegment(K, L)
  val MN = LineSegment(M, N)

  //parallel horizontal rays
  val OP = Ray(LineSegment(O, P))
  val QR = Ray(LineSegment(Q, R))

  //polygons
  val simplePolygon = Polygon(Point(0,0), Point(2,2), Point(2,0))
  val complexPolygon = Polygon(S, T, U, V, W, X, Z)

  /**
   *  CD intersects CE, DF, KL, IJ
   *  EF intersects CE, DF, MN, GH
   *
   *  CE intersects CD, EF, KL, GH
   *  DF intersects CD, EF, MN, IJ
   *
   *  GH intersects CE, EF, KL, MN
   *  IJ intersects DF, CD, KL, MN
   *
   *  KL intersects CE, CD, GH, IJ
   *  MN intersects DF, EF, GH, IJ
   */

}
