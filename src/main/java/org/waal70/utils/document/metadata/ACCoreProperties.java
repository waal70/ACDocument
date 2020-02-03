/**
 * 
 */
package org.waal70.utils.document.metadata;

import org.waal70.utils.document.Archive;

/**
 * @author awaal
 *
 */
public interface ACCoreProperties {


/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Contains a core set of basic metadata properties, which all parsers
 *  will attempt to supply (where the file format permits). These are all
 *  defined in terms of other standard namespaces.
 *  
 * Users who wish to have consistent metadata across file formats
 *  can make use of these Properties, knowing that where present they will
 *  have consistent semantic meaning between different file formats. (No 
 *  matter if one file format calls it Title, another Long-Title and another
 *  Long-Name, if they all mean the same thing as defined by 
 *  {@link DublinCore#TITLE} then they will all be present as such)
 *
 */
    /**
     * A file might contain different types of embedded documents.
     * The most common is the ATTACHMENT.
     * <p>
     * An INLINE embedded resource should be used for embedded image
     * files that are used to render the page image (as in PDXObjImages in PDF files).
     * <p>
     * A MACRO is code that is embedded in the document and is intended
     * to be executable within the application that opens the document.  This
     * includes traditional macros within Microsoft Office files and
     * javascript within PDFActions.  This would not include, e.g., an
     * .exe file embedded in a .zip file.
     * <p>
     * Not all parsers have yet implemented this.
     *
     */
    public enum EmbeddedResourceType {
        INLINE,
        ATTACHMENT,
        MACRO
    };

    /**
     * The common delimiter used between the namespace abbreviation and the property name
     */
    String NAMESPACE_PREFIX_DELIMITER = ":";

    /**
     * Use this to prefix metadata properties that store information
     * about the parsing process.  Users should be able to distinguish
     * between metadata that was contained within the document and
     * metadata about the parsing process.
     * In AC 2.0 (or earlier?), let's change X-ParsedBy to X-AC-Parsed-By.
     */
    public static String AC_META_PREFIX = "X-ARCH"+NAMESPACE_PREFIX_DELIMITER;

    /**
     * Use this to store parse exception information in the Metadata object.
     */
    public static String AC_META_EXCEPTION_PREFIX = AC_META_PREFIX+"EXCEPTION"+
            NAMESPACE_PREFIX_DELIMITER;

    /**
     * Use this to store exceptions caught during a parse that are
     * non-fatal, e.g. if a parser is in lenient mode and more
     * content can be extracted if we ignore an exception thrown by
     * a dependency.
     */
    public static final Property AC_META_EXCEPTION_WARNING =
            Property.internalTextBag(AC_META_EXCEPTION_PREFIX+"warn");

    /**
     * Use this to store exceptions caught while trying to read the
     * stream of an embedded resource.  Do not use this if there is
     * a parse exception on the embedded resource.
     */
    Property AC_META_EXCEPTION_EMBEDDED_STREAM =
            Property.internalTextBag(AC_META_EXCEPTION_PREFIX+"embedded_stream_exception");


    String RESOURCE_NAME_KEY = "resourceName";

    String PROTECTED = "protected";

    String EMBEDDED_RELATIONSHIP_ID = "embeddedRelationshipId";

    String EMBEDDED_STORAGE_CLASS_ID = "embeddedStorageClassId";

    String EMBEDDED_RESOURCE_TYPE_KEY = "embeddedResourceType";

    /**
     * Some file formats can store information about their original
     * file name/location or about their attachment's original file name/location.
     */
    public static final Property ORIGINAL_RESOURCE_NAME =
            Property.internalTextBag(AC_META_PREFIX+"origResourceName");

    /**
     * This is currently used to identify Content-Type that may be
     * included within a document, such as in html documents
     * (e.g. <meta http-equiv="content-type" content="text/html; charset=UTF-8">)
     , or the value might come from outside the document.  This information
     * may be faulty and should be treated only as a hint.
     */

    /**
     * @see DublinCore#FORMAT
     */
    Property FORMAT = DublinCore.FORMAT;
    
    /**
    * @see DublinCore#IDENTIFIER
    */
    Property IDENTIFIER = DublinCore.IDENTIFIER;
    
    /**
    * @see DublinCore#CONTRIBUTOR
    */
    Property CONTRIBUTOR = DublinCore.CONTRIBUTOR;
    
   /**
    * @see DublinCore#COVERAGE
    */
     Property COVERAGE = DublinCore.COVERAGE;
    
   /**
    * @see DublinCore#CREATOR
    */
     Property CREATOR = DublinCore.CREATOR;
    
    /**
    * @see DublinCore#LANGUAGE
    */
     Property LANGUAGE = DublinCore.LANGUAGE;
    
   /**
    * @see DublinCore#PUBLISHER
    */
     Property PUBLISHER = DublinCore.PUBLISHER;
    
   /**
    * @see DublinCore#RELATION
    */
     Property RELATION = DublinCore.RELATION;

   /**
    * @see DublinCore#RIGHTS
    */
     Property RIGHTS = DublinCore.RIGHTS;
    
   /**
    * @see DublinCore#SOURCE
    */
     Property SOURCE = DublinCore.SOURCE;
    
   /**
    * @see DublinCore#TYPE
    */
     Property TYPE = DublinCore.TYPE;
    
    // Descriptive properties
    
    /**
     * @see DublinCore#TITLE
     */
     Property TITLE = DublinCore.TITLE;
     
    /**
     * @see DublinCore#DESCRIPTION
     */
     Property DESCRIPTION = DublinCore.DESCRIPTION;
     
    /**
     * {@link DublinCore#SUBJECT}; should include both subject and keywords
     *  if a document format has both.  See also {@link Office#KEYWORDS}
     *  and {@link OfficeOpenXMLCore#SUBJECT}.
     */
     Property SUBJECT = DublinCore.SUBJECT;

    // Date related properties
    
     /** 
      * @see DublinCore#DATE 
      */
      Property CREATED = DublinCore.CREATED;
      
      Property DATED = DublinCore.DATE;
      
      //Properties for the AC Archive
       Property DOC_TYPE = Property.externalClosedChoice(AC_META_PREFIX +"doctype", Archive.DocumentType.getAllTypes());
       Property RECIPIENT = Property.externalText(AC_META_PREFIX+"recipient");
      
     /** 
      * @see DublinCore#MODIFIED
      */
      Property MODIFIED = DublinCore.MODIFIED;

 
 
    /**
     * Embedded resource type property
     */
     Property EMBEDDED_RESOURCE_TYPE = Property.internalClosedChoice(EMBEDDED_RESOURCE_TYPE_KEY,
                                                                     EmbeddedResourceType.ATTACHMENT.toString(),
                                                                     EmbeddedResourceType.INLINE.toString());



    Property HAS_SIGNATURE = Property.internalBoolean("hasSignature");
}
