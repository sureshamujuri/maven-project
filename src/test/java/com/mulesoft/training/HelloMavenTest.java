package com.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;

public class HelloMavenTest extends FunctionalTestCase {

	@Rule
	public DynamicPort myPort = new DynamicPort("http.port");
	
    @Test
    public void mavenFlowReturnsHelloMaven() throws Exception {
        runFlowAndExpect("mavenFlow", "Hello Maven");
        System.out.print("\n\nTestCase-1:  Dynamic HTTP Port used----------->" + myPort.getNumber() + "\n\n");
    }
    
    @Test
    public void retrieveFlightsAddsAppropriateHeader() throws Exception {
      System.out.print("\n\nTestCase-2:  Dynamic HTTP Port used----------->" + myPort.getNumber() + "\n\n");	
      MuleEvent event = runFlow("retrieveFlights");
      String contentType = event.getMessage().getOutboundProperty("Content-Type");
      assertEquals("application/json", contentType);
    }
    
    @Override
    protected String[] getConfigFiles() {
    	String[] files = {"maven-project.xml", "global.xml"};
        return files;
    }

}
