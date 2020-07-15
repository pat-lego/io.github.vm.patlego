package patlego.vm.github.io.datasource.impl;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import patlego.vm.github.io.datasource.KarafDB_DAO;
import patlego.vm.github.io.utils.DataResource;
import patlego.vm.github.io.utils.impl.DataResourceImpl;



@Component(
	service = KarafDB_DAO.class,
	immediate = true
)
public class KarafDB_DAOImpl implements KarafDB_DAO {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Reference(target = ("(&(objectclass=javax.sql.DataSource)(dataSourceName=karafdb))"))
	private DataSource dataSource;
	
	@Override
	public DataResource getDataResource() {
		return new DataResourceImpl(dataSource);
	}

	@Activate
	protected void activate() {
		logger.info(String.format("%s service is now active", getClass().getName()));
	}

	@Deactivate
	protected void deactivate() {
		this.dataSource = null;
		logger.info(String.format("%s service is now deactivated", getClass().getName()));
	}

	@Modified
	protected void Modified() {
		logger.info(String.format("%s service has been modified", getClass().getName()));
	}
    
    
}