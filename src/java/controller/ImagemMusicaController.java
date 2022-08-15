package controller;

import dao.ImagemMusica;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import bean.ImagemMusicaFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named("imagemMusicaController")
@SessionScoped
public class ImagemMusicaController implements Serializable {

    @EJB
    private bean.ImagemMusicaFacade ejbFacade;
    private MenuModel model;
    private List<ImagemMusica> items = null;
    private ImagemMusica selected;
    @Inject
    private ImagemMusicaFacade imagemMusicaFacade;

    public ImagemMusicaController() {
    }
    
    @PostConstruct
    public void init() {
        if(getFacade().findAll().isEmpty()){
            imagemMusicaFacade.create(new ImagemMusica("/resources/imagens/contrast-annoDominiBeats.jpg", "/resources/musicas/contrast-annoDominiBeats.mp3","Contrast - Anno Domini Beats"));
            imagemMusicaFacade.create(new ImagemMusica("/resources/imagens/glass-annoDominiBeats.jpg", "/resources/musicas/glass-annoDominiBeats.mp3","Glass - Anno Domini Beats"));
            imagemMusicaFacade.create(new ImagemMusica("/resources/imagens/illusions-annoDominiBeats.jpg", "/resources/musicas/illusions-annoDominiBeats.mp3","Illusions - Anno Domini Beats"));
            imagemMusicaFacade.create(new ImagemMusica("/resources/imagens/neverSurrender-annoDominiBeats.jpg", "/resources/musicas/neverSurrender-annoDominiBeats.mp3","Never Surrender - Anno Domini Beats"));
            imagemMusicaFacade.create(new ImagemMusica("/resources/imagens/pray-annoDominiBeats.jpg", "/resources/musicas/pray-annoDominiBeats.mp3","Pray - Anno Domini Beats"));
            imagemMusicaFacade.create(new ImagemMusica("/resources/imagens/warzone-annoDominiBeats.jpg", "/resources/musicas/warzone-annoDominiBeats.mp3","Warzone - Anno Domini Beats"));
        }
        /*model = new DefaultMenuModel();

        //First submenu
        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
                .label("Options")
                .build();

        DefaultMenuItem item = DefaultMenuItem.builder()
                .value("Save (Non-Ajax)")
                .icon("pi pi-save")
                .ajax(false)
                .command("#{menuView.save}")
                .update("messages")
                .build();
        firstSubmenu.getElements().add(item);

        item = DefaultMenuItem.builder()
                .value("Update")
                .icon("pi pi-refresh")
                .command("#{menuView.update}")
                .update("messages")
                .build();
        firstSubmenu.getElements().add(item);*/
        //imagemMusicaFacade.create(new ImagemMusica("contrast-annoDominiBeats.jpg", "Contrast - Anno Domini Beats.mp3","Contrast - Anno Domini Beats"));
        //imagemMusicaFacade.create(new ImagemMusica("glass-annoDominiBeats.jpg", "Glass - Anno Domini Beats.mp3","Glass - Anno Domini Beats"));
        //imagemMusicaFacade.create(new ImagemMusica("illusions-annoDominiBeats.jpg", "Illusions - Anno Domini Beats.mp3","Illusions - Anno Domini Beats"));
        //imagemMusicaFacade.create(new ImagemMusica("neverSurrender-annoDominiBeats.jpg", "Never Surrender - Anno Domini Beats.mp3","Never Surrender - Anno Domini Beats"));
        //imagemMusicaFacade.create(new ImagemMusica("pray-annoDominiBeats.jpg", "Pray - Anno Domini Beats.mp3","Pray - Anno Domini Beats"));
        //imagemMusicaFacade.create(new ImagemMusica("warzone-annoDominiBeats.jpg", "Warzone - Anno Domini Beats.mp3","Warzone - Anno Domini Beats"));
    }

    public ImagemMusica getSelected() {
        return selected;
    }

    public void setSelected(ImagemMusica selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ImagemMusicaFacade getFacade() {
        return ejbFacade;
    }

    public ImagemMusica prepareCreate() {
        selected = new ImagemMusica();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ImagemMusicaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ImagemMusicaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ImagemMusicaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ImagemMusica> getItems() {
        if (items == null) {
            items = getFacade().findAll();            
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ImagemMusica getImagemMusica(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<ImagemMusica> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ImagemMusica> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ImagemMusica.class)
    public static class ImagemMusicaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImagemMusicaController controller = (ImagemMusicaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "imagemMusicaController");
            return controller.getImagemMusica(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ImagemMusica) {
                ImagemMusica o = (ImagemMusica) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ImagemMusica.class.getName()});
                return null;
            }
        }

    }

}
