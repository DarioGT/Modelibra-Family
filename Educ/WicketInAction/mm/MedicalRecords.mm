<?xml version="1.0" encoding="UTF-8"?>

	<!-- ========================================== -->
	<!-- Reusable Domain Config                     -->
	<!-- ========================================== -->

<domains>

	<domain oid="1253218217141">
		<code>Medical</code>
		<type>Reusable</type>
	    
		<models>

			<model oid="1253218267551">
				<code>Records</code>
				<author>Dzenan Ridjanovic</author>
				<persistenceType>xml</persistenceType>
				<persistenceRelativePath>data/xml/medical/records</persistenceRelativePath>
				<defaultLoadSave>true</defaultLoadSave>
	    
				<concepts>
			
					<concept oid="1253218298654">
						<code>Patient</code>					
						<entitiesCode>Patients</entitiesCode>
						<entry>true</entry>
			    
						<properties>
							<property oid="1253218328515">
								<code>firstName</code>
								<propertyClass>java.lang.String</propertyClass>
								<maxLength>32</maxLength>
								<required>true</required>

								<essential>true</essential>
							</property>
							<property oid="1253218332772">
								<code>lastName</code>
								<propertyClass>java.lang.String</propertyClass>
								<maxLength>32</maxLength>
								<required>true</required>

								<essential>true</essential>
							</property>
							<property oid="1253218351381">
								<code>email</code>
								<propertyClass>java.lang.String</propertyClass>
								<validateType>true</validateType>
								<validationType>org.modelibra.type.Email</validationType>
								<maxLength>80</maxLength>
								<required>true</required>
								<unique>true</unique>

								<essential>true</essential>
							</property>
						</properties>

						<neighbors>
						</neighbors>
			    
					</concept>		    
		
				</concepts>
		
			</model>
    
		</models>

	</domain>
		
</domains>
