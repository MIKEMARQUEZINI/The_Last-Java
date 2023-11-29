package prosper.thelast.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "waters")
public class Water extends Product{
    private boolean sparkling;

    public Water(boolean sparkling) {
        this.sparkling = sparkling;
    }

    public boolean isSparkling() {
        return sparkling;
    }

    public void setSparkling(boolean sparkling) {
        this.sparkling = sparkling;
    }
}
