package com.antoniojnavarro.naventory.app.commons;

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.antoniojnavarro.naventory.app.commons.utils.AOPUtils;
import com.antoniojnavarro.naventory.app.util.Constantes;
import com.antoniojnavarro.naventory.services.commons.ServicioException;

@Component
@Aspect
@Order(2)
public class AspectAroundAllTypesOfExceptionHandler implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(AspectAroundAllTypesOfExceptionHandler.class);

	private static final long serialVersionUID = 1L;

	@Around("(@within(org.springframework.stereotype.Controller) "
			+ "|| @within(javax.inject.Named)) && execution(public * *(..))")
	public Object handle(ProceedingJoinPoint proceedingJoinPoint) throws IOException {
		try {
			return proceedingJoinPoint.proceed();
		} catch (ServicioException ex) {
			// Se introduce un mensaje en el FacesContext para visualizarlo en
			// la
			// vista
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
		} catch (InvalidDataAccessApiUsageException ex){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
			String messageMergeException = bundle.getString("entidad.negocio.merge.exception.ifnotexist");
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, messageMergeException, messageMergeException));
		} catch (DataIntegrityViolationException ex) {
			if (RequestContextHolder.getRequestAttributes() != null) {
				// Vamos a incluir la excepción dentro de la sesión web, para
				// poder recuperarla en la página de error.
				ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
				if (attrs.getRequest() != null) {
					attrs.getRequest().getSession(true).setAttribute(Constantes.SESSION_ATTR_EXCEPTION_ASPECT, ex);
				}
			}

			// Se introduce un mensaje en el FacesContext para visualizarlo en
			// la
			// vista y tener un enlace al /errorPage.xhtml
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.getExternalContext().getFlash().setKeepMessages(true);
			ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
			HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
			String message = bundle.getString("entidad.negocio.violacionintegridad").replace("{0}",
					AOPUtils.buildRequestURL(req) + "/errorPage.xhtml");
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
		} catch (Throwable ex) {
			if (RequestContextHolder.getRequestAttributes() != null) {
				// Vamos a incluir la excepción dentro de la sesión web, para
				// poder recuperarla en la página de error.
				ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
				if (attrs.getRequest() != null) {
					attrs.getRequest().getSession().setAttribute(Constantes.SESSION_ATTR_EXCEPTION_ASPECT, ex);
				}
			}
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
			String buildRequest = AOPUtils.buildRequestURL(req) + "/errorPage.xhtml";
			externalContext.redirect(buildRequest);
		}
		return new String();
	}

	@PostConstruct
	public void init() {
		logger.info("Iniciando " + this.getClass().getSimpleName());
	}

	@PreDestroy
	public void destroy() {
		logger.info("Destruyendo " + this.getClass().getSimpleName());
	}
}
