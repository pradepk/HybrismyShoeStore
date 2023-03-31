package de.hybris.myshoestore.facades.NewProducts;

import de.hybris.myshoestore.facades.NewProductsData;
import de.hybris.platform.commercewebservicescommons.dto.NewProducts.NewProductsDataWSDTO;

import java.util.List;

public interface NewProductsFacadeApi {
    public List<NewProductsDataWSDTO> getSpecificProductDetailsApi(final String code);

    public void removeProduct(String code);

    public List<NewProductsDataWSDTO> getAllProductsDetails();

    public void createProducts(NewProductsData newProducts);


    public void updateNewProducts(String code, String description);
}