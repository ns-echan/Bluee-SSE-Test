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
package com.symantec.dlpx.flexresponse.examples.quarantine;

import static java.util.Collections.emptyMap;

import java.util.Map;

import com.symantec.dlpx.flexresponse.DefaultPluginMetadata;
import com.symantec.dlpx.flexresponse.Plugin;
import com.symantec.dlpx.flexresponse.PluginMetadata;
import com.symantec.dlpx.flexresponse.action.IncidentResponseAction;

/**
 * An example of a simple FlexResponse plugin for quarantining files.
 *
 * This plugin provides an action that gets some basic information about the 
 * incident and then updates its custom attributes and remediation location.  
 * The code for doing the actual quarantining work is not provided to keep this 
 * example simple.
 */
public class QuarantinePlugin implements Plugin
{
	@Override
	public PluginMetadata getMetadata()
	{
		Map<String, Object> attributes = emptyMap();
	
		return new DefaultPluginMetadata("<Company>", "3.0", attributes);
	}

	@Override
	public IncidentResponseAction newIncidentResponseAction()
	{
		return new QuarantineAction(new StubQuarantiner());
	}
}
