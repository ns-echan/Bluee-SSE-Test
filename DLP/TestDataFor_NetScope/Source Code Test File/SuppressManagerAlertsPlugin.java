package com.symantec.dlpx.flexresponse.examples.suppressmanageralerts;

import static java.util.Collections.emptyMap; 

import java.util.Map;

import com.symantec.dlpx.flexresponse.DefaultPluginMetadata;
import com.symantec.dlpx.flexresponse.Plugin;
import com.symantec.dlpx.flexresponse.PluginMetadata;
import com.symantec.dlpx.flexresponse.action.IncidentResponseAction;

public class SuppressManagerAlertsPlugin implements Plugin{
	public PluginMetadata getMetadata(){
		Map<String, Object> attributes = emptyMap();
		
		return new DefaultPluginMetadata("<Company>", "3.0", attributes);
	}

	public IncidentResponseAction newIncidentResponseAction(){
		return new SuppressManagerAlertsAction();
	}
}
