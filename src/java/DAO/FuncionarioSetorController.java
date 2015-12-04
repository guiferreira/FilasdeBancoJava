package DAO;

import DAO.util.JsfUtil;
import DAO.util.PaginationHelper;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "funcionarioSetorController")
@SessionScoped
public class FuncionarioSetorController implements Serializable {

    private FuncionarioSetor current;
    private DataModel items = null;
    @EJB
    private DAO.FuncionarioSetorFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public FuncionarioSetorController() {
    }

    public FuncionarioSetor getSelected() {
        if (current == null) {
            current = new FuncionarioSetor();
            current.setFuncionarioSetorPK(new DAO.FuncionarioSetorPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private FuncionarioSetorFacade getFacade() {
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
        current = (FuncionarioSetor) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new FuncionarioSetor();
        current.setFuncionarioSetorPK(new DAO.FuncionarioSetorPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getFuncionarioSetorPK().setIdsetor(current.getSetor().getIdsetor());
            current.getFuncionarioSetorPK().setIdfuncionario(current.getFuncionario().getIdfuncionario());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FuncionarioSetorCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (FuncionarioSetor) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getFuncionarioSetorPK().setIdsetor(current.getSetor().getIdsetor());
            current.getFuncionarioSetorPK().setIdfuncionario(current.getFuncionario().getIdfuncionario());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FuncionarioSetorUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (FuncionarioSetor) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FuncionarioSetorDeleted"));
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

    @FacesConverter(forClass = FuncionarioSetor.class)
    public static class FuncionarioSetorControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FuncionarioSetorController controller = (FuncionarioSetorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "funcionarioSetorController");
            return controller.ejbFacade.find(getKey(value));
        }

        DAO.FuncionarioSetorPK getKey(String value) {
            DAO.FuncionarioSetorPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new DAO.FuncionarioSetorPK();
            key.setIdfuncionario(Integer.parseInt(values[0]));
            key.setIdsetor(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(DAO.FuncionarioSetorPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdfuncionario());
            sb.append(SEPARATOR);
            sb.append(value.getIdsetor());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof FuncionarioSetor) {
                FuncionarioSetor o = (FuncionarioSetor) object;
                return getStringKey(o.getFuncionarioSetorPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FuncionarioSetor.class.getName());
            }
        }

    }

}
