package com.crimsonlogic.sbangularjs.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.sbangularjs.model.HouseholdAppliance;

@Repository("householdApplianceDAO")
public class HouseholdApplianceDAOImpl extends AbstractDao<Integer, HouseholdAppliance> implements HouseholdApplianceDAO {

	static final Logger LOG = LoggerFactory.getLogger(HouseholdApplianceDAOImpl.class);
	
	@Override
	public List<HouseholdAppliance> getAllHouseholdAppliances() {
		CriteriaQuery<HouseholdAppliance> crit = createEntityCriteria();
		Root<HouseholdAppliance> root = crit.from(HouseholdAppliance.class);
		crit.select(root);

		List<Order> orderList = new ArrayList<Order>();
		orderList.add(getCriteriaBuilder().desc(root.get("dateBought")));
		crit.orderBy(orderList);

		Query<HouseholdAppliance> query = getSession().createQuery(crit);
		return query.getResultList();
	}

	@Override
	public void addHouseholdAppliance(HouseholdAppliance householdAppliance) {
		try {
			Transaction tx = getSession().beginTransaction();
			getSession().save(householdAppliance);
			tx.commit();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

	}

	@Override
	public void updateHouseholdAppliance(HouseholdAppliance householdAppliance) {
		try {
			Transaction tx = getSession().beginTransaction();
			getSession().update(householdAppliance);
			tx.commit();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

	}

	@Override
	public void deleteHouseholdAppliance(String serialNumber) {
		HouseholdAppliance householdAppliance = getHouseholdAppliancesBySerialNumber(serialNumber);
		if (null != householdAppliance) {
			try {
				Transaction tx = getSession().beginTransaction();
				getSession().delete(householdAppliance);
				tx.commit();
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}

	}

	public HouseholdAppliance getHouseholdAppliancesBySerialNumber(String SerialNumber) {
		HouseholdAppliance householdAppliance = null;
		try {
			CriteriaQuery<HouseholdAppliance> crit = createEntityCriteria();
			Root<HouseholdAppliance> root = crit.from(HouseholdAppliance.class);
			crit.select(root).where(getCriteriaBuilder().equal(root.get("serialNumber"), SerialNumber));
			Query<HouseholdAppliance> query = getSession().createQuery(crit);
			householdAppliance =  query.getSingleResult();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return householdAppliance;
	}

	@Override
	public List<HouseholdAppliance> findAllHouseholdAppliances(HouseholdAppliance householdAppliance) {
		CriteriaQuery<HouseholdAppliance> crit = createEntityCriteria();
		Root<HouseholdAppliance> root = crit.from(HouseholdAppliance.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(householdAppliance.getSerialNumber() != null && !householdAppliance.getSerialNumber().isEmpty())
		{
		predicates.add(getCriteriaBuilder().like(getCriteriaBuilder().upper(root.get("serialNumber")), "%"+householdAppliance.getSerialNumber().toUpperCase()+"%"));
		}
		
		if(householdAppliance.getBrand() !=null && !householdAppliance.getBrand().isEmpty())
		{
			predicates.add(getCriteriaBuilder().like(getCriteriaBuilder().upper(root.get("brand")), "%"+householdAppliance.getBrand().toUpperCase()+"%"));
		}
		if(householdAppliance.getModel() !=null && !householdAppliance.getModel().isEmpty())
		{
			predicates.add(getCriteriaBuilder().like(getCriteriaBuilder().upper(root.get("model")), "%"+householdAppliance.getModel().toUpperCase()+"%"));
		}
		if(householdAppliance.getStatus() !=null && !householdAppliance.getStatus().isEmpty())
		{
			predicates.add(getCriteriaBuilder().like(getCriteriaBuilder().upper(root.get("status")), "%"+householdAppliance.getStatus().toUpperCase()+"%"));
		}
		
		if(householdAppliance.getDateBought() !=null)
		{
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			Date todayWithZeroTime;
			try {
				todayWithZeroTime = formatter.parse(formatter.format(householdAppliance.getDateBought()));
				java.sql.Date myDate = new java.sql.Date(todayWithZeroTime.getTime());    
				predicates.add(getCriteriaBuilder().equal(root.get("dateBought"), getCriteriaBuilder().literal(myDate)));
			} catch (ParseException e) {
				LOG.error(e.getMessage(), e);
			}
			
		}
		
		crit.select(root).where(predicates.toArray(new Predicate[] {}));
		
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(getCriteriaBuilder().desc(root.get("dateBought")));
		crit.orderBy(orderList);

		Query<HouseholdAppliance> query = getSession().createQuery(crit);
		return query.getResultList();
	}

}
