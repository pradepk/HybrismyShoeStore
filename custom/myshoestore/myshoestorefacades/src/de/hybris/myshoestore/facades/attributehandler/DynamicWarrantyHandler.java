package de.hybris.myshoestore.facades.attributehandler;

import de.hybris.myshoestore.core.model.NewProductsModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

public class DynamicWarrantyHandler implements DynamicAttributeHandler<Integer,NewProductsModel>
{
   public static int warranty=0;

   @Override
   public Integer get(final NewProductsModel model)

   {
      final int purchaseDate = model.getPurchaseDate();


      warranty = purchaseDate+5;


      return warranty;

   }

   @Override
   public void set(final NewProductsModel model, final Integer val)
   {
      if (val != null)
      {
         throw new UnsupportedOperationException();
      }
   }
}