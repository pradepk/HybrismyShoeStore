package de.hybris.myshoestore.core.service.impl;

import de.hybris.myshoestore.core.dao.NewProductsDao;
import de.hybris.myshoestore.core.model.NewProductsModel;
import de.hybris.myshoestore.core.service.NewProductsService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import java.util.List;
import org.apache.log4j.Logger;

@Service
public class NewProductsServiceImpl implements NewProductsService {
    private NewProductsDao newProductsDao;
    private static final Logger LOG = Logger.getLogger(NewProductsServiceImpl.class);





    @Required
    public void setNewProductsDao(final NewProductsDao newProductsDao)
    {
        this.newProductsDao = newProductsDao;
    }

    @Override
    public List<NewProductsModel> getNewProductsDetails()
    {
        LOG.info("###### NewProductsServiceImpl ######");
        return newProductsDao.getNewProductsDetails();
    }

    @Override
    public List<NewProductsModel> getSpecificNewProductsDetails(String code) {
        LOG.info("###### NewProductsServiceImpl ######");
        return newProductsDao.getSpecificNewProductsDetails(code);
    }

    @Override
    public void createnewProducts(NewProductsModel newProductsModel) {
        LOG.info("###### NewProductsServiceImpl ######");
        newProductsDao.createNewProducts(newProductsModel);
    }

    @Override
    public void removenewProducts(String code) {
        LOG.info("###### NewProductsServiceImpl ######");
        List<NewProductsModel> newProductsModels=getSpecificNewProductsDetails(code);
        newProductsDao.removeNewProducts(newProductsModels);

    }

    @Override
    public void updateNewProducts(String code, String name) {
        LOG.info("###### NewProductsServiceImpl ######");
        newProductsDao.updateNewProducts(code,name);
    }
}

