package de.hybris.myshoestore.facades.NewProducts;

import de.hybris.myshoestore.facades.NewProductsData;
import de.hybris.platform.commercewebservicescommons.dto.NewProducts.NewProductsDataWSDTO;

import java.util.List;

public interface NewProductsFacade {
    public List<NewProductsData>getNewProductsDetails();
    public List<NewProductsData> getSpecificNewProductsDetails(final String code);
    public void createNewProducts(final NewProductsData newProductsData);
    public void removeNewProducts(final String code);
    public void updateNewProducts(final String code, final String name);



}
