package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;
import com.antoniojnavarro.naventory.app.security.services.api.ServicioAutenticacion;
import com.antoniojnavarro.naventory.app.security.services.dto.UsuarioAutenticado;
import com.antoniojnavarro.naventory.model.entities.Categoria;
import com.antoniojnavarro.naventory.model.filters.CategoriaSearchFilter;
import com.antoniojnavarro.naventory.services.api.ServicioCategoria;

@Named("categoriasBean")
@Scope(value = PFScope.VIEW_SCOPED)
public class CategoriasBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(CategoriasBean.class);

	// CAMPOS
	private boolean editing;
	// ENTITIES
	private CategoriaSearchFilter filtro;
	private Categoria categoria;

	private Categoria selectedCategoria;
	private UsuarioAutenticado usuarioAutenticado;
	// LISTAS
	private List<Categoria> categorias;
	private List<Categoria> filteredCategorias;
	// SERVICIOS
	@Autowired
	private ServicioCategoria srvCategoria;
	@Autowired
	private ServicioAutenticacion srvAutenticacion;

	@PostConstruct
	public void init() {
		logger.info("Categorias.init()");
		inicilizarAtributos();
		cargarCategorias();

	}

	public void inicilizarAtributos() {
		this.usuarioAutenticado = srvAutenticacion.getUserDetailsCurrentUserLogged();
		this.categorias = null;
		this.editing = false;
		this.selectedCategoria = new Categoria();
		this.categoria = new Categoria();
	}

	public void newCategoria() {
		this.editing = false;
		this.selectedCategoria = new Categoria();
	}

	public void editarCategoria(Categoria categoria) {
		this.selectedCategoria = null;
		this.selectedCategoria = categoria;
		this.editing = true;
	}

	public void iniciarSelectedCategoria() {
		this.selectedCategoria = null;
	}

	public void cargarCategorias() {
		this.categorias = srvCategoria.findCategoriasByUsuario(this.usuarioAutenticado.getUsuario());
	}

	public void borrarCategoria(Categoria categoria) {

		srvCategoria.delete(categoria);
		this.categorias.remove(categoria);
		addInfo("categorias.succesDelete");
		this.editing = false;
	}

	public void guardarCategoria() {

		selectedCategoria.setUsuario(usuarioAutenticado.getUsuario());
		srvCategoria.saveOrUpdate(this.selectedCategoria, true);
		if (!editing) {
			this.categorias.add(selectedCategoria);
		}
		super.closeDialog("categoriaDetailsDialog");
		addInfo("categorias.succesNew");
		editing = false;
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

	public CategoriaSearchFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(CategoriaSearchFilter filtro) {
		this.filtro = filtro;
	}

}
