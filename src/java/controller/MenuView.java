package controller;

import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import bean.ImageFacade;
import bean.PlaylistFacade;
import dao.Image;
import dao.Playlist;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named
@SessionScoped
public class MenuView implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private bean.PlaylistFacade ejbFacade;
    private MenuModel model;
    private List<Playlist> items;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();

        //First submenu
        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
                .label("Menu")
                .build();
        
        DefaultMenuItem item = DefaultMenuItem.builder()
                .value("Musicas")
                .icon("pi pi-save")
                .url("/ProjetoFinalJSFBibliotecaMusicas/faces/index.xhtml")
                .build();
        firstSubmenu.getElements().add(item);

        item = DefaultMenuItem.builder()
                .value("Playlist")
                .icon("pi pi-refresh")
                .url("/ProjetoFinalJSFBibliotecaMusicas/faces/playlist/ListarPlaylist.xhtml")
                .build();
        firstSubmenu.getElements().add(item);

        /*DefaultMenuItem item = DefaultMenuItem.builder()
                .value("Musicas")
                .icon("pi pi-save")
                .ajax(false)
                .command("#{menuView.save}")
                .update("messages")
                .build();
        firstSubmenu.getElements().add(item);

        item = DefaultMenuItem.builder()
                .value("Playlist")
                .icon("pi pi-refresh")
                .command("#{menuView.update}")
                .update("messages")
                .build();
        firstSubmenu.getElements().add(item);

        item = DefaultMenuItem.builder()
                .value("Delete")
                .icon("pi pi-times")
                .command("#{menuView.delete}")
                .build();
        firstSubmenu.getElements().add(item);*/

        model.getElements().add(firstSubmenu);

        //Second submenu
        DefaultSubMenu secondSubmenu = DefaultSubMenu.builder()
                .label("Playlist")
                .build();

        items = getItems();
        for(Playlist conteudo: items){
            item = DefaultMenuItem.builder()
                .value(conteudo)                
                .build();
        secondSubmenu.getElements().add(item);
        }
        
        
        /*item = DefaultMenuItem.builder()
                .value("Website")
                .url("http://www.primefaces.org")
                .icon("pi pi-external-link")
                .build();
        secondSubmenu.getElements().add(item);

        item = DefaultMenuItem.builder()
                .value("Internal")
                .icon("pi pi-upload")
                .command("#{menuView.redirect}")
                .build();
        secondSubmenu.getElements().add(item);*/

        model.getElements().add(secondSubmenu);
    }

    public MenuModel getModel() {
        return model;
    }
    
    public List<Playlist> getItems(){
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    private PlaylistFacade getFacade() {
        return ejbFacade;
    }

    public void redirect() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath());
    }

    public void save() {
        addMessage("Save", "Data saved");
    }

    public void update() {
        addMessage("Update", "Data updated");
    }

    public void delete() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Delete", "Data deleted");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void sleepAndSave() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        save();
    }

    public void sleepAndUpdate() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        update();
    }

    public void sleepAndDelete() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        delete();
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
