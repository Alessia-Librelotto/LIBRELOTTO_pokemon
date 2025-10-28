import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Database db = null;
        try{
            db = Database.getInstance();
        }catch(SQLException e){
            System.out.println("Errore nella connessione");
            System.exit(-1);
        }

        //singleton
        API api = new API();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci pokemon: ");
        Pokemon pokemon = api.getPokemon(scanner.nextLine());
        if(pokemon == null) System.exit(-1);
        db.insert(pokemon);

    }
}