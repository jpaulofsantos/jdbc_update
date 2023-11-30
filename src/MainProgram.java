import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainProgram {
    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DB.getConnection();

            preparedStatement = connection.prepareStatement(
                    "UPDATE seller "
                    + "SET BaseSalary = BaseSalary + ? "
                    + "WHERE "
                    + "(DepartmentId = ?)");

            preparedStatement.setDouble(1, 200.00); //1 é referente ao primeiro ? e 200 é o valor que será aumentado no salário
            preparedStatement.setInt(2, 2); //2 é referente ao segundo ? e o valor é referente ao Id do Departamento

            int rowAffected = preparedStatement.executeUpdate();

            System.out.println("Affected rows: " + rowAffected);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            DB.closeStatement(preparedStatement);
            DB.closeConnection();
        }
    }
}
