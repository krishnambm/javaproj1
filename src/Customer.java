import java.util.Arrays;
import java.util.List;

enum Status {
    PRIVILIGED (0),
    NORMAL (1),
    RESTRICTED (2);

    private int code;

    private Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}

public class Customer {
    private int id;
    private String name;
    private Status status;

    public Customer(int id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    public static class CustomerBuilder {
        private int id;
        private String name;
        private Status status;

        public CustomerBuilder id(int id) {
            this.id = id;
            return this;
        }
        public CustomerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public Customer build() {
            return new Customer(id, name, status);
        }
    }

}

