package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Utilitários para JSF
 * 
 * @author E804621
 *
 */
public class JSFUtils {

	public static void addInfoMessage(String msg) {
		FacesMessage message = new FacesMessage();
		message.setSummary(msg);
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void addMessage(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public static void addWarnMessage(String msg) {
		FacesMessage message = new FacesMessage();
		message.setSummary(msg);
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public static void addErrorMessage(String msg) {
		FacesMessage message = new FacesMessage();
		message.setSummary(msg);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
