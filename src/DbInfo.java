public interface DbInfo {
    String MysqlDriver ="com.mysql.jdbc.Driver";
    String database="jdbc:mysql://localhost/hospital_management_system";
    String databaseUser ="root";
    String databsePassword="";

    //login
    String queryGetLoginData = "SELECT * FROM `login` WHERE userName=? AND password=?";

    //patient
    String querryInsertPatient = "INSERT into patient(name,gender,age,date,contact,address) VALUES(?,?,?,?,?,?)";
    String querryGetPatient = "SELECT * FROM patient WHERE ID=?";
    String getLatustPatient = "SELECT ID FROM patient ORDER BY ID DESC LIMIT 1";
    String queryUpdatePatient = "UPDATE patient SET name=?,gender=?,age=?,date=?,contact=?,address=? WHERE ID=?";
    String queryDeletePatient = "DELETE FROM patient WHERE id=?";

    //staff
    String querryInsertStaff = "INSERT into staff(name,gender,position,salary,contact,address) VALUES(?,?,?,?,?,?)";
    String getLatustStaff = "SELECT ID FROM staff ORDER BY ID DESC LIMIT 1";
    String querryGetStaff = "SELECT * FROM staff WHERE ID=?";
    String queryUpdateStaff = "UPDATE staff SET name=?,gender=?,position=?,salary=?,contact=?,address=? WHERE ID=?";
}
