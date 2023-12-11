import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcDimension.Dimension;
import com.ptc.pfc.pfcDimension2D.Dimension2D;
import com.ptc.pfc.pfcDrawing.Drawing;
import com.ptc.pfc.pfcModelItem.*;
import com.ptc.pfc.pfcSelect.SelectionOptions;
import com.ptc.pfc.pfcSelect.*;
import com.ptc.pfc.pfcSelect.pfcSelect;
import com.ptc.wfc.wfcDimension.WDimension;
import com.ptc.wfc.wfcDimension.WDimension2D;

public class drawingOperations {

    //Selection
    private static ModelItemOwner owner = SessionModel.model;
    private static Selections selections;
    private static int selectionSize;

    //Excel
    private static String fileNameIn;
    private static String fileNameOut;



    public static void selectBox() {
        if(SessionModel.model != null) {

            SelectionOptions sel_options;
            try {
                sel_options = pfcSelect.SelectionOptions_Create("dimension");
                sel_options.SetMaxNumSels(owner.ListItems(ModelItemType.ITEM_DIMENSION).getarraysize());
                selections = pfcAsync.session.Select(sel_options, null);
                selectionSize = selections.getarraysize();
            } catch (jxthrowable e) {
                throw new RuntimeException(e);
            }
        }
    }



    public static void selectDim() {




    }

    public static void exportDim() {
        if(selectionSize != 0) {

            ModelItem dimension2D;
            Selection selection;
            Dimension2D dimension;
            Dimension dimension2;
            WDimension wdimension;
            WDimension2D wDimension2D;


            try {
                selection = selections.get(0);
                dimension2D = selection.GetSelItem();
                //dimension = (Dimension2D) dimension2D;
                //dimension2 = (Dimension) dimension2D;
                wdimension = (WDimension) dimension2D;
                wDimension2D = (WDimension2D) dimension2D;
                //System.out.println(dimension.GetDimValue());
                //System.out.println(dimension.GetDimValue());
                System.out.println(wdimension.GetDisplayedValue());
                System.out.println(wdimension.GetDimValue());
                System.out.println(wDimension2D.GetDimValue());



            } catch (jxthrowable e) {
                throw new RuntimeException(e);
            }


        }
    }
}
