/*
 * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *
 *
 *
 *
 *
 */
/*
   Copyright 2016 Attila Szegedi

   Licensed under both the Apache License, Version 2.0 (the "Apache License")
   and the BSD License (the "BSD License"), with licensee being free to
   choose either of the two at their discretion.

   You may not use this file except in compliance with either the Apache
   License or the BSD License.

   If you choose to use this file in compliance with the Apache License, the
   following notice applies to you:

       You may obtain a copy of the Apache License at

           http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
       implied. See the License for the specific language governing
       permissions and limitations under the License.

   If you choose to use this file in compliance with the BSD License, the
   following notice applies to you:

       Redistribution and use in source and binary forms, with or without
       modification, are permitted provided that the following conditions are
       met:
       * Redistributions of source code must retain the above copyright
         notice, this list of conditions and the following disclaimer.
       * Redistributions in binary form must reproduce the above copyright
         notice, this list of conditions and the following disclaimer in the
         documentation and/or other materials provided with the distribution.
       * Neither the name of the copyright holder nor the names of
         contributors may be used to endorse or promote products derived from
         this software without specific prior written permission.

       THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
       IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
       TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
       PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL COPYRIGHT HOLDER
       BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
       CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
       SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
       BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
       WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
       OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
       ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package jdk.dynalink;

/**
 * An enumeration of standard namespaces defined by Dynalink.
 */
public enum StandardNamespace implements Namespace {
    /**
     * Standard namespace for properties of an object.
     */
    PROPERTY,
    /**
     * Standard namespace for elements of a collection object.
     */
    ELEMENT,
    /**
     * Standard namespace for methods of an object. The method objects retrieved
     * through a {@link StandardOperation#GET} on this namespace can be (and where
     * object semantics allows they should be) unbound, that is: not bound to the
     * object they were retrieved through. When they are used with
     * {@link StandardOperation#CALL} an explicit "this" receiver argument is always
     * passed to them. Of course bound methods can be returned if the object semantics
     * requires them and such methods are free to ignore the receiver passed in the
     * {@code CALL} operation or even raise an error when it is different from the one
     * the method is bound to, or exhibit any other behavior their semantics requires
     * in such case.
     */
    METHOD;

    /**
     * If the passed in operation is a {@link NamespaceOperation}, or a
     * {@link NamedOperation} wrapping a {@link NamespaceOperation}, then it
     * returns the first (if any) {@link StandardNamespace} in its namespace
     * list. If the passed operation is not a namespace operation (optionally
     * wrapped in a named operation), or if it doesn't have any standard
     * namespaces in it, returns {@code null}.
     * @param op the operation
     * @return the first standard namespace in the operation's namespace list
     */
    public static StandardNamespace findFirst(final Operation op) {
        for(final Namespace ns: NamespaceOperation.getNamespaces(NamedOperation.getBaseOperation(op))) {
            if (ns instanceof StandardNamespace) {
                return (StandardNamespace)ns;
            }
        }
        return null;
    }
}
