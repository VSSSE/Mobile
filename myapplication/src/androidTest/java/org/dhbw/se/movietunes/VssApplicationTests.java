package org.dhbw.se.movietunes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ExampleInstrumentedTest.class,ExtractorTest.class,HttpCommunicatorTest.class, SpotifyCommunicationTest.class})
public class VssApplicationTests{

}