/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.modelibra.wicket.security;

import java.io.Serializable;

import org.modelibra.IEntity;
import org.modelibra.DomainModel;
import org.modelibra.config.ConceptConfig;
import org.modelibra.config.ModelConfig;
import org.modelibra.config.NeighborConfig;
import org.modelibra.config.PropertyConfig;

/**
 * <p>
 * Access point determines if a user has display and update rights for concepts,
 * properties and neighbors. The display of concepts, properties and neighbors
 * may be done without sign in. Without sign in all users have the same display
 * rights determined by the display configurations of concepts, properties and
 * neighbors. With sign in, display and update rights are determined by security
 * roles. Security roles are admin, manager, advanced, regular and casual.
 * </p>
 * 
 * <p>
 * The admin, manager and advanced roles have all display rights (true even when
 * false in display configurations of concepts, properties and neighbors). The
 * regular and casual roles have display rights only for display configurations
 * of concepts, properties and neighbors that are true.
 * </p>
 * 
 * <p>
 * The admin and manager roles have all add rights (true even when false in add
 * configurations of concepts). The advanced, regular and casual roles have add
 * rights only for add configurations of concepts that are true.
 * </p>
 * 
 * <p>
 * The admin role has all remove rights (true even when false in remove
 * configurations of concepts). The manager, advanced and regular roles have
 * remove rights only for remove configurations of concepts that are true. The
 * casual role does not have remove rights (false even when true in remove
 * configurations of concepts).
 * </p>
 * 
 * <p>
 * The admin and manager roles have all update rights (true even when false in
 * update configurations of properties and neighbors). The advanced, regular and
 * casual roles have update rights only for update configurations of properties
 * and neighbors that are true.
 * </p>
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-11-20
 */
@SuppressWarnings("serial")
public class AccessPoint implements Serializable {

	public static final String CODE = "code";

	public static final String PASSWORD = "password";

	public static final String ROLE = "role";

	public static final String GUEST = "guest";

	public static final String ADMIN = "admin";

	public static final String MANAGER = "manager";

	public static final String ADVANCED = "advanced";

	public static final String REGULAR = "regular";

	public static final String CASUAL = "casual";

	public static final String ADD = "add";

	public static final String REMOVE = "remove";

	public static final String UPDATE = "update";

	private String codePropertyCode;

	private String rolePropertyCode;

	/**
	 * Constructs an access point.
	 */
	public AccessPoint() {
		this(CODE, ROLE);
	}

	/**
	 * Constructs an access point.
	 * 
	 * @param codePropertyCode
	 *            code property code
	 * @param rolePropertyCode
	 *            role property code
	 */
	public AccessPoint(String codePropertyCode, String rolePropertyCode) {
		super();
		this.codePropertyCode = codePropertyCode;
		this.rolePropertyCode = rolePropertyCode;
	}

	/**
	 * Checks if a concept display is allowed. Users with the admin, manager or
	 * advanced role have all display rights. For other users , the display
	 * restriction is determined by the concept configuration. If there is no
	 * sign in, the display restriction is determined by the concept
	 * configuration.
	 * 
	 * @param appSession
	 *            app session
	 * @param conceptConfig
	 *            concept configuration
	 * @return <code>true</code> if display is allowed
	 */
	public boolean isConceptDisplayAllowed(AppSession appSession,
			ConceptConfig conceptConfig) {
		boolean allowed = true;
		ModelConfig modelConfig = conceptConfig.getModelConfig();
		if (modelConfig.getDomainConfig().isSignin()) {
			if (!appSession.isUserSignedIn()) {
				if (!conceptConfig.isDisplay()) {
					allowed = false;
				}
			} else {
				IEntity<?> signedInUser = appSession.getSignedInUser();
				if (signedInUser != null) {
					DomainModel model = (DomainModel) signedInUser.getModel();
					Object roleObject = model.getModelMeta().getProperty(
							signedInUser, rolePropertyCode);
					if (roleObject != null) {
						if (roleObject instanceof String) {
							String securityRole = (String) roleObject;
							if (!securityRole.equals(ADMIN)
									&& !securityRole.equals(MANAGER)
									&& !securityRole.equals(ADVANCED)) {
								if (!conceptConfig.isDisplay()) {
									allowed = false;
								}
							}
						}
					}
				}
			}
		} else if (!conceptConfig.isDisplay()) {
			allowed = false;
		}
		return allowed;
	}

	/**
	 * Checks if a concept update (add, remove and update) is allowed. Users
	 * with ADMIN role have all update rights. For other users, the concept
	 * update action restrictions are determined by the concept configuration.
	 * If there is no sign in, the update restrictions are determined by the
	 * concept configuration.
	 * 
	 * @param appSession
	 *            app session
	 * @param conceptConfig
	 *            concept configuration
	 * @return <code>true</code> if update is allowed
	 */
	public boolean isConceptUpdateAllowed(AppSession appSession,
			ConceptConfig conceptConfig) {
		boolean allowed = true;
		ModelConfig modelConfig = conceptConfig.getModelConfig();
		if (modelConfig.getDomainConfig().isSignin()) {
			if (!appSession.isUserSignedIn()) {
				allowed = false;
			} else {
				IEntity<?> signedInUser = appSession.getSignedInUser();
				if (signedInUser != null) {
					DomainModel model = (DomainModel) signedInUser.getModel();
					Object roleObject = model.getModelMeta().getProperty(
							signedInUser, rolePropertyCode);
					if (roleObject != null) {
						if (roleObject instanceof String) {
							String securityRole = (String) roleObject;
							if (!securityRole.equals(ADMIN)) {
								if (!conceptConfig.isAdd()
										&& !conceptConfig.isUpdate()
										&& !conceptConfig.isRemove()) {
									allowed = false;
								}
							}
						}
					}
				}
			}
		} else if (!conceptConfig.isAdd() && !conceptConfig.isUpdate()
				&& !conceptConfig.isRemove()) {
			allowed = false;
		}
		return allowed;
	}

	/**
	 * Checks if a concept update action (add, remove or update) is allowed.
	 * Users with the ADMIN role have all update rights. Users with the MANAGER
	 * role cannot remove an entity if the remove action is not allowed for the
	 * concept. For other users, the concept update action restrictions are
	 * determined by the concept configuration. If there is no sign in, the
	 * update restrictions are determined by the concept configuration.
	 * 
	 * @param appSession
	 *            app session
	 * @param conceptConfig
	 *            concept configuration
	 * @param action
	 *            action
	 * @return <code>true</code> if an update action is allowed
	 */
	public boolean isConceptUpdateAllowed(AppSession appSession,
			ConceptConfig conceptConfig, String action) {
		boolean allowed = true;
		ModelConfig modelConfig = conceptConfig.getModelConfig();
		if (modelConfig.getDomainConfig().isSignin()) {
			if (!appSession.isUserSignedIn()) {
				allowed = false;
			} else {
				IEntity<?> signedInUser = appSession.getSignedInUser();
				if (signedInUser != null) {
					DomainModel model = (DomainModel) signedInUser.getModel();
					Object roleObject = model.getModelMeta().getProperty(
							signedInUser, rolePropertyCode);
					if (roleObject != null) {
						if (roleObject instanceof String) {
							String securityRole = (String) roleObject;
							if (!securityRole.equals(ADMIN)) {
								if (securityRole.equals(MANAGER)) {
									if (action.equals(REMOVE)
											&& !conceptConfig.isRemove()) {
										allowed = false;
									}
								} else if (action.equals(ADD)
										&& !conceptConfig.isAdd()) {
									allowed = false;
								} else if (action.equals(UPDATE)
										&& !conceptConfig.isUpdate()) {
									allowed = false;
								} else if (action.equals(REMOVE)) {
									if (!conceptConfig.isRemove()) {
										allowed = false;
									} else if (securityRole.equals(CASUAL)) {
										allowed = false;
									}
								}
							}
						}
					}
				}
			}
		} else if (action.equals(ADD) && !conceptConfig.isAdd()) {
			allowed = false;
		} else if (action.equals(UPDATE) && !conceptConfig.isUpdate()) {
			allowed = false;
		} else if (action.equals(REMOVE) && !conceptConfig.isRemove()) {
			allowed = false;
		}
		return allowed;
	}

	/**
	 * Checks if a concept update action (add, remove or update) is allowed.
	 * Users with the ADMIN role have all update rights. Users with the MANAGER
	 * role cannot remove an entity if the remove action is not allowed for the
	 * concept. For other users, the concept update action restrictions are
	 * determined by the concept configuration, except for their own entity. If
	 * there is no sign in, the update restrictions are determined by the
	 * concept configuration.
	 * 
	 * @param appSession
	 *            app session
	 * @param conceptConfig
	 *            concept configuration
	 * @param user
	 *            user entity
	 * @param action
	 *            action
	 * @return <code>true</code> if an update action is allowed
	 */
	public boolean isConceptUpdateAllowed(AppSession appSession,
			ConceptConfig conceptConfig, IEntity<?> user, String action) {
		boolean allowed = true;
		ModelConfig modelConfig = conceptConfig.getModelConfig();
		if (modelConfig.getDomainConfig().isSignin()) {
			if (!appSession.isUserSignedIn()) {
				allowed = false;
			} else {
				IEntity<?> signedInUser = appSession.getSignedInUser();
				if (signedInUser != null) {
					DomainModel model = (DomainModel) signedInUser.getModel();
					Object roleObject = model.getModelMeta().getProperty(
							signedInUser, rolePropertyCode);
					if (roleObject != null) {
						if (roleObject instanceof String) {
							String securityRole = (String) roleObject;
							if (!securityRole.equals(ADMIN)) {
								if (securityRole.equals(MANAGER)) {
									if (action.equals(REMOVE)
											&& !conceptConfig.isRemove()) {
										allowed = false;
									}
								} else {
									String signinConcept = modelConfig
											.getDomainConfig()
											.getSigninConcept();
									if (conceptConfig.getCode().equals(
											signinConcept)) {
										if (user != null) {
											String userCode = (String) user
													.getProperty(codePropertyCode);
											if (userCode != null) {
												String signedInMemberCode = (String) signedInUser
														.getProperty(codePropertyCode);
												if (signedInMemberCode != null) {
													if (action.equals(ADD)) {
														if (!conceptConfig
																.isAdd()) {
															allowed = false;
														} else if (securityRole
																.equals(CASUAL)) {
															if (signedInMemberCode
																	.equals(GUEST)) {
																allowed = true;
															}
														} else if (!signedInMemberCode
																.equals(userCode)) {
															allowed = false;
														}
													} else if (action
															.equals(REMOVE)) {
														if (!conceptConfig
																.isRemove()) {
															allowed = false;
														} else if (securityRole
																.equals(CASUAL)) {
															allowed = false;
														}
													}
												} else {
													allowed = false;
												}
											} else {
												allowed = false;
											}
										} else {
											allowed = false;
										}
									} else if (action.equals(ADD)
											&& !conceptConfig.isAdd()) {
										allowed = false;
									} else if (action.equals(UPDATE)
											&& !conceptConfig.isUpdate()) {
										allowed = false;
									} else if (action.equals(REMOVE)) {
										if (!conceptConfig.isRemove()) {
											allowed = false;
										} else if (securityRole.equals(CASUAL)) {
											allowed = false;
										}
									}
								}
							}
						}
					}
				}
			}
		} else if (action.equals(ADD) && !conceptConfig.isAdd()) {
			allowed = false;
		} else if (action.equals(UPDATE) && !conceptConfig.isUpdate()) {
			allowed = false;
		} else if (action.equals(REMOVE) && !conceptConfig.isRemove()) {
			allowed = false;
		}
		return allowed;
	}

	/**
	 * Checks if a property display is allowed. Users with the ADMIN, MANAGER or
	 * ADVANCED role have all display rights. For other users, the display
	 * restriction is determined by the property configuration. If there is no
	 * sign in, the display restriction is determined by the property
	 * configuration.
	 * 
	 * @param appSession
	 *            app session
	 * @param propertyConfig
	 *            property configuration
	 * @return <code>true</code> if display is allowed
	 */
	public boolean isPropertyDisplayAllowed(AppSession appSession,
			PropertyConfig propertyConfig) {
		boolean allowed = true;
		ModelConfig modelConfig = propertyConfig.getConceptConfig()
				.getModelConfig();
		if (modelConfig.getDomainConfig().isSignin()) {
			if (!appSession.isUserSignedIn()) {
				if (!propertyConfig.isDisplay()) {
					allowed = false;
				}
			} else {
				IEntity<?> signedInUser = appSession.getSignedInUser();
				if (signedInUser != null) {
					DomainModel model = (DomainModel) signedInUser.getModel();
					Object roleObject = model.getModelMeta().getProperty(
							signedInUser, rolePropertyCode);
					if (roleObject != null) {
						if (roleObject instanceof String) {
							String securityRole = (String) roleObject;
							if (!securityRole.equals(ADMIN)
									&& !securityRole.equals(MANAGER)
									&& !securityRole.equals(ADVANCED)) {
								if (!propertyConfig.isDisplay()) {
									allowed = false;
								}
							}
						}
					}
				}
			}
		} else if (!propertyConfig.isDisplay()) {
			allowed = false;
		}
		return allowed;
	}

	/**
	 * Checks if a property update is allowed. Users with the ADMIN or MANAGER
	 * role have all update rights. For other users, the update restriction is
	 * determined by the property configuration. If there is no sign in, the
	 * update restriction is determined by the property configuration.
	 * 
	 * @param appSession
	 *            app session
	 * @param propertyConfig
	 *            property configuration
	 * @return <code>true</code> if update is allowed
	 */
	public boolean isPropertyUpdateAllowed(AppSession appSession,
			PropertyConfig propertyConfig) {
		boolean allowed = true;
		ModelConfig modelConfig = propertyConfig.getConceptConfig()
				.getModelConfig();
		if (modelConfig.getDomainConfig().isSignin()) {
			if (!appSession.isUserSignedIn()) {
				String conceptCode = propertyConfig.getConceptConfig()
						.getCode();
				if (!conceptCode.equals("Applicant")) {
					allowed = false;
				}
			} else {
				IEntity<?> signedInUser = appSession.getSignedInUser();
				if (signedInUser != null) {
					DomainModel model = (DomainModel) signedInUser.getModel();
					Object roleObject = model.getModelMeta().getProperty(
							signedInUser, rolePropertyCode);
					if (roleObject != null) {
						if (roleObject instanceof String) {
							String securityRole = (String) roleObject;
							if (!securityRole.equals(ADMIN)
									&& !securityRole.equals(MANAGER)) {
								if (!propertyConfig.isUpdate()) {
									allowed = false;
								}
							}
						}
					}
				}
			}
		} else if (!propertyConfig.isUpdate()) {
			allowed = false;
		}
		return allowed;
	}

	/**
	 * Checks if a neighbor display is allowed. Users with the ADMIN, MANAGER or
	 * ADVANCED role have all display rights. For other users, the display
	 * restriction is determined by the property configuration. If there is no
	 * sign in, the display restriction is determined by the neighbor
	 * configuration.
	 * 
	 * @param appSession
	 *            app session
	 * @param neighborConfig
	 *            neighbor configuration
	 * @return <code>true</code> if display is allowed
	 */
	public boolean isNeighborDisplayAllowed(AppSession appSession,
			NeighborConfig neighborConfig) {
		boolean allowed = true;
		ModelConfig modelConfig = neighborConfig.getConceptConfig()
				.getModelConfig();
		if (modelConfig.getDomainConfig().isSignin()) {
			if (!appSession.isUserSignedIn()) {
				if (!neighborConfig.isDisplay()) {
					allowed = false;
				}
			} else {
				IEntity<?> signedInUser = appSession.getSignedInUser();
				if (signedInUser != null) {
					DomainModel model = (DomainModel) signedInUser.getModel();
					Object roleObject = model.getModelMeta().getProperty(
							signedInUser, rolePropertyCode);
					if (roleObject != null) {
						if (roleObject instanceof String) {
							String securityRole = (String) roleObject;
							if (!securityRole.equals(ADMIN)
									&& !securityRole.equals(MANAGER)
									&& !securityRole.equals(ADVANCED)) {
								if (!neighborConfig.isDisplay()) {
									allowed = false;
								}
							}
						}
					}
				}
			}
		} else if (!neighborConfig.isDisplay()) {
			allowed = false;
		}
		return allowed;
	}

	/**
	 * Checks if a neighbor update is allowed. Users with the ADMIN or MANAGER
	 * role have all update rights. For other users, the update restriction is
	 * determined by the neighbor configuration. If there is no sign in, the
	 * update restriction is determined by the neighbor configuration.
	 * 
	 * @param appSession
	 *            app session
	 * @param neighborConfig
	 *            neighbor configuration
	 * @return <code>true</code> if update is allowed
	 */
	public boolean isNeighborUpdateAllowed(AppSession appSession,
			NeighborConfig neighborConfig) {
		boolean allowed = true;
		ModelConfig modelConfig = neighborConfig.getConceptConfig()
				.getModelConfig();
		if (modelConfig.getDomainConfig().isSignin()) {
			if (!appSession.isUserSignedIn()) {
				allowed = false;
			} else {
				IEntity<?> signedInUser = appSession.getSignedInUser();
				if (signedInUser != null) {
					DomainModel model = (DomainModel) signedInUser.getModel();
					Object roleObject = model.getModelMeta().getProperty(
							signedInUser, rolePropertyCode);
					if (roleObject != null) {
						if (roleObject instanceof String) {
							String securityRole = (String) roleObject;
							if (!securityRole.equals(ADMIN)
									&& !securityRole.equals(MANAGER)) {
								if (!neighborConfig.isUpdate()) {
									allowed = false;
								}
							}
						}
					}
				}
			}
		} else if (!neighborConfig.isUpdate()) {
			allowed = false;
		}
		return allowed;
	}

}
