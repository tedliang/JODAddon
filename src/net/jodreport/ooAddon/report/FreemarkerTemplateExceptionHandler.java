package net.jodreport.ooAddon.report;

import java.io.IOException;
import java.io.Writer;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerTemplateExceptionHandler implements TemplateExceptionHandler {

	public void handleTemplateException(TemplateException te, Environment env, Writer out) 
			throws TemplateException {
		try {
			
			String ftl = te.getFTLInstructionStack();
			String error = ftl.substring(ftl.indexOf("==> ")+4);
			int idx = error.indexOf(" escaped ${");
			if(idx==-1){
				idx = error.indexOf(" [on line ");
			}
			if(idx>0){
				error = error.substring(0, idx);
			}
			out.write("[ERROR: " + error + "]");
			
		} catch (IOException e) {
			throw new TemplateException(
					"Failed to print error message. Cause: " + e, env);
		}
	}

}
