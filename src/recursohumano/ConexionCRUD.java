package recursohumano;

import java.sql.*; //Librerias para las conexiones a la BD

public class ConexionCRUD {
    /*Ruta de la base de datos el servidor 127.0.0.1, el puerto 3306 
    y el nombre de la base de datos bd de datos bd_recurso_humano*/
    private final String servidor = "jdbc:mysql://127.0.0.1:3306/bd_recurso_humano";

    //Nombre del usuario (root por defecto) de la base de datos
    private final String usuario = "root";
    //Clave del usuario de la base de datos
    private final String clave = "";
    //Libreria de mysql
    private final String driverConector = "com.mysql.jdbc.Driver";
    //Objeto de la clase Connection del paquete java.sql
    private static Connection conexion;
    
    public ConexionCRUD() {
        try{
            Class.forName(driverConector); //Levantar el driver
            //Establecer conexion
         conexion=DriverManager.getConnection(servidor, usuario, clave);
        }catch(ClassNotFoundException  |  SQLException e) {
            System.out.println("Conexion fallida! Error! : " + e.getMessage());
            
        }
    }
    public Connection getConnection(){
        return conexion; //Devuelve el objeto conexion
    }
    public void guardarRegistros(String Tabla, String CamposTabla, String valoresCampos) {
        //Cargar la conexion
        ConexionCRUD conectar = new ConexionCRUD();
        Connection cone = conectar.getConnection();
        try{
            //Definir la sentencia sql
            String sqlQueryStmt = "INSERT INTO " + Tabla + " (" + CamposTabla + ") VALUES (" + valoresCampos + ");";
                    //Establecemos la comunicacion entre nuestra aplicacion java y la base de datos
                    Statement stmt; //Envia sentencias sql a la base de datos
                    stmt = cone.createStatement ();
                    stmt.executeUpdate (sqlQueryStmt); //Ejecutar la sentencia sql
                     //Cerrar el Statement y la conexion; se cierran en orden inverso de como se han abierto
                     stmt.close();
                     cone.close();
                     System.out.println("Registro guardado correctamente!");
        }catch(Exception e)  {
            System.out.println(e.getMessage());
        }
    }
    
        
        public void actualizarEliminarRegistro(String Tabla, String valoresCamposNuevos, String Condicion) {
            //Cargar la conexion
            ConexionCRUD conectar = new ConexionCRUD();
            Connection cone = conectar.getConnection();
            try{
                Statement stmt;
                String sqlQueryStmt ="";
                //Verificar que valoresCamposNuevosvenga vacia y asi seleccionar si es borrar o actulizar registro
                if (valoresCamposNuevos.isEmpty()) {
                    String sqlQuerystmt = "DELETE FROM " + Tabla + " WHERE " + Condicion + ";";
                }else{
                    sqlQueryStmt = "UPDATE" + Tabla + "SET" + valoresCamposNuevos + "WHERE" + Condicion + ";";
                }
                stmt = cone.createStatement(); 
                stmt.executeUpdate(sqlQueryStmt);
                stmt.close();
                cone.close();
            }catch (SQLException ex) {
                System.out.println("Ha ocurrido el siguiente error: " + ex.getMessage());
            }
        }
        public void desplegarRegistros(String TablaBuscar, String CamposBuscar, String CondicionBuscar) throws SQLException{
            //Cargar la conexion
            ConexionCRUD conectar = new ConexionCRUD();
            Connection cone = conectar.getConnection();
            try{
                Statement stmt;
                String sqlQueryStmt ="";
                if (CondicionBuscar.equals("")) {
                    sqlQueryStmt = "SELECT" + CamposBuscar + "FROM" + TablaBuscar + ";";
                }else{
                    sqlQueryStmt = "SELECT" + CamposBuscar + "FROM" + TablaBuscar + "WHERE" + CondicionBuscar + ";";
                }
                stmt = cone.createStatement();
                stmt.executeQuery(sqlQueryStmt);
                //Le indicamos que ejecute la consulta de la tabla y le pasamos por argumentos nuestra sentencia
               try(ResultSet miResultSet = stmt.executeQuery (sqlQueryStmt)) {
                   if(miResultSet.next()) { //Ubica el cursor en la primera filade la tablade resultado
                       ResultSetMetaData metaData = miResultSet.getMetaData();
                       int numColumnas = metaData.getColumnCount(); //Obtiene el numero de columnas de la consulta
                       System.out.print("<< REGISTROS ALMACENADOS >>");
                       System.out.println();
                       for (int i = 1; i <= numColumnas; i++) {
                           //Muestra los titulos de las columnas y %-20s\t indica la separacion entre columnas
                           System.out.printf("%-20s\t", miResultSet.getObject(i));
                       }
                       System.out.println();
                    while (miResultSet.next());
                   System.out.println();
               } else {
                       System.out.println("No se han encontrado registros");
                       }
               miResultSet.close(); //cerrar el ResulSet
                }finally{
                       //Cerrar el Statement y la conexion; se cierran en orden inverso de como se han abirto
                       stmt.close();
                       cone.close();
                       }
            } catch (SQLException ex) {
                System.out.println("Ha ocurrido el siguiente error: " + ex.getMessage());
            }
        }
}



