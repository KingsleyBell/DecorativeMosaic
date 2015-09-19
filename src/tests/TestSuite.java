package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//JUnit Suite Test
@RunWith(Suite.class)
@Suite.SuiteClasses({ 
   EdgeCurveTest.class,
   DirectionFieldTest.class
})
public class TestSuite {
}