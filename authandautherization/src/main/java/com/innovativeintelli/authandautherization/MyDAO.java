public class MyDAO extends T3HibernateSessionManager {

private static final String INSERT_DUMMY_TABLE_VALUES =
            "INSERT INTO DUMMY_TABLE " +
            "(COLUMN_1, COLUMN_2, COLUMN_3, COLUMN_4, COLUMN_5, " +
            "COLUMN_6, COLUMN_7, COLUMN_8, COLUMN_9, " +
            "COLUMN_10, COLUMN_11, COLUMN_12, COLUMN_13, COLUMN_14, COLUMN_15, " +
            "COLUMN_16, COLUMN_17, COLUMN_18, COLUMN_19, " +
            "COLUMN_20) VALUES " +
            "(TO_NUMBER(:param1), TO_DATE(:param2,'mm/dd/yyyy'), TO_NUMBER(:param3), TO_NUMBER(:param4), TO_NUMBER(:param5), " +
            "TO_NUMBER(:param6), TO_NUMBER(:param7), TO_NUMBER(:param8), TO_NUMBER(:param9), " +
            "TO_NUMBER(:param10), TO_NUMBER(:param11), TO_NUMBER(:param12), TO_NUMBER(:param13), TO_NUMBER(:param14), " +
            ":param15, :param16, TO_NUMBER(:param17), TO_NUMBER(:param18), TO_NUMBER(:param19), " +
            "TO_NUMBER(:param20))";
}
