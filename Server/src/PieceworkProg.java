import java.io.DataInputStream;
import java.io.IOException;

public class PieceworkProg {
    public double countSalary(DataInputStream in) throws IOException {
        String productFactS = in.readUTF();
        String productPlanS = in.readUTF();
        String pieceRateS = in.readUTF();
        String coefS = in.readUTF();

        Double productFact = Double.parseDouble(productFactS);
        Double productPlan = Double.parseDouble(productPlanS);
        Double pieceRate = Double.parseDouble(pieceRateS);
        Double coef = Double.parseDouble(coefS);

        return pieceRate*productPlan+coef*pieceRate*(productFact-productPlan);
    }
}
