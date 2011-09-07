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
package modelibra.swing.app;

import java.util.Properties;

import org.modelibra.config.DomainConfig;
import org.modelibra.swing.app.App;
import org.modelibra.swing.app.MainFrame;
import org.modelibra.util.NatLang;
import org.modelibra.util.PropertiesLoader;

import modelibra.Modelibra;
import modelibra.ModelibraConfig;
import modelibra.PersistentModelibra;

public class Start {

    public static final String APP_CONFIG_LOCAL_PATH = "Start.properties";
    
    public static final int MAIN_FRAME_X = 0;
    public static final int MAIN_FRAME_Y = 0;

    public Start() {
        ModelibraConfig modelibraConfig = new ModelibraConfig();
        DomainConfig domainConfig = modelibraConfig.getDomainConfig();
        Modelibra modelibra = new Modelibra(domainConfig);
        new PersistentModelibra(modelibra);

        Properties configurator = PropertiesLoader.load(Start.class,
                APP_CONFIG_LOCAL_PATH);
        String language = configurator.getProperty("lang");
        String textResources = configurator.getProperty("textResources");
        NatLang natLang = new NatLang();
        natLang.setNaturalLanguage(language, textResources);
        
        App app = new App(modelibra, natLang);
        MainFrame mainFrame = new MainFrame(app);
        mainFrame.setLocation(MAIN_FRAME_X, MAIN_FRAME_Y);
        mainFrame.setVisible(true);
    }

    /**
     * Application entry (start) point.
     * 
     * @param args
     *            args not used
     */
    public static void main(String[] args) {
        new Start();
    }

}
