package base

import shape.{Group, Polygon}

/**
 * Created by bruno on 2/7/15.
 */
object Fixtures {

  def isGoodEnough(conceptualArea: Double, computedArea: Double): Boolean =
    math.abs(computedArea - conceptualArea) / conceptualArea <= epsilon

  //max acceptable error is 1% of discrepancy
  // between real and calculated value.
  val epsilon = 0.01

  //points
  val A = Point(1,2)
  val B = Point(5,9)

  val C = Point(-8,8)
  val D = Point(8,8)
  val E = Point(8,-8)
  val F = Point(-8,-8)

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
  val S = Point(-4,6)
  val T = Point(-3,3)
  val U = Point(1,3)
  val V = Point(-4,-4)
  val W = Point(-6,3)

  //simple polygon points
  val X = Point(0,0)
  val Y = Point(2,2)
  val Z = Point(2,0)

  //parallel horizontal
  val CD = LineSegment(C, D)
  val EF = LineSegment(E, F)

  //parallel vertical
  val CF = LineSegment(C, F)
  val DE = LineSegment(D, E)

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
  val simplePolygon = Polygon(X, Y, Z)
  val complexPolygon = Polygon(S, T, U, V, W)

  val simpleGroup = Group(simplePolygon)
  val complexGroup = Group(simplePolygon, Group(complexPolygon))
  val moreComplexGroup = Group(simplePolygon, Group(Polygon(C, D, E, F), Group(complexPolygon)))

  val locationPoint = Point(10,10)

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
