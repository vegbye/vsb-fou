package vsb.fou.kladd.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

public class BrukAvCommonsConfiguration {

    public static void main(String[] args) throws ConfigurationException, IOException {
        new BrukAvCommonsConfiguration().loadPropsFile();
    }

    public void loadPropsFile() throws ConfigurationException, IOException {
        PropertiesConfiguration config = new PropertiesConfiguration(new ClassPathResource("vsb.properties").getFile());
        FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
        strategy.setRefreshDelay(5 * 60 * 1000L);
        config.setReloadingStrategy(strategy);
        String kaffe = config.getString("vsb.kaffe");
        System.out.println("kaffe = " + kaffe);
        double versjon = config.getDouble("versjon");
        System.out.println("versjon = " + versjon);
        List<Object> list = config.getList("vsb.liste");
        System.out.println("list = " + list);
    }
}
