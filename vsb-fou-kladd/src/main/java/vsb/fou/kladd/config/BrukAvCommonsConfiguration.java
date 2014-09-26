package vsb.fou.kladd.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

public class BrukAvCommonsConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrukAvCommonsConfiguration.class);

    public static void main(String[] args) throws ConfigurationException, IOException {
        new BrukAvCommonsConfiguration().loadPropsFile();
    }

    public void loadPropsFile() throws ConfigurationException, IOException {
        PropertiesConfiguration config = new PropertiesConfiguration(new ClassPathResource("vsb.properties").getFile());
        FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
        strategy.setRefreshDelay(5 * 60 * 1000L);
        config.setReloadingStrategy(strategy);
        String kaffe = config.getString("vsb.kaffe");
        LOGGER.info("kaffe = " + kaffe);
        double versjon = config.getDouble("versjon");
        LOGGER.info("versjon = " + versjon);
        List<Object> list = config.getList("vsb.liste");
        LOGGER.info("list = " + list);
        boolean aBoolean = config.getBoolean(this.getClass().getName());
        LOGGER.info("aBoolean = " + aBoolean);
    }
}
