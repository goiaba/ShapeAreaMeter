import base.{Constants, LineSegment, Point}
import shape.{Polygon, Location}
//val polygon =
//  Location(Point(0,0),Polygon(Point(10,10), Point(90,120), Point(60,200), Point(60,180)))
//polygon.getArea()

val conceptual = 162
val computed = Location(Point(10,10),
  Polygon(Point(0,0), Point(2,2), Point(2,0))).scale(3).scale(3).getArea()

val epsilon = math.abs(computed - conceptual) / conceptual


//http://www.ahristov.com/tutorial/geometry-games/intersection-lines.html
//http://devmag.org.za/2009/04/13/basic-collision-detection-in-2d-part-1/
//http://www.cap.ufrj.br/matematica/PortaldoProfessorMec/atividades/Aula_translacao.pdf
//http://www.euclideanspace.com/maths/algebra/vectors/angleBetween/
//http://www.euclideanspace.com/maths/algebra/vectors/angleBetween/issues/index.htm
//https://cyberlotus.wordpress.com/2011/04/04/drawing-concentric-circles-using-scala-and-swing/
//https://github.com/scala/scala-swing/blob/master/docs/examples/swing/UIDemo.scala

//1e+5 => 1.9983141781618539
//1e+4
//=> 1.9892747446248662
//=> 1.997323789014529
//=> 1.998894466580125
//=> 1.9967032609701618
//=> 1.9927665279334081
//=> 1.9971234538564477
//=> 1.9957682226535145
//=> 1.9937469047178353
//=> 1.9963829292269824
//=> 2.0002301196622243
//=> 1.9965233398947997
//=> 1.9956278357787105
//=> 1.9973086004722456
//=> 2.000450207095264
//=> 2.0039619611707797
//=> 1.996583231822468
//=> 1.9929367155727413
//=> 1.9967383691845924
//=> 1.997143436031227
//=> 1.9985191929601633
//=> 2.0015357794080497


/*
Horas Trabalhadas
02/06 -> 15h05 : 20h15 = 5h10min
02/06 -> 23h40 : 01h30 = 1h50min
02/07 -> 10h40 : 11h30 = 0h50min
02/07 -> 12h30 : 20h00 = 7h30min
02/07 -> 22h00 : 02:00 = 4h00min
02/08 -> 10h30 : 13:00 = 2h30min
02/08 -> 19h00 : 01h30 = 6h30min
02/09 -> 23h30 :
--------------------------------
Total                   28h20min
 */
//val ls1 = LineSegment(Point(20,10), Point(40,30))
//val ls2 = LineSegment(Point(40,30), Point(20,50))
//val ls3 = LineSegment(Point(20,50), Point(50,70))
//val ls4 = LineSegment(Point(50,70), Point(80,50))
//val ls5 = LineSegment(Point(80,50), Point(80,30))
//val ls6 = LineSegment(Point(80,30), Point(60,20))
//val ls7 = LineSegment(Point(60,20), Point(60,0))
//val ls8 = LineSegment(Point(60,0), Point(40,20))
//val ls9 = LineSegment(Point(40,20), Point(20,10))
