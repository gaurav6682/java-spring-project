
	package com.project.Util;

	import java.util.List;

	import org.springframework.stereotype.Component;

import com.project.model.FillForm;
import com.project.model.FillFormHeader;

	@Component
	public class FillFormUtil {
		
		FillFormHeader fillformheader;
		List<FillForm> fillform;
		public FillFormUtil() {
			super();
		}
		public FillFormUtil(FillFormHeader fillformheader, List<FillForm> fillform) {
			super();
			this.fillformheader = fillformheader;
			this.fillform = fillform;
		}
		public FillFormHeader getFillformheader() {
			return fillformheader;
		}
		public void setFillformheader(FillFormHeader fillformheader) {
			this.fillformheader = fillformheader;
		}
		public List<FillForm> getFillform() {
			return fillform;
		}
		public void setFillform(List<FillForm> fillform) {
			this.fillform = fillform;
		}
		@Override
		public String toString() {
			return "FillFormUtil [fillformheader=" + fillformheader + ", fillform=" + fillform + "]";
		}
		
		
		
		
	}

