/*
 * $Header: /l/extreme/cvs/codes/XPP3/java/src/java/xpath/org/xmlpull/v1/builder/xpath/saxpath/com/werken/saxpath/TokenTypes.java,v 1.1 2004/06/16 15:55:43 aslom Exp $
 * $Revision: 1.1 $
 * $Date: 2004/06/16 15:55:43 $
 *
 * ====================================================================
 *
 * Copyright (C) 2000-2002 werken digital.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions, and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions, and the disclaimer that follows 
 *    these conditions in the documentation and/or other materials 
 *    provided with the distribution.
 *
 * 3. The name "SAXPath" must not be used to endorse or promote products
 *    derived from this software without prior written permission.  For
 *    written permission, please contact license@saxpath.org.
 * 
 * 4. Products derived from this software may not be called "SAXPath", nor
 *    may "SAXPath" appear in their name, without prior written permission
 *    from the SAXPath Project Management (pm@saxpath.org).
 * 
 * In addition, we request (but do not require) that you include in the 
 * end-user documentation provided with the redistribution and/or in the 
 * software itself an acknowledgement equivalent to the following:
 *     "This product includes software developed by the
 *      SAXPath Project (http://www.saxpath.org/)."
 * Alternatively, the acknowledgment may be graphical using the logos 
 * available at http://www.saxpath.org/
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE SAXPath AUTHORS OR THE PROJECT
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * ====================================================================
 * This software consists of voluntary contributions made by many 
 * individuals on behalf of the SAXPath Project and was originally 
 * created by bob mcwhirter <bob@werken.com> and 
 * James Strachan <jstrachan@apache.org>.  For more information on the 
 * SAXPath Project, please see <http://www.saxpath.org/>.
 * 
 * $Id: TokenTypes.java,v 1.1 2004/06/16 15:55:43 aslom Exp $
 */



package org.xmlpull.v1.builder.xpath.saxpath.com.werken.saxpath;

interface TokenTypes
{
    static final int LEFT_PAREN  = 1;
    static final int RIGHT_PAREN = 2;

    static final int LEFT_BRACKET  = 3;
    static final int RIGHT_BRACKET = 4;

    static final int PLUS = 5;
    static final int MINUS = 6;
    static final int LESS_THAN = 7;
    static final int LESS_THAN_EQUALS = 8;
    static final int GREATER_THAN = 9;
    static final int GREATER_THAN_EQUALS = 10;

    static final int SLASH = 11;
    static final int DOUBLE_SLASH = 12;
    static final int DOT = 13;
    static final int DOT_DOT = 14;

    static final int IDENTIFIER = 15;

    static final int AT = 16;
    static final int PIPE = 17;
    static final int COLON = 18;
    static final int DOUBLE_COLON = 19;
    static final int STAR = 20;

    static final int EQUALS = 21;
    static final int NOT_EQUALS = 22;
    static final int NOT = 23;

    static final int DIV = 24;
    static final int MOD = 25;

    static final int DOLLAR = 26;

    static final int LITERAL = 27;

    static final int AND = 28;
    static final int OR = 29;

    static final int INTEGER = 30;
    static final int DOUBLE = 31;

    static final int COMMA = 32;

    //static final int SKIP = 99;
    //static final int EOF = 100;
    static final int SKIP = -2;
    static final int EOF = -1;
}
