package com.devtty.gat.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * A class extending {@link Application} and annotated with @ApplicationPath
 * is the Java EE 6 "no XML" approach to activating JAX-RS.
 *
 * <p>Resources are served relative to the servlet path specified in the {@link
 * ApplicationPath} annotation.</p>
 *
 * <p><strong>NOTE</strong><br/>The following modifications are required to get
 * JAX-RS working on JBoss AS 6.0.0.M4:</p>
 *
 * <ol>
 * <li>Remove (or comment out) the <code>extends Application</code> clause on this class</li>
 * <li>Remove (or comment out) the <code>@ApplicationPath</code> annotation on this class</li>
 * <li>Open up web.xml and add the following XML snippet:
 * <pre>
 * &lt;context-param&gt;
 *    &lt;param-name&gt;resteasy.scan&lt;/param-name&gt;
 *    &lt;param-value&gt;true&lt;/param-value&gt;
 * &lt;/context-param&gt;

 * &lt;context-param&gt;
 *    &lt;param-name&gt;resteasy.servlet.mapping.prefix&lt;/param-name&gt;
 *    &lt;param-value&gt;/rest&lt;/param-value&gt;
 * &lt;/context-param&gt;
 * </pre>
 * <p>The mapping prefix should match the value that was in the
 * <code>@ApplicationPath</code> annotation, prepended with a forward slash (/)
 * if not already present.</p>
 * </li>
 * </ol>
 */
@ApplicationPath("/rest")
public class JaxRsActivator extends Application
{
   /* class body intentionally left blank */
}
