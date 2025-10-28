import java.sql.*;

public class Database {
    Connection connection;
    private static Database instance;

    private Database() throws SQLException {
        String url = "jdbc:sqlite:database/pokemon.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connected to the database");
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void insert(Pokemon pokemon) {
        try{
            if(!connection.isValid(5))
                return;
        }catch(SQLException e){
            System.out.println("Errore nella connessione al db");
            return;
        }

        String query = "INSERT INTO pokemon(id, nome, tipo1, tipo2) VALUES (?,?,?,?) ";
        PreparedStatement stmt = null;
        try{
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, pokemon.id);
            stmt.setString(2, pokemon.name);
            stmt.setString(3, pokemon.types.get(0).getType().getName());
            if(pokemon.types.size() != 1){
                stmt.setString(4, pokemon.types.get(1).getType().getName());
            }else stmt.setString(4, null);

            stmt.executeUpdate();
        }catch(SQLException e){
            System.err.println("Errore nella query");
        }
    }
}
