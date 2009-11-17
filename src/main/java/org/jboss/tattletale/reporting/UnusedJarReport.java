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
package org.jboss.tattletale.reporting;

import org.jboss.tattletale.core.Archive;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.SortedSet;

/**
 * A report that shows unused JAR archives
 * @author Jesper Pedersen <jesper.pedersen@jboss.org>
 */
public class UnusedJarReport extends Report
{
   /** NAME */
   private static final String NAME = "Unused Jar";

   /** DIRECTORY */
   private static final String DIRECTORY = "unusedjar";

   /**
    * Constructor
    * @param archives The archives
    */
   public UnusedJarReport(SortedSet<Archive> archives)
   {
      super(ReportSeverity.WARNING, archives, NAME, DIRECTORY);
   }

   /**
    * Write out the report's content
    * @param bw the writer to use
    * @exception IOException if an error occurs
    */
   void writeHtmlBodyContent(BufferedWriter bw) throws IOException
   {
      bw.write("<table>" + Dump.NEW_LINE);

      bw.write("  <tr>" + Dump.NEW_LINE);
      bw.write("     <th>Archive</th>" + Dump.NEW_LINE);
      bw.write("     <th>Used</th>" + Dump.NEW_LINE);
      bw.write("  </tr>" + Dump.NEW_LINE);

      boolean odd = true;
      int used = 0;
      int unused = 0;

      for (Archive archive : archives)
      {
         boolean archiveStatus = false;

         Iterator<Archive> it = archives.iterator();
         while (!archiveStatus && it.hasNext())
         {
            Archive a = it.next();

            if (!archive.getName().equals(a.getName()))
            {
               Iterator<String> sit = a.getRequires().iterator();
               while (!archiveStatus && sit.hasNext())
               {
                  String require = sit.next();

                  if (archive.getProvides().keySet().contains(require))
                  {
                     archiveStatus = true;
                  }
               }
            }
         }


         if (odd)
         {
            bw.write("  <tr class=\"rowodd\">" + Dump.NEW_LINE);
         }
         else
         {
            bw.write("  <tr class=\"roweven\">" + Dump.NEW_LINE);
         }
         bw.write("     <td><a href=\"../jar/" + archive.getName() + ".html\">" + archive.getName() + "</a></td>" +
                  Dump.NEW_LINE);

         if (archiveStatus)
         {
            bw.write("     <td style=\"color: green;\">Yes</td>" + Dump.NEW_LINE);
            used++;
         }
         else
         {
            bw.write("     <td style=\"color: red;\">No</td>" + Dump.NEW_LINE);
            unused++;
            status = ReportStatus.YELLOW;
         }

         bw.write("  </tr>" + Dump.NEW_LINE);

         odd = !odd;
      }

      bw.write("</table>" + Dump.NEW_LINE);

      bw.write(Dump.NEW_LINE);
      bw.write("<p>" + Dump.NEW_LINE);

      bw.write("<table>" + Dump.NEW_LINE);

      bw.write("  <tr>" + Dump.NEW_LINE);
      bw.write("     <th>Status</th>" + Dump.NEW_LINE);
      bw.write("     <th>Archives</th>" + Dump.NEW_LINE);
      bw.write("  </tr>" + Dump.NEW_LINE);

      bw.write("  <tr class=\"rowodd\">" + Dump.NEW_LINE);
      bw.write("     <td>Used</td>" + Dump.NEW_LINE);
      bw.write("     <td style=\"color: green;\">" + used + "</td>" + Dump.NEW_LINE);
      bw.write("  </tr>" + Dump.NEW_LINE);

      bw.write("  <tr class=\"roweven\">" + Dump.NEW_LINE);
      bw.write("     <td>Unused</td>" + Dump.NEW_LINE);
      bw.write("     <td style=\"color: red;\">" + unused + "</td>" + Dump.NEW_LINE);
      bw.write("  </tr>" + Dump.NEW_LINE);

      bw.write("</table>" + Dump.NEW_LINE);
   }

   /**
    * write out the header of the report's content
    * @param bw the writer to use
    * @throws IOException if an errror occurs
    */
   void writeHtmlBodyHeader(BufferedWriter bw) throws IOException
   {
      bw.write("<body>" + Dump.NEW_LINE);
      bw.write(Dump.NEW_LINE);

      bw.write("<h1>" + NAME + "</h1>" + Dump.NEW_LINE);

      bw.write("<a href=\"../index.html\">Main</a>" + Dump.NEW_LINE);
      bw.write("<p>" + Dump.NEW_LINE);
   }
}
