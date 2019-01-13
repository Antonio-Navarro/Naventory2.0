//package com.antoniojnavarro.naventory.app.jsf.beans;
//
//import java.io.IOException;
//
//import javax.annotation.PostConstruct;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.inject.Named;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Scope;
//
//import com.antoniojnavarro.naventory.app.commons.PFScope;
//import com.antoniojnavarro.naventory.model.entities.Usuario;
//
//@Named("usuarioAutenticadoBean")
//@Scope(value = PFScope.SESSION_SCOPED)
//public class UsuarioAutenticado extends MasterBean {
//
//	private static final long serialVersionUID = 1L;
//
//	private static final Logger logger = LoggerFactory.getLogger(UsuarioAutenticado.class);
//
//	// CAMPOS
//	// ENTITIES
//	private Usuario usuario;
//
//	// LISTAS
//
//	// SERVICIOS
//
//	@PostConstruct
//	public void init() {
//		logger.info("UsuarioAutenticado.init()");
//	}
//
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//
//	public void isLoged() {
//		if (getUsuario() == null) {
//			FacesContext facesContext = FacesContext.getCurrentInstance();
//			ExternalContext externalContext = facesContext.getExternalContext();
//			try {
//				externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public void isNotLogin() {
//		if (getUsuario() != null) {
//			FacesContext facesContext = FacesContext.getCurrentInstance();
//			ExternalContext externalContext = facesContext.getExternalContext();
//			try {
//				externalContext.redirect(externalContext.getRequestContextPath() + "/private/home.xhtml");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//}
