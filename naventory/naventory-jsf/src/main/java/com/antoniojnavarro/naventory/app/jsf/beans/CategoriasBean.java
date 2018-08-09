package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.services.api.ServicioCategoria;

@Named("categoriasBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class CategoriasBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(CategoriasBean.class);

	// CAMPOS
	private boolean editing;
	// ENTITIES

	private Categoria categoria;

	private Categoria selectedCategoria;
	@Autowired
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private List<Categoria> categorias;
	private List<Categoria> filteredCategorias;
	// SERVICIOS
	@Autowired
	private ServicioCategoria srvCategoria;

	@PostConstruct
	public void init() {
		usuarioAutenticado.isLoged();
		logger.info("Categorias.init()");
		inicilizarAtributos();
		cargarCategorias();

	}

	public void inicilizarAtributos() {
		this.categorias = null;
		this.editing = false;
		this.selectedCategoria = new Categoria();
		this.categoria = new Categoria();
	}
	public void newCategoria() {
		this.editing = false;
		this.selectedCategoria = new Categoria();
	}
	public void editarCategoria (Categoria categoria) {
		this.selectedCategoria = categoria;
		this.editing = true;
	}
	
	public void cargarCategorias() {
		this.categorias = srvCategoria.findCategoriasByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void borrarCategoria(Categoria categoria) {

		srvCategoria.delete(categoria);
		this.categorias.remove(categoria);
		addInfo("users.succesDelete");
	}

	public void guardarCategoria() {
		srvCategoria.saveOrUpdate(this.selectedCategoria, true);
		addInfo("categorias.succesNew");

	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getSelectedCategoria() {
		return selectedCategoria;
	}

	public void setSelectedCategoria(Categoria selectedCategoria) {
		this.selectedCategoria = selectedCategoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public List<Categoria> getFilteredCategorias() {
		return filteredCategorias;
	}

	public void setFilteredCategorias(List<Categoria> filteredCategorias) {
		this.filteredCategorias = filteredCategorias;
	}

}
