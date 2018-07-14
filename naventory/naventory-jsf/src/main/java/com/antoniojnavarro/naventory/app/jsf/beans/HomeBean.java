//package com.antoniojnavarro.naventory.app.jsf.beans;
//
//import javax.annotation.PostConstruct;
//import javax.inject.Named;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//
//import com.antoniojnavarro.naventory.app.commons.PFScope;
//import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
//import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
//
//@Named("homeBean")
//@Scope(value = PFScope.VIEW_SCOPED)
//public class HomeBean extends MasterBean {
//	private static final Logger logger = LoggerFactory.getLogger(HomeBean.class);
//	private static final long serialVersionUID = 1L;
//	private UsuarioAutenticado usuarioAutenticado;
//
//	@Autowired
//	private ServicioAutenticacion srvAutenticacion;
//
//	@PostConstruct
//	private void init() {
//		this.usuarioAutenticado = this.srvAutenticacion.getUserDetailsCurrentUserLogged();
//		logger.info("El usuario autenticado es:  " + usuarioAutenticado.getUsername());
//	}
//
//	public UsuarioAutenticado getUsuarioAutenticado() {
//		return usuarioAutenticado;
//	}
//
//	public void setUsuarioAutenticado(UsuarioAutenticado usuarioAutenticado) {
//		this.usuarioAutenticado = usuarioAutenticado;
//	}
//}
