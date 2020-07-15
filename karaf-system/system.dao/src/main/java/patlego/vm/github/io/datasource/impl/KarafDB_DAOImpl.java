package patlego.vm.github.io.datasource.impl;

import java.sql.Connection;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import patlego.vm.github.io.datasource.KarafDB_DAO;

@Component(
	service = KarafDB_DAO.class,
	immediate = true
)
public class KarafDB_DAOImpl implements KarafDB_DAO {

	@Reference(target = ("(&(objectclass=javax.sql.DataSource)(dataSourceName=karafdb))"))
	private DataSource dataSource;
	
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Activate
	protected void activate() {
	}

	@Deactivate
	protected void deactivate() {
		this.dataSource = null;
	}

	@Modified
	protected void Modified() {
	}
    
    
}