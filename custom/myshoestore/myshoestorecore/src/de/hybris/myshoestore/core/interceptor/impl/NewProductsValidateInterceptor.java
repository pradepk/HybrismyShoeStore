package de.hybris.myshoestore.core.interceptor.impl;

import de.hybris.myshoestore.core.interceptor.ValidateInterceptor;
import de.hybris.myshoestore.core.model.NewProductsModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;


public class NewProductsValidateInterceptor implements ValidateInterceptor {

    private static final Logger LOG = Logger.getLogger(NewProductsValidateInterceptor.class);
    @Override
    public void onValidate(Object obj, InterceptorContext ctx) throws InterceptorException {
        LOG.info("######## NewProductsValidateInterceptor #############");

        if (obj instanceof NewProductsModel){
            final NewProductsModel model=(NewProductsModel) obj;
            if(StringUtils.isEmpty(model.getName())){
                throw new InterceptorException("Product name cant be empty");
            }



        }

    }
}
