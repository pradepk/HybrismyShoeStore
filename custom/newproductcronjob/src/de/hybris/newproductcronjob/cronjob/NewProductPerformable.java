
package de.hybris.newproductcronjob.cronjob;
import de.hybris.newproductcronjob.model.NewProductCronJobModel;
import org.apache.log4j.Logger;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.PerformResult;




public class NewProductPerformable extends AbstractJobPerformable<NewProductCronJobModel>
{
	private static final Logger LOG = Logger.getLogger(NewProductPerformable.class.getName());

	@Override
	public PerformResult perform(final NewProductCronJobModel cronJobModel)
	{
		LOG.info("**********************************");
		LOG.info("Hello World");
		LOG.info("**********************************");

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
}