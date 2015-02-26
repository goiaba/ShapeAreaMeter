#General

- The model classes are with 100% of test coverage. Due to time restrictions and unknowledge of how to test Swing modules, the ui package was not tested. Because of that, the coverage percentage drops to 82.67%, a value below the mininum required. Hoping that this will not be a problem. 
- Both extra credit tasks were attempted:
    - Simple Swing GUI:
      - The main method contains six possible shapes to be drawn into a Swing frame. When the user runs the main class, it shows a menu with the options. After choose one of them a new Swing Frame will be created. It is a simple example that does not includes input data handling.
      - SBT is throwing the following exception: "Exception: sbt.TrapExitSecurityException thrown from the UncaughtExceptionHandler in thread "AWT-EventQueue-0"". This seems to be linked with the java System.exit() call, but I have not found out how to solve this.
    - Scaling polygons and groups up or down by a factor:
      - All the options except 2 are using the scale functionality.

#Usage

To run the tests:

    $ sbt test

To run the tests with scoverage:

    $ sbt clean coverage test

To run the main class on a Unix-based system:

    $ sbt 'runMain ui.Main'

To run the main class on a Unix-based system selecting one of the options (1..6):

    $ echo <1..6> | sbt 'runMain ui.Main'

#References

https://bitbucket.org/goiaba/shapes-android-scala
https://cyberlotus.wordpress.com/2011/04/04/drawing-concentric-circles-using-scala-and-swing/
http://www.ahristov.com/tutorial/geometry-games/intersection-lines.html
http://devmag.org.za/2009/04/13/basic-collision-detection-in-2d-part-1/
http://www.cap.ufrj.br/matematica/PortaldoProfessorMec/atividades/Aula_translacao.pdf
http://www.euclideanspace.com/maths/algebra/vectors/angleBetween/
http://www.euclideanspace.com/maths/algebra/vectors/angleBetween/issues/index.htm
https://cyberlotus.wordpress.com/2011/04/04/drawing-concentric-circles-using-scala-and-swing/
https://github.com/scala/scala-swing/blob/master/docs/examples/swing/UIDemo.scala
