import java.util.Calendar;

public class ExampleUsage {
    public static void main(String[] args) {
        // Create an instance of DummyTableValues
        DummyTableValues dummyTableValues = new DummyTableValues();

        // Set values for the parameters (example values used here)
        dummyTableValues.setParam1(1); // Assuming this is a Number
        dummyTableValues.setParam2(Calendar.getInstance()); // Assuming this is a Calendar instance
        dummyTableValues.setParam3(100.0); // Assuming this is a Number
        dummyTableValues.setParam4(200.0); // Assuming this is a Number
        dummyTableValues.setParam5(50.0); // Assuming this is a Number
        dummyTableValues.setParam6(300.0); // Assuming this is a Number
        dummyTableValues.setParam7(25.0); // Assuming this is a Number
        dummyTableValues.setParam8(400.0); // Assuming this is a Number
        dummyTableValues.setParam9(150.0); // Assuming this is a Number
        dummyTableValues.setParam10(1000); // Assuming this is a Number
        dummyTableValues.setParam11(2000); // Assuming this is a Number
        dummyTableValues.setParam12(500); // Assuming this is a Number
        dummyTableValues.setParam13(10000); // Assuming this is a Number
        dummyTableValues.setParam14(15000); // Assuming this is a Number

        // Create Calendar instances for param15 and param16
        Calendar param15 = Calendar.getInstance();
        Calendar param16 = Calendar.getInstance();

        // Set these Calendar instances to dummyTableValues
        dummyTableValues.setParam15(param15);
        dummyTableValues.setParam16(param16);

        // Assuming you have an instance of the class containing insertDummyTableValues method
        ExampleUsage exampleUsage = new ExampleUsage();
        exampleUsage.insertDummyTableValues(dummyTableValues);
    }

    // Method to invoke the insertDummyTableValues
    public void insertDummyTableValues(DummyTableValues dummyTableValues) {

      MyDAO.insertDummyTableValues(dummyTableValues);
        
    }
}
