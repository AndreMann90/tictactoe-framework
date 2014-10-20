package testUtil;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import otherTests.TestDriverTest;
import playerTest.RandomPlayerTest;
import playerTest.ThreeInARowPlayerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   RandomPlayerTest.class,
   ThreeInARowPlayerTest.class,
   TestDriverTest.class
})
public class PlayerTestSuite {

}
