/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.tattletale.reporting.profiles;

import org.jboss.tattletale.core.ArchiveTypes;

import javassist.bytecode.ClassFile;

/**
 * Sun: Java 5 (JSSE)
 *
 * @author Jesper Pedersen <jesper.pedersen@jboss.org>
 */
public class SunJava5JSSE extends CommonProfile
{

   private static final String CLASS_SET = "sunjdk5-jsse.clz.gz";
   private static final String PROFILE_NAME = "Sun Java 5 (JSSE)";
   private static final String PROFILE_CODE = "jsse5";
   private static final String PROFILE_LOCATION = "jsse.jar";
   private static final int ARCHIVE_TYPE = ArchiveTypes.JAR;
   private static final int CLASSFILE_VERSION = ClassFile.JAVA_5;

   /** Constructor */
   public SunJava5JSSE()
   {
      super(CLASS_SET, ARCHIVE_TYPE, PROFILE_NAME, CLASSFILE_VERSION, PROFILE_LOCATION);
   }

   @Override
   public String getProfileCode()
   {
      return PROFILE_CODE;
   }

   @Override
   protected String getProfileName()
   {
      return PROFILE_NAME;
   }
}
