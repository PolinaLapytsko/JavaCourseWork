import java.io.DataInputStream;
import java.io.IOException;

public class PieceworkPrize {
    public double countSalary(DataInputStream in) throws IOException {
        String productS = in.readUTF();
        String productPlaneS = in.readUTF();
        String pieceRateS = in.readUTF();
        String coefS = in.readUTF();
        String planPrizeS = in.readUTF();
        String prizeS = in.readUTF();
        String planS = in.readUTF();

        Double product = Double.parseDouble(productS);
        Double productPlane = Double.parseDouble(productPlaneS);
        Double pieceRate = Double.parseDouble(pieceRateS);
        Double coef = Double.parseDouble(coefS);
        Double planPrize = Double.parseDouble(planPrizeS);
        Double prize = Double.parseDouble(prizeS);
        Double plan = Double.parseDouble(planS);

        return product*pieceRate*(1+(planPrize+prize*plan)/100);
    }
}
