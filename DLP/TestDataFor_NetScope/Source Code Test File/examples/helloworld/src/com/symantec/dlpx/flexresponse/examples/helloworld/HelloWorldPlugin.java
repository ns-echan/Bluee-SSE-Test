//////////////////////////////////////////////////////////////////////////////////
// Symantec copyright header start
//////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2015 Symantec Corporation. All rights reserved.
//
// THIS SOFTWARE CONTAINS CONFIDENTIAL INFORMATION AND TRADE SECRETS OF SYMANTEC
// CORPORATION.  USE, DISCLOSURE OR REPRODUCTION IS PROHIBITED WITHOUT THE PRIOR
// EXPRESS WRITTEN PERMISSION OF SYMANTEC CORPORATION.
//
// The Licensed Software and Documentation are deemed to be commercial computer
// software as defined in FAR 12.212 and subject to restricted rights as defined
// in FAR Section 52.227-19 "Commercial Computer Software - Restricted Rights"
// and DFARS 227.7202, "Rights in Commercial Computer Software or Commercial
// Computer Software Documentation", as applicable, and any successor
// regulations.  Any use, modification, reproduction release, performance,
// display or disclosure of the Licensed Software and Documentation by the U.S.
// Government shall be solely in accordance with the terms of this Agreement.
//
//////////////////////////////////////////////////////////////////////////////////
// Symantec copyright header stop
//////////////////////////////////////////////////////////////////////////////////
package com.symantec.dlpx.flexresponse.examples.helloworld;

import static java.util.Collections.emptyMap; 

import java.util.Map;

import com.symantec.dlpx.flexresponse.DefaultPluginMetadata;
import com.symantec.dlpx.flexresponse.Plugin;
import com.symantec.dlpx.flexresponse.PluginMetadata;
import com.symantec.dlpx.flexresponse.action.IncidentResponseAction;

/**
 * An example of an extremely simple FlexResponse plugin.
 *
 * This plugin provides an action that simply returns the action result message
 *  "Hello World! This item has been remediated." for every incident.
 */
public class HelloWorldPlugin implements Plugin
{
	public PluginMetadata getMetadata()
	{
		Map<String, Object> attributes = emptyMap();
		
		return new DefaultPluginMetadata("<Company>", "3.0", attributes);
	}

	public IncidentResponseAction newIncidentResponseAction()
	{
		return new HelloWorldAction();
	}
}
