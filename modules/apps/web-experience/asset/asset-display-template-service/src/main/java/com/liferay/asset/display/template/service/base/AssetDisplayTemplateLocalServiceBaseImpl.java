/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.asset.display.template.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.display.template.model.AssetDisplayTemplate;
import com.liferay.asset.display.template.service.AssetDisplayTemplateLocalService;
import com.liferay.asset.display.template.service.persistence.AssetDisplayTemplatePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the asset display template local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.asset.display.template.service.impl.AssetDisplayTemplateLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.asset.display.template.service.impl.AssetDisplayTemplateLocalServiceImpl
 * @see com.liferay.asset.display.template.service.AssetDisplayTemplateLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class AssetDisplayTemplateLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements AssetDisplayTemplateLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.asset.display.template.service.AssetDisplayTemplateLocalServiceUtil} to access the asset display template local service.
	 */

	/**
	 * Adds the asset display template to the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetDisplayTemplate the asset display template
	 * @return the asset display template that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetDisplayTemplate addAssetDisplayTemplate(
		AssetDisplayTemplate assetDisplayTemplate) {
		assetDisplayTemplate.setNew(true);

		return assetDisplayTemplatePersistence.update(assetDisplayTemplate);
	}

	/**
	 * Creates a new asset display template with the primary key. Does not add the asset display template to the database.
	 *
	 * @param assetDisplayTemplateId the primary key for the new asset display template
	 * @return the new asset display template
	 */
	@Override
	public AssetDisplayTemplate createAssetDisplayTemplate(
		long assetDisplayTemplateId) {
		return assetDisplayTemplatePersistence.create(assetDisplayTemplateId);
	}

	/**
	 * Deletes the asset display template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetDisplayTemplateId the primary key of the asset display template
	 * @return the asset display template that was removed
	 * @throws PortalException if a asset display template with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AssetDisplayTemplate deleteAssetDisplayTemplate(
		long assetDisplayTemplateId) throws PortalException {
		return assetDisplayTemplatePersistence.remove(assetDisplayTemplateId);
	}

	/**
	 * Deletes the asset display template from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetDisplayTemplate the asset display template
	 * @return the asset display template that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AssetDisplayTemplate deleteAssetDisplayTemplate(
		AssetDisplayTemplate assetDisplayTemplate) throws PortalException {
		return assetDisplayTemplatePersistence.remove(assetDisplayTemplate);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(AssetDisplayTemplate.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return assetDisplayTemplatePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.display.template.model.impl.AssetDisplayTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return assetDisplayTemplatePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.display.template.model.impl.AssetDisplayTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return assetDisplayTemplatePersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return assetDisplayTemplatePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return assetDisplayTemplatePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public AssetDisplayTemplate fetchAssetDisplayTemplate(
		long assetDisplayTemplateId) {
		return assetDisplayTemplatePersistence.fetchByPrimaryKey(assetDisplayTemplateId);
	}

	/**
	 * Returns the asset display template with the primary key.
	 *
	 * @param assetDisplayTemplateId the primary key of the asset display template
	 * @return the asset display template
	 * @throws PortalException if a asset display template with the primary key could not be found
	 */
	@Override
	public AssetDisplayTemplate getAssetDisplayTemplate(
		long assetDisplayTemplateId) throws PortalException {
		return assetDisplayTemplatePersistence.findByPrimaryKey(assetDisplayTemplateId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(assetDisplayTemplateLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AssetDisplayTemplate.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"assetDisplayTemplateId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(assetDisplayTemplateLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(AssetDisplayTemplate.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"assetDisplayTemplateId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(assetDisplayTemplateLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AssetDisplayTemplate.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"assetDisplayTemplateId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return assetDisplayTemplateLocalService.deleteAssetDisplayTemplate((AssetDisplayTemplate)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return assetDisplayTemplatePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the asset display templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.asset.display.template.model.impl.AssetDisplayTemplateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset display templates
	 * @param end the upper bound of the range of asset display templates (not inclusive)
	 * @return the range of asset display templates
	 */
	@Override
	public List<AssetDisplayTemplate> getAssetDisplayTemplates(int start,
		int end) {
		return assetDisplayTemplatePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of asset display templates.
	 *
	 * @return the number of asset display templates
	 */
	@Override
	public int getAssetDisplayTemplatesCount() {
		return assetDisplayTemplatePersistence.countAll();
	}

	/**
	 * Updates the asset display template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param assetDisplayTemplate the asset display template
	 * @return the asset display template that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetDisplayTemplate updateAssetDisplayTemplate(
		AssetDisplayTemplate assetDisplayTemplate) {
		return assetDisplayTemplatePersistence.update(assetDisplayTemplate);
	}

	/**
	 * Returns the asset display template local service.
	 *
	 * @return the asset display template local service
	 */
	public AssetDisplayTemplateLocalService getAssetDisplayTemplateLocalService() {
		return assetDisplayTemplateLocalService;
	}

	/**
	 * Sets the asset display template local service.
	 *
	 * @param assetDisplayTemplateLocalService the asset display template local service
	 */
	public void setAssetDisplayTemplateLocalService(
		AssetDisplayTemplateLocalService assetDisplayTemplateLocalService) {
		this.assetDisplayTemplateLocalService = assetDisplayTemplateLocalService;
	}

	/**
	 * Returns the asset display template persistence.
	 *
	 * @return the asset display template persistence
	 */
	public AssetDisplayTemplatePersistence getAssetDisplayTemplatePersistence() {
		return assetDisplayTemplatePersistence;
	}

	/**
	 * Sets the asset display template persistence.
	 *
	 * @param assetDisplayTemplatePersistence the asset display template persistence
	 */
	public void setAssetDisplayTemplatePersistence(
		AssetDisplayTemplatePersistence assetDisplayTemplatePersistence) {
		this.assetDisplayTemplatePersistence = assetDisplayTemplatePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.asset.display.template.model.AssetDisplayTemplate",
			assetDisplayTemplateLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.asset.display.template.model.AssetDisplayTemplate");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AssetDisplayTemplateLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AssetDisplayTemplate.class;
	}

	protected String getModelClassName() {
		return AssetDisplayTemplate.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = assetDisplayTemplatePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = AssetDisplayTemplateLocalService.class)
	protected AssetDisplayTemplateLocalService assetDisplayTemplateLocalService;
	@BeanReference(type = AssetDisplayTemplatePersistence.class)
	protected AssetDisplayTemplatePersistence assetDisplayTemplatePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}