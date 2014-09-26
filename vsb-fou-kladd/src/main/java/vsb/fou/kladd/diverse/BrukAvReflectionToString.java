package vsb.fou.kladd.diverse;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrukAvReflectionToString {

    public static final String KEY = "APP-KEY";
    private static final Logger LOGGER = LoggerFactory.getLogger(BrukAvReflectionToString.class);
    private Integer id;
    private String name;
    private String description;
    private transient String secretKey;
    private InnerKlasse innerKlasse;

    public BrukAvReflectionToString(Integer id, String name, String description,
                                    String secretKey) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.secretKey = secretKey;
        this.innerKlasse = new InnerKlasse("VEGARD");
    }

    public static void main(String[] args) {
        BrukAvReflectionToString demo =
                new BrukAvReflectionToString(1, "MANU", "Manchester United", "Alex");
        LOGGER.info("Demo = " + demo);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.MULTI_LINE_STYLE, true, true);
    }

    private static class InnerKlasse {
        private String navn;

        private InnerKlasse(String navn) {
            this.navn = navn;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this,
                    ToStringStyle.MULTI_LINE_STYLE, true, true);
        }
    }
}
