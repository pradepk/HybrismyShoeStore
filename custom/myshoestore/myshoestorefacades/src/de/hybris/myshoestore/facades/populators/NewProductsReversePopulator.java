package de.hybris.myshoestore.facades.populators;

import de.hybris.myshoestore.core.model.NewProductsModel;
import de.hybris.myshoestore.facades.NewProductsData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;



public class NewProductsReversePopulator implements Populator<NewProductsData, NewProductsModel>

{

    private static final Logger LOG = Logger.getLogger(NewProductsDataPopulator.class);

    @Override
    public void populate(final NewProductsData source, final NewProductsModel target) throws ConversionException
    {
        LOG.info("########## NewProductsDataPopulator ###########");
        Assert.notNull(source, "Parameter source can not be null");
        Assert.notNull(target, "Parameter target can not be null");
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setSize(source.getSize());
        target.setColor(source.getColor());
        target.setPurchaseDate(source.getPurchaseDate());
    }



    }



