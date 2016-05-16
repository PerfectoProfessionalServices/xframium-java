/*******************************************************************************
 * xFramium
 *
 * Copyright 2016 by Moreland Labs LTD (http://www.morelandlabs.com)
 *
 * Some open source application is free software: you can redistribute 
 * it and/or modify it under the terms of the GNU General Public 
 * License as published by the Free Software Foundation, either 
 * version 3 of the License, or (at your option) any later version.
 *  
 * Some open source application is distributed in the hope that it will 
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *  
 * You should have received a copy of the GNU General Public License
 * along with xFramium.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPL-3.0+ <http://spdx.org/licenses/GPL-3.0+>
 *******************************************************************************/
/*
 * 
 */
package com.xframium.device.data;

// TODO: Auto-generated Javadoc
/**
 * The Interface DataProvider.
 */
public interface DataProvider
{
	/**
	 * The Enum DriverType.
	 */
	public enum DriverType
	{
		
		/** The web. */
		WEB,
		
		/** The perfecto. */
		PERFECTO,
		
		/** The appium. */
		APPIUM;
	}
	/**
	 * Read data.
	 */
	public void readData();
}
