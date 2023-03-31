/**
 *
 */
package org.myshoestore.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerBatchContext;
import de.hybris.platform.solrfacetsearch.indexer.spi.InputDocument;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractValueResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;


/**
 * @author Administrator
 *
 */
public class FirstNameDisplayValueResolver extends AbstractValueResolver<CustomerModel, Object, Object>
{

	private static final String FIRST_NAME = null;
	private static final String LAST_NAME = null;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	@Override
	protected void addFieldValues(final InputDocument document, final IndexerBatchContext arg1,
			final IndexedProperty indexedProperty, final CustomerModel arg03,
			final ValueResolverContext<Object, Object> resolverContext) throws FieldValueProviderException
	{
		final List<String> firstNames = new ArrayList<>();
		final List<CustomerModel> customers = getAllCustomers();

		customers.forEach(customer -> {
			final Optional<Map<String, String>> displayName = parseName(customer.getDisplayName());
			if (displayName.isPresent())
			{
				firstNames.add(displayName.get().get(FIRST_NAME));
			}

		});

		if (!firstNames.isEmpty())
		{
			document.addField(indexedProperty, firstNames, resolverContext.getFieldQualifier());
		}

	}

	protected Optional<Map<String, String>> parseName(final String fullName)
	{
		final Integer lastIndexOf = fullName.lastIndexOf(' ');
		if (lastIndexOf > 0)
		{
			final HashMap<String, String> displayName = new HashMap<>();
			displayName.put(FIRST_NAME, fullName.substring(0, lastIndexOf).trim());
			displayName.put(LAST_NAME, fullName.substring(lastIndexOf).trim());
			return Optional.of(displayName);
		}
		return Optional.empty();

	}

	protected List<CustomerModel> getAllCustomers()
	{
		final FlexibleSearchQuery fsq = new FlexibleSearchQuery("SELECT {PK} FROM {CUSTOMER}");
		final SearchResult<CustomerModel> result = flexibleSearchService.search(fsq);
		return result.getResult();
	}

}
