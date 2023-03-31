package de.hybris.myshoestore.core.service;

import de.hybris.myshoestore.core.model.NewProductsModel;

import java.util.List;

public interface NewProductsService {
    public List <NewProductsModel>getNewProductsDetails();
    public List<NewProductsModel>getSpecificNewProductsDetails(final String code);
    public void createnewProducts(NewProductsModel newProductsModel);
    public void removenewProducts(final String code);
    public void updateNewProducts(final String code, final String name);

}
