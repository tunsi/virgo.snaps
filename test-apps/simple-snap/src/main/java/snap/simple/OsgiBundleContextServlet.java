/*******************************************************************************
 * Copyright (c) 2008, 2010 VMware Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   VMware Inc. - initial contribution
 *******************************************************************************/

package snap.simple;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Dictionary;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;

public class OsgiBundleContextServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    /** 
     * {@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BundleContext bundleContext = (BundleContext) getServletContext().getAttribute("osgi-bundlecontext");
        
        PrintWriter writer = response.getWriter();
        
        Dictionary<?, ?> headers = bundleContext.getBundle().getHeaders();
        Enumeration<?> keys = headers.keys();
        
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            writer.println(key + " " + headers.get(key));
        }
        
        writer.flush();
        writer.close();
    }
}
