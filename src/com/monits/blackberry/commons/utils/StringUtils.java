/*
 * Copyright 2012 Monits
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.monits.blackberry.commons.utils;

import java.util.Vector;

import net.rim.device.api.ui.Font;

/**
 * Utility class to manage strings.
 * @author Rodrigo Pereyra
 *
 */
public class StringUtils {

	/**
	 * This method wrap a string in function of the given width
	 * @param text Text to wrap
	 * @param font Font object
	 * @param width Available width
	 * @return A vector containing the wrapped text.
	 */
	public Vector wrap(String text, Font font, int width) {
		Vector result = new Vector();
		if (text == null) {
			return result;
		}

		boolean hasMore = true;

		int current = 0; // The current index of the cursor
		int lineBreak = -1; // The next line break index
		int nextSpace = -1; // The space after line break

		while (hasMore) {
			//Find the line break
			while (true) {
				lineBreak = nextSpace;

				if (lineBreak == text.length() - 1) {

					// We have reached the last line
					hasMore = false;
					break;
				} else {

					nextSpace = text.indexOf(' ', lineBreak + 1);

					if (nextSpace == -1) {
						nextSpace = text.length() - 1;
					}
					int linewidth = font.getAdvance(text, current, nextSpace - current);

					// If too long, break out of the find loop
					if (linewidth > width) {
						break;
					}
				}
			}
			
			String line = text.substring(current, lineBreak + 1);
			result.addElement(line);
			current = lineBreak + 1;
		}
		
		return result;
	}

	/**
	 * Splits this string around matches of the given char.
	 * @param string String to split.
	 * @param splitChar Delimiting char.
	 * @return Vector containing the expressions that matches with the char.
	 */
	public Vector split(String string, char splitChar) {
		Vector v = new Vector();

		String working = string;
		int index = working.indexOf(splitChar);

		// Work with the string until there's no more tokens.
		while (index != -1) {
			String tmp = "";
			if (index > 0) {
				tmp = working.substring(0, index);
			}
			v.addElement(tmp);

			working = working.substring(index + 1);

			// Find the next index
			index = working.indexOf(splitChar);
		}

		// Add the rest of the working string
		v.addElement(working);

		return v;
	}
}