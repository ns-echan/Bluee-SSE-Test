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

/**
 * A "null" quarantiner that does not acutally do any quaranting work.
 * 
 * This is just a stub.
 */
public class StubQuarantiner implements Quarantiner
{
	public QuarantineResult quarantine(QuarantineData data)
	{
		QuarantineResult result = new QuarantineResult();
		result.quarantinedFileLocation 
			= data.quarantineFolderPath + "//" + data.scanFilePath;

		return result;
	}
}
