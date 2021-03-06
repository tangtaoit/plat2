/*
 * Copyright 2009 Michael Würtinger (mwuertinger@users.sourceforge.net)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tt.plat.comp.charts;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Locale;

public class DataSet implements Serializable
{
	/** Required by {@link java.io.Serializable} */
	private static final long serialVersionUID = 1L;

	private String x;
	private String y;

	public DataSet(String x, String y)
	{
		this.x = x;
		this.y = y;
	}

//	public DataSet(DateTime time, double value)
//	{
//		x = time.getMillis();
//		y = value;
//	}


    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    @Override
	public String toString()
	{
		return String.format(Locale.US, "[%s, %s]", x, y);
	}
}
