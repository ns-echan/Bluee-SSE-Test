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

import java.util.ResourceBundle; 
import java.text.MessageFormat;

public class LocalizedMessageBuilder
{
	/**
	 * A helper method to get a message string out of a resource bundle.  
	 * This demonstrates a "best practice" for internationalizing strings so 
	 * they can be localized.
	 */
	public String getLocalizedString(String key, String ... arguments)
	{
		ResourceBundle bundle = getBundle();
		String message = bundle.getString(key);
		return MessageFormat.format(message, arguments);
	}

	private ResourceBundle getBundle()
	{
		return ResourceBundle.getBundle(getPackageName() + ".Bundle");
	}

	private String getPackageName()
	{
		return this.getClass().getPackage().getName();
	}
}
