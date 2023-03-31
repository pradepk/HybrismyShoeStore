package de.hybris.myshoestore.facades.NewProducts.Impl;

import de.hybris.myshoestore.core.model.NewProductsModel;
import de.hybris.myshoestore.core.service.NewProductsService;
import de.hybris.myshoestore.facades.NewProducts.NewProductsFacade;
import de.hybris.myshoestore.facades.NewProductsData;
import de.hybris.myshoestore.facades.populators.NewProductsReversePopulator;
import de.hybris.platform.commercewebservicescommons.dto.NewProducts.NewProductsDataWSDTO;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import java.util.ArrayList;
import javax.annotation.Resource;
import java.util.List;


import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

public class NewProductsFacadeImpl implements NewProductsFacade {
    private NewProductsService newProductsService;
    private ModelService modelService;

    private NewProductsReversePopulator newProductsReversePopulator;

    public NewProductsReversePopulator getNewProductsReversePopulator(){
        return newProductsReversePopulator;
    }

    public void setNewProductsReversePopulator(NewProductsReversePopulator newProductsReversePopulator){
        this.newProductsReversePopulator = newProductsReversePopulator;
    }
    public void setNewProductsDataConverter(Converter<NewProductsModel, NewProductsData> newProductsDataConverter)
    {
        this.newProductsDataConverter = newProductsDataConverter;
    }

    public ModelService getModelService() {
        return modelService;
    }
    public  void setModelService(ModelService modelService){
        this.modelService=modelService;
    }
    @Resource(name = "newProductsDataConverter")
    private Converter<NewProductsModel, NewProductsData> newProductsDataConverter;


    public NewProductsService getNewProductsService(){
        return newProductsService;
    }

    @Required
    public void setNewProductsService(final NewProductsService newProductsService)
    {
        this.newProductsService = newProductsService;
    }

    @Override
    public List<NewProductsData> getNewProductsDetails() {

        final List<NewProductsData> products = new ArrayList<NewProductsData>();
        final List<NewProductsModel> model = newProductsService.getNewProductsDetails();
        final List<NewProductsData> newProductsData = newProductsDataConverter.convertAll(model);
        products.addAll(newProductsData);
        return products;
    }

    @Override
    public List<NewProductsData> getSpecificNewProductsDetails(String code) {
        final List<NewProductsData> newProductsData = new ArrayList<NewProductsData>();
        final List<NewProductsModel> model = getNewProductsService().getSpecificNewProductsDetails(code);
        final List<NewProductsData> newProductsData1 = newProductsDataConverter.convertAll(model);
        newProductsData.addAll(newProductsData);
        return newProductsData;
    }

    @Override
    public void createNewProducts(NewProductsData newProductsData) {
        final NewProductsModel newProductsModel = getModelService().create(NewProductsModel.class);
        getNewProductsReversePopulator().populate(newProductsData, newProductsModel);
        getNewProductsService().createnewProducts(newProductsModel);

    }

    @Override
    public void removeNewProducts(String code) {
        getNewProductsService().removenewProducts(code);

    }

    @Override
    public void updateNewProducts(String code, String name) {
        getNewProductsService().updateNewProducts(code,name);
    }


}

