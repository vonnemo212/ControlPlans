import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcArgument.pfcArgument;
import com.ptc.pfc.pfcExport.*;
import com.ptc.pfc.pfcModel.Model;
import com.ptc.pfc.pfcSession.Session;

public class SessionModel {

    public static Model model;
    public static String sifra;

    public static void GetSessionModel() {
        try {
            model = pfcAsync.session.GetCurrentModel();

                if(model != null) {
                    sifra = model.GetInstanceName();
                } else {
                    System.out.println("No model in session");
                }
        } catch (jxthrowable e) {
            throw new RuntimeException(e);
        }
    }

    public static void exportPDF() {
        try {
            PDFExportInstructions pdf_instr = pfcExport.PDFExportInstructions_Create();
            String fileName =propertiesFile.prop.getProperty("Pot_za_izvoz_PDF") + sifra + "_kontrolni.pdf";
            int stroke = PDFFontStrokeMode._PDF_STROKE_ALL_FONTS;

            //PDF Options

            PDFOptions pdfOptions = PDFOptions.create();
            PDFOption pdfOption1 = pfcExport.PDFOption_Create();

            pdfOption1.SetOptionType(PDFOptionType.PDFOPT_FONT_STROKE);
            pdfOption1.SetOptionValue(pfcArgument.CreateIntArgValue(stroke));
            pdfOptions.append(pdfOption1);

            //PDF Export Instructions @@@@@@@ Dodaj pot za export @@@@@@@
            pdf_instr.SetOptions(pdfOptions);
            model.Export(fileName,pdf_instr);
        } catch (jxthrowable e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkModel(Session session) {
        boolean test;
        try {
            test = session.GetCurrentModel() != null;
        } catch (jxthrowable e) {
            throw new RuntimeException(e);
        }
        return test;
    }
}
