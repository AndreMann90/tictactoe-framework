package testUtil;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import aiStagesTest.RandomPlayerTest;
import aiStagesTest.ThreeInARowPlayerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   RandomPlayerTest.class,
   ThreeInARowPlayerTest.class
})
public class PlayerTestSuite {

}
