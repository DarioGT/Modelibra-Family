package org.modelibra.modeler.gen.modelibra.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.modelibra.modeler.app.pref.Para;
import org.modelibra.modeler.model.AppModel;

/**
 * Generates the Modelibra XML configuration file from all diagrams.
 * 
 * @author Dzenan Ridjanovic
 * @version 2007-12-27
 */
public class ModelibraDomainConfigGenerator extends ModelibraConfigGenerator {

	public ModelibraDomainConfigGenerator(AppModel appModel) {
		super(appModel);
	}

	/**
	 * Generates the configuration.
	 * 
	 * @param configFile
	 *            config file
	 * @param velocityTemplate
	 *            velocity template
	 */
	protected void generateConfig(File configFile, String velocityTemplate) {
		if (configFile != null) {
			try {
				Properties properties = new Properties();
				properties.setProperty("resource.loader", "class");
				properties
						.setProperty("class.resource.loader.class",
								"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
				Velocity.init(properties);

				String domainNameFirstLetterToUpper = appModel.getName().trim();
				if (domainNameFirstLetterToUpper != "?"
						&& domainNameFirstLetterToUpper != "") {
					domainNameFirstLetterToUpper = appModel
							.firstLetterToUpper(domainNameFirstLetterToUpper);
				} else {
					domainNameFirstLetterToUpper = "change this to the domain name";
				}
				String domainNameAllLettersToLower = appModel
						.allLettersToLower(domainNameFirstLetterToUpper);

				String packagePrefixAllLettersToLower = appModel.getPrefix()
						.trim();
				if (packagePrefixAllLettersToLower != "?"
						&& packagePrefixAllLettersToLower != "") {
					packagePrefixAllLettersToLower = appModel
							.allLettersToLower(packagePrefixAllLettersToLower);
				}
				String domainOid = appModel.getOid().getOidUniqueNumberString();

				List modelList = appModel.getDiagramList();

				VelocityContext context = new VelocityContext();
				context.put("packageprefix", packagePrefixAllLettersToLower);
				context.put("DomainName", domainNameFirstLetterToUpper);
				context.put("domainname", domainNameAllLettersToLower);
				context.put("domainOid", domainOid);

				context.put("modelList", modelList);

				Template template = Velocity.getTemplate(velocityTemplate);
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						configFile));
				template.merge(context, writer);
				writer.flush();
				writer.close();
				informUser(modelList.size() + " "
						+ Para.getPara().getText("modelsCreated"));
			} catch (Exception e) {
				System.out
						.println("Error in ModelibraDomainConfigGenerator.generateConfig: "
								+ e.getMessage());
			}
		} else {
			informUser(Para.getPara().getText("configNotCreated"));
		}
	}

}