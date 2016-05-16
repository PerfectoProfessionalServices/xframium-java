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
package com.xframium.gesture;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Point;
import org.openqa.selenium.ScreenOrientation;
import com.xframium.gesture.Gesture.Direction;
import com.xframium.gesture.Gesture.GestureType;
import com.xframium.gesture.factory.GestureFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class GestureManager.
 */
public class GestureManager
{
	
	/** The singleton. */
	private static GestureManager singleton = new GestureManager();

	/**
	 * Instance.
	 *
	 * @return the gesture manager
	 */
	public static GestureManager instance()
	{
		return singleton;
	}

	/**
	 * Instantiates a new gesture manager.
	 */
	private GestureManager()
	{

	}
	
	/** The gesture factory. */
	private GestureFactory gestureFactory;
	
	/** The log. */
	private Log log = LogFactory.getLog( GestureManager.class );
	
	/**
	 * Sets the gesture factory.
	 *
	 * @param gestureFactory the new gesture factory
	 */
	public void setGestureFactory( GestureFactory gestureFactory )
	{
		this.gestureFactory = gestureFactory;
	}
	
	/**
	 * Creates the Hide Keyboard Gesture.
	 *
	 * @return the gesture
	 */
	public Gesture createHideKeyboard()
	{
		return gestureFactory.createGesture( GestureType.HIDE_KEYBOARD, new Object[] { false } ); 
	}
	
	/**
	 * Creates the swipe.
	 *
	 * @param swipeDirection the swipe direction
	 * @return the gesture
	 */
	public Gesture createSwipe( Direction swipeDirection )
	{
		switch( swipeDirection )
		{
			case DOWN:
				return createSwipe( new Point( 50, 15 ), new Point( 50, 85 ) );
				
			case LEFT:
				return createSwipe( new Point( 55, 50 ), new Point( 85, 50 ) );
				
			case RIGHT:
				return createSwipe( new Point( 85, 50 ), new Point( 15, 50 ) );
				
			case UP:
				return createSwipe( new Point( 50, 85 ), new Point( 50, 15 ) );
				
			default:
				return null;
		}
	}
	
	/**
	 * Creates the rotate.
	 *
	 * @param sOrientation the s orientation
	 * @return the gesture
	 */
	public Gesture createRotate( ScreenOrientation sOrientation )
	{
		return gestureFactory.createGesture( GestureType.ROTATE, new Object[] { sOrientation } );
	}
	
	/**
	 * Creates the swipe.
	 *
	 * @param startPosition the start position
	 * @param endPosition the end position
	 * @return the gesture
	 */
	public Gesture createSwipe( Point startPosition, Point endPosition )
	{
		return gestureFactory.createGesture( GestureType.SWIPE, new Object[] { startPosition, endPosition } );
	}
	
	/**
	 * Creates the press.
	 *
	 * @param pressPosition the press position
	 * @return the gesture
	 */
	public Gesture createPress( Point pressPosition )
	{
		return createPress( pressPosition, 100l );
	}
	
	/**
	 * Creates the press.
	 *
	 * @param pressPosition the press position
	 * @param pressLength the press length
	 * @return the gesture
	 */
	public Gesture createPress( Point pressPosition, long pressLength )
	{
		return gestureFactory.createGesture( GestureType.PRESS, new Object[] { pressPosition, pressLength } );
	}
	
	/**
	 * Creates the key press.
	 *
	 * @param keyCode the key code
	 * @param metaState the meta state
	 * @return the gesture
	 */
	public Gesture createKeyPress( int keyCode, int metaState )
	{
		return gestureFactory.createGesture( GestureType.KEYPRESS, new Object[] { keyCode, metaState } );
	}
	
	/**
	 * Creates the zoom.
	 *
	 * @return the gesture
	 */
	public Gesture createZoom()
	{
		return createZoom( new Point( 45, 45 ), new Point( 55, 55 ), new Point( 15, 15 ), new Point( 85, 85 ) );
	}
	
	/**
	 * Creates the zoom.
	 *
	 * @param startOne the start one
	 * @param startTwo the start two
	 * @param endOne the end one
	 * @param endTwo the end two
	 * @return the gesture
	 */
	public Gesture createZoom( Point startOne, Point startTwo, Point endOne, Point endTwo )
	{
		return gestureFactory.createGesture( GestureType.ZOOM, new Object[] { startOne, startTwo, endOne, endTwo } );
	}
	
	/**
	 * Creates the pinch.
	 *
	 * @return the gesture
	 */
	public Gesture createPinch()
	{
		return createPinch( new Point( 15, 15 ), new Point( 85, 85 ), new Point( 45, 45 ), new Point( 55, 55 ) );
	}
	
	/**
	 * Creates the pinch.
	 *
	 * @param startOne the start one
	 * @param startTwo the start two
	 * @param endOne the end one
	 * @param endTwo the end two
	 * @return the gesture
	 */
	public Gesture createPinch( Point startOne, Point startTwo, Point endOne, Point endTwo )
	{
		return gestureFactory.createGesture( GestureType.PINCH, new Object[] { startOne, startTwo, endOne, endTwo } );
	}
	
}
