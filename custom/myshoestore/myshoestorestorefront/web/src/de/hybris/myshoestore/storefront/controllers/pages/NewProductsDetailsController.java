package de.hybris.myshoestore.storefront.controllers.pages;

import de.hybris.myshoestore.core.newproductform.NewProductsForm;
import de.hybris.myshoestore.facades.NewProducts.NewProductsFacade;
import de.hybris.myshoestore.facades.NewProductsData;
import de.hybris.myshoestore.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static de.hybris.myshoestore.storefront.controllers.ControllerConstants.Views.Pages.Product.CreateNewProducts;


@Controller
@RequestMapping(value = "/new")
public class NewProductsDetailsController extends AbstractPageController {
    @Autowired
    private NewProductsFacade newProductsFacade;
    private static final String NEWPRODUCTS_CMS_PAGE = "newProductsDetails";
    private static final String REDIRECT_TO_CREATE_NEWPRODUCTS = REDIRECT_PREFIX + "/new/newProducts";
    private static final String FORM_GLOBAL_ERROR = "form.global.error";


    private static final Logger LOG = Logger.getLogger(NewProductsDetailsController.class);

    @RequestMapping(value = "/newProducts", method = RequestMethod.GET)
    public String getNewProductsDetails(final Model model) throws CMSItemNotFoundException {
        LOG.info("######## NewProductDetailsController updateOldPassword() method ####");

        //final List<NewProductsData> newProductsData = newProductsFacade.getNewProductsDetails();
        final List<NewProductsData>newProductsData=newProductsFacade.getNewProductsDetails();

        model.addAttribute("newProductsData", newProductsData);
        storeCmsPageInModel(model, getContentPageForLabelOrId("newProducts"));
        setUpMetaDataForContentPage(model, getContentPageForLabelOrId("newProducts"));


        return ControllerConstants.Views.Pages.Product.NewProductsDetails;
    }

    @RequestMapping(value = "/CreateNewProducts", method = RequestMethod.GET)
    public String createNewProducts(final Model model) throws CMSItemNotFoundException {
        LOG.info("#### NEWPRODUCTSCONTROLLER ####");
        final NewProductsForm newProductsForm = getPreparedNewProductsForm();
        model.addAttribute("newProductsForm", new NewProductsForm());
        final ContentPageModel createNewProductsPage = getContentPageForLabelOrId("CreateNewProducts");
        storeCmsPageInModel(model, createNewProductsPage);
        setUpMetaDataForContentPage(model, createNewProductsPage);
        return CreateNewProducts;
    }

    @RequestMapping(value = "/CreateNewProducts", method = RequestMethod.POST)
    public String createNewProducts(final NewProductsForm newProductsForm, final BindingResult bindingResult, final Model model,
                                    final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException {
        LOG.info("######## NewProductsController ########");
        if (bindingResult.hasErrors()) {
            GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
            final ContentPageModel createNewProducts = getContentPageForLabelOrId("CreateNewProducts");
            storeCmsPageInModel(model, createNewProducts);
            setUpMetaDataForContentPage(model, createNewProducts);
            return getViewForPage(model);
        }
        String returnAction = REDIRECT_TO_CREATE_NEWPRODUCTS;
        final NewProductsData newProducts = new NewProductsData();
        newProducts.setCode(newProductsForm.getCode());
        newProducts.setName(newProductsForm.getName());
        newProducts.setDescription(newProductsForm.getDescription());
        newProducts.setSize(newProductsForm.getSize());
        newProducts.setColor(newProductsForm.getColor());
        newProducts.setPurchaseDate(newProductsForm.getPurchaseDate());

        model.addAttribute("newProductsForm", new NewProductsForm());

        final ContentPageModel createNewProductsPage = getContentPageForLabelOrId("CreateNewProducts");
        storeCmsPageInModel(model, createNewProductsPage);
        setUpMetaDataForContentPage(model, createNewProductsPage);

        getNewProductsFacade().createNewProducts(newProducts);
        GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
                "NewProducts Created!", null);

        return returnAction;
    }

    protected NewProductsForm getPreparedNewProductsForm() {
        final NewProductsForm newProductsForm = new NewProductsForm();
        newProductsForm.setCode("Product Code");
        newProductsForm.setName("Product Name");
        newProductsForm.setDescription("Product Description");
        newProductsForm.setSize("Product Size");
        newProductsForm.setColor("Product Color");
        newProductsForm.setPurchaseDate(0);
        return newProductsForm;
    }

    public NewProductsFacade getNewProductsFacade() {
        return newProductsFacade;
    }

    public void setNewProductsFacade(final NewProductsFacade newProductsFacade) {
        this.newProductsFacade = newProductsFacade;
    }

    public static String getNewproductsCmsPage() {
        return NEWPRODUCTS_CMS_PAGE;
    }


    @RequestMapping(value = "/UpdateNewProducts", method = RequestMethod.GET)
    public String updateNewProductsName(final Model model) throws CMSItemNotFoundException {
        LOG.info("######## NewProductsController ########");
        model.addAttribute("newProductsForm", new NewProductsForm());
        final ContentPageModel updateNewProductsPage = getContentPageForLabelOrId("UpdateNewProducts");
        storeCmsPageInModel(model, updateNewProductsPage);
        setUpMetaDataForContentPage(model, updateNewProductsPage);
        return ControllerConstants.Views.Pages.Product.UpdateNewProducts;
    }

    @RequestMapping(value = "/UpdateNewProducts", method = RequestMethod.POST)
    public String updateNewProducts(final NewProductsForm newProductsForm, final BindingResult bindingResult, final Model model,
                                    final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException {
        LOG.info("######## NewProductsController ########");
        if (newProductsForm.getCode() == null) {
            GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, "Please Enter the Code", null);
            return REDIRECT_PREFIX + "UpdateNewProducts";
        }
        if (getNewProductsFacade().getSpecificNewProductsDetails(newProductsForm.getCode()).isEmpty()) {
            GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, "Code Not Found", null);
            return REDIRECT_PREFIX + "UpdateNewProducts";
        }
        if (bindingResult.hasErrors()) {
            GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
            final ContentPageModel updateNewProducts = getContentPageForLabelOrId("UpdateNewProducts");
            storeCmsPageInModel(model, updateNewProducts);
            setUpMetaDataForContentPage(model, updateNewProducts);
            return getViewForPage(model);
        }
        String returnAction = REDIRECT_TO_CREATE_NEWPRODUCTS;

        final List<NewProductsData> newProductsData = newProductsFacade.getSpecificNewProductsDetails(newProductsForm.getCode());

        model.addAttribute("newProductsForm", new NewProductsForm());

        final ContentPageModel updateNewProducts = getContentPageForLabelOrId("UpdateNewProducts");
        storeCmsPageInModel(model, updateNewProducts);
        setUpMetaDataForContentPage(model, updateNewProducts);

        getNewProductsFacade().updateNewProducts(newProductsForm.getCode(), newProductsForm.getName());
        GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
                "New Products Updated!", null);

        return returnAction;
    }

    @RequestMapping(value = "/removeNewProducts/{code}", method = RequestMethod.POST)
    public String deleteNewProducts(@PathVariable final String code,
                                 final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException {
        LOG.info("######## NewProductsController ########");

        String returnAction = REDIRECT_TO_CREATE_NEWPRODUCTS;

        getNewProductsFacade().removeNewProducts(code);
        GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER,
                "NewProducts Removed with Code - " + code + "!", null);

        return returnAction;

    }
}

