package br.upf.projetofotografia.controller;

import br.upf.projetofotografia.entity.LocalEntity;
import br.upf.projetofotografia.facade.LocalFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class LocalController implements Serializable {

    @EJB
    private LocalFacade facade;

    private LocalEntity entity;
    private LocalEntity selected;
    private List<LocalEntity> list;

    @PostConstruct
    public void init() {
        entity = new LocalEntity();
        list = facade.findAll();
    }

    public void salvar() {
        if (entity.getId() == null) {
            facade.create(entity);
        } else {
            facade.edit(entity);
        }
        recarregarLocais(); // Recarrega a lista após salvar
        entity = new LocalEntity(); // Limpa o formulário
    }

    public void excluir() {
        if (selected != null) {
            facade.remove(selected);
            recarregarLocais(); // Recarrega a lista após excluir
            selected = null;
        }
    }

    public void novo() {
        entity = new LocalEntity();
    }

    public LocalEntity getEntity() { return entity; }
    public void setEntity(LocalEntity entity) {
        this.entity = entity;
    }

    public LocalEntity getSelected() { return selected; }
    public void setSelected(LocalEntity selected) {
        this.selected = selected;
        if (selected != null) {
            this.entity = selected; // Carrega os dados do local selecionado para edição
        }
    }

    public List<LocalEntity> getList() { return list; }

    private void recarregarLocais() {
        list = facade.findAll();
    }
}


