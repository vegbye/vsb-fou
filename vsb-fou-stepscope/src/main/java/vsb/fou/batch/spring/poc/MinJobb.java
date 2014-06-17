package vsb.fou.batch.spring.poc;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MinJobb {

    @Resource
    private ItemReader<String> stepScopedReader;

    public void hei() throws Exception {
        stepScopedReader.read();
    }
}
