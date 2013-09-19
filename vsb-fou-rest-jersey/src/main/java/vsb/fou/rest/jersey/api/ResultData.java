package vsb.fou.rest.jersey.api;

/**
 * @author Vegard S. Bye
 */
public class ResultData {

    private String name;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("ResultData[name: %s, status: %s]", name, status);
    }
}
