public class Vehicle {
    @NotNull
    @Length(min = 5)
    private String modelName;

    @Limit(limit = 20)
    private int numWheels;

    @Limit(limit = 8)
    private int numDoors;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getNumWheels() {
        return numWheels;
    }

    public void setNumWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    @Override
    public String toString() {
        return "Vehicle [modelName=" + modelName + ", numWheels=" + numWheels
                + ", numDoors=" + numDoors + "]";
    }


}
