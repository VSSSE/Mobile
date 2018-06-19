package org.dhbw.movietunes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ InstrumentedTest.class, ExtractorTest.class, HttpCommunicatorTest.class,
        SpotifyCommunicationTest.class, YoutubeCommunicationTest.class,
        MovieCommunicationTest.class, SonstigeTest.class})
public class VssApplicationTests {

}