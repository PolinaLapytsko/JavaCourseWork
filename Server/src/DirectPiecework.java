import java.io.DataInputStream;
import java.io.IOException;
import java.sql.Connection;

public class DirectPiecework {
    public double countSalary(DataInputStream in) throws IOException {
        String productS = in.readUTF();
        String pieceRateS = in.readUTF();

        Double product = Double.parseDouble(productS);
        Double pieceRate = Double.parseDouble(pieceRateS);

        return product*pieceRate;
    }

}
