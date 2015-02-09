import base.{BoundingBox, Ray, LineSegment, Point}
import polygon.Polygon

//http://www.ahristov.com/tutorial/geometry-games/intersection-lines.html
//http://devmag.org.za/2009/04/13/basic-collision-detection-in-2d-part-1/
val point = Point(1.5,1)
val points = List(Point(0,0), Point(2,2), Point(2,0))
val ray = new Ray(point, Point(point.x+1, point.y))
val sides = points.:+(points.head).sliding(2).map(x =>
  LineSegment(x.head,x.last))

val p = Polygon(points:_*)
//
//val ps = p.sides().map(s => Point(s.p1.x, s.p1.y)).toSeq
//ps.minBy(_.x).x
//ps.maxBy(_.x).x
//ps.minBy(_.y).y
//ps.maxBy(_.y).y



p.getArea()
/*
Horas Trabalhadas
02/06 -> 15h05 : 20h15
02/06 -> 23h40 : 1h30
02/07 -> 10h40 : 11h30
02/07 -> 12h30 :
 */
