/*
 * Copyright 2002,2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.jelly.tags.core;

import org.apache.commons.jelly.JellyTagException;
import org.apache.commons.jelly.TagSupport;
import org.apache.commons.jelly.XMLOutput;
import org.apache.commons.jelly.expression.Expression;
import org.xml.sax.SAXException;

/** A tag which evaluates an expression
  *
  * @tag out
  * @author <a href="mailto:jstrachan@apache.org">James Strachan</a>
  * @version $Revision: 1.16 $
  */
public class ExprTag extends TagSupport {

    /** The expression to evaluate. */
    private Expression value;

    public ExprTag() {
    }

    // Tag interface
    //-------------------------------------------------------------------------
    public void doTag(XMLOutput output) throws JellyTagException {
        if (value != null) {
            String text = value.evaluateAsString(context);
            if (text != null) {

                try {
                    output.write(text);
                }
                catch (SAXException e) {
                    throw new JellyTagException("could not write the XMLOutput",e);
                }
            }
        }
    }

    // Properties
    //-------------------------------------------------------------------------

    /**
     * Sets the Jexl expression to evaluate.
     *
     * @required true
     */
    public void setValue(Expression value) {
        this.value = value;
    }
}
