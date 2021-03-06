package annotation_example;

/*
Description:
Create an Annotation called @TestMethod.
It should be applicable to Methods, and be available at Runtime.

Create a Class called TestMyService.  Create one or more methods in
this class with the form "public boolean testXXX() {...}".  Annotate
these test methods with your new @TestMethod annotation.

The class under test is the MyService class.  This is the class that you
have to write your testMethods for.

Now Create an Application class called UnitTester.  This class will run the
test methods in the TestMyService class.  This class should load
in the TestMyService class into the VM.  It should then search for Methods in the
class which are annotated with the @TestMethod annotation.  It should
further filter the methods by making sure that any annotated methods
also take no arguments, and return a boolean.

The UnitTester should run all test methods that meet the specified criteria.
It should record the results.  After all tests have run, it should print
a report of the test results.

Duration 30-45 minutes
*/

public class TestMyService {

    @TestMethod
    public boolean testGetSquare() {
        MyService srv = new MyService();
        return srv.getSquare(2) == 4;
    }

    @TestMethod
    public boolean testGetCube() {
        MyService srv = new MyService();
        return srv.getCube(2) == 8;

    }


}
