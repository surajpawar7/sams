package controllers;

import entities.SubjectBook;
import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import beans.SubjectBookFacade;
import entities.FacultySubject;
import entities.Subject;
import entities.SubjectObjective;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("subjectBookController")
@SessionScoped
public class SubjectBookController implements Serializable {

    private SubjectBook current;
    private DataModel items = null;
    @EJB
    private beans.SubjectBookFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Subject sub;

    public SubjectBookController() {
    }

    public Subject getSub() {
        return sub;
    }

    public SubjectBook getSelected() {
        if (current == null) {
            current = new SubjectBook();
            selectedItemIndex = -1;
        }
        return current;
    }

    private SubjectBookFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (SubjectBook) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    public DataModel getItemsUser() {
        return new ListDataModel(getFacade().getByIdSubject(sub));
    }
    public DataModel getItemsUser(FacultySubject facSub) {
        return new ListDataModel(getFacade().getByIdSubject(facSub.getIdSubject()));
    }
    public String prepareCreate() {
        current = new SubjectBook();
        selectedItemIndex = -1;
        return "Create";
    }
    
    public String prepareSubjectBook(Subject sub) {
        this.sub = sub;
        prepareCreate();

        return "SubjectBooks?faces-redirect=true";
    }
    
    public void createA() {
        current.setIdSubjectBooks(0);
        current.setIdSubject(sub);
        create();
    }
    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SubjectBookCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (SubjectBook) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SubjectBookUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    public String destroyObjective(SubjectBook subjectBook) {
        current = subjectBook;
        performDestroy();
        prepareCreate();
        return "SubjectBooks?faces-redirect=true";
    }
    public String destroy() {
        current = (SubjectBook) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SubjectBookDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public SubjectBook getSubjectBook(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = SubjectBook.class)
    public static class SubjectBookControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SubjectBookController controller = (SubjectBookController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "subjectBookController");
            return controller.getSubjectBook(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof SubjectBook) {
                SubjectBook o = (SubjectBook) object;
                return getStringKey(o.getIdSubjectBooks());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + SubjectBook.class.getName());
            }
        }
    }
}
