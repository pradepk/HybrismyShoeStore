package de.hybris.myshoestore.core.dao;

import de.hybris.myshoestore.core.model.NewProductsModel;

import java.util.List;

public interface NewProductsDao {
   public List<NewProductsModel> getNewProductsDetails();
   public List<NewProductsModel>getSpecificNewProductsDetails(final String code);
   public void createNewProducts(NewProductsModel newProductsModel);
   public void removeNewProducts(List<NewProductsModel> newProducts);

   public void updateNewProducts(final String code,final String name);



}
