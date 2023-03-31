package de.hybris.myshoestore.core.dao.impl;


import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.myshoestore.core.dao.NewProductsDao;
import de.hybris.myshoestore.core.model.NewProductsModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


public class NewProductsDaoImpl  implements NewProductsDao
{

    private FlexibleSearchService flexibleSearchService;
    private ModelService modelService;
    private static final Logger LOG = Logger.getLogger(NewProductsDaoImpl.class);
    @Required
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
    {
    this.flexibleSearchService = flexibleSearchService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }



    @Override
    public List<NewProductsModel> getNewProductsDetails()
    {

         String query = "SELECT {PK} FROM {NewProducts}";
         FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
        SearchResult searchResult = flexibleSearchService.search(searchQuery);//g
        return searchResult.getResult();
    }

    @Override
    public List<NewProductsModel> getSpecificNewProductsDetails(final String code) {
        LOG.info("##### NewProductsDaoImpl ######");
        final Map<String , Object> params = new HashMap<String,Object>();
        final String query = "SELECT {PK} FROM {NewProducts} WHERE {code}=?code";
        params.put("code",code);
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
        searchQuery.addQueryParameters(params);
        final SearchResult searchResult = flexibleSearchService.search(searchQuery);
        return searchResult.getResult() == null ?  Collections.emptyList():searchResult.getResult();



    }

    @Override
    public void createNewProducts(NewProductsModel newProductsModel) {
        LOG.info("####NewProductsDaoImpl####");
        getModelService().save(newProductsModel);

    }

    @Override
    public void removeNewProducts(List<NewProductsModel> newProducts) {
        LOG.info("####NewProductsDaoImpl####");
        validateParameterNotNull(newProducts,"Products Model cannot be null");
        getModelService().removeAll(newProducts);

    }


    @Override
    public void updateNewProducts(String code, String name) {
        LOG.info("####NewProductsDaoImpl####");
        validateParameterNotNull(name,"Name cannot be null");
        for (NewProductsModel newProductsModel:getSpecificNewProductsDetails(code)){
            if(newProductsModel.getCode().equals(code)){
//                NewProductsModel np= newProductsModel;
//                np.setName(name);
                getModelService().setAttributeValue(newProductsModel,"product name",name);
                getModelService().save(newProductsModel);
                getModelService().refresh(newProductsModel);
            }
        }



    }


}

