package de.hybris.myshoestore.facades.NewProducts.Impl;

import de.hybris.myshoestore.core.model.NewProductsModel;
import de.hybris.myshoestore.core.service.NewProductsService;

import de.hybris.myshoestore.facades.NewProducts.NewProductsFacadeApi;
import de.hybris.myshoestore.facades.NewProductsData;
import de.hybris.myshoestore.facades.populators.NewProductsPopulatorApi;
import de.hybris.myshoestore.facades.populators.NewProductsReversePopulator;

import de.hybris.platform.commercewebservicescommons.dto.NewProducts.NewProductsDataWSDTO;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class NewProductsFacadeImplApi implements NewProductsFacadeApi {
    private static final Logger LOG = Logger.getLogger(NewProductsFacadeImplApi.class);
    private NewProductsService newProductsService;
    private NewProductsPopulatorApi newProductsPopulatorApi;
    private NewProductsReversePopulator newProductsReversePopulator;
    private String code;
    private String name;

    public NewProductsReversePopulator getNewProductsReversePopulator() {
        return newProductsReversePopulator;
    }
    public void setNewProductsReversePopulator(NewProductsReversePopulator newProductsReversePopulator) {
        this.newProductsReversePopulator = newProductsReversePopulator;
    }


    private ModelService modelService;

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public NewProductsPopulatorApi getNewProductsPopulatorApi() {
        return newProductsPopulatorApi;
    }

    public void setNewProductsPopulatorApi(NewProductsPopulatorApi newProductsPopulatorApi) {
        this.newProductsPopulatorApi = newProductsPopulatorApi;
    }

    public NewProductsService getNewProductsService() {
        return newProductsService;
    }

    public void setNewProductsService(NewProductsService newProductsService) {
        this.newProductsService = newProductsService;
    }

    @Resource(name = "newProductsDataConverterApi")
    private Converter<NewProductsModel,NewProductsDataWSDTO > newProductsDataConverterApi;

    @Override
    public List<NewProductsDataWSDTO> getSpecificProductDetailsApi(String code) {
        LOG.info("######### ProductFacadeImpl #############");
        final List<NewProductsDataWSDTO> product = new ArrayList<NewProductsDataWSDTO>();
        final List<NewProductsModel> model = getNewProductsService().getSpecificNewProductsDetails(code);
        final List<NewProductsDataWSDTO> newProduct = newProductsDataConverterApi.convertAll(model);
        product.addAll(newProduct);
        return product;
    }
    @Override
    public void removeProduct(final String code) {

        LOG.info("-----------remove product-------");
        getNewProductsService().removenewProducts(code);

    }

    @Override
    public List<NewProductsDataWSDTO> getAllProductsDetails() {
        LOG.info("####################### newProductFacadeImpl ###################");
        final List<NewProductsDataWSDTO> products = new ArrayList<NewProductsDataWSDTO>();
        final List<NewProductsModel> model = getNewProductsService().getNewProductsDetails();
        final List<NewProductsDataWSDTO> newProducts= newProductsDataConverterApi.convertAll(model);
        products.addAll(newProducts );
        return products;
    }

    @Override
    public void createProducts(NewProductsData newProducts) {
        final NewProductsModel newProductsModel=getModelService().create(NewProductsModel.class);
        getNewProductsReversePopulator().populate(newProducts,newProductsModel);
        newProductsService.createnewProducts(newProductsModel);
    }

    @Override
    public void updateNewProducts(String code, String name) {
        LOG.info("Update NewProducts");
        getNewProductsService().updateNewProducts(code, name);
    }


}