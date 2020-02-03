package util;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Utilitarios para o JSF
 * 
 * @author E804684
 *
 */
public class JSFUtils {

	/**
	 * Adiciona mensagem para ser exibida no JSF e mantem ela mesmo ao redirecionar
	 * de pagina com o setKeepMessages(true)
	 * 
	 * @param mensagem
	 */
	public static void addMensagemComFlah(String mensagem) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
	}

	public static void addMensagem(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
	}

	public static void redirectTo(String url) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			JSFUtils.addMensagem(e.getMessage());
		}
	}
}
