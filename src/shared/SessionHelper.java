package shared;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionHelper {
	private HttpSession session;
	public SessionHelper() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		session = (HttpSession) facesContext.getExternalContext().getSession(false);
	}
	public static HttpSession getSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (HttpSession) facesContext.getExternalContext().getSession(false);
	}
}
