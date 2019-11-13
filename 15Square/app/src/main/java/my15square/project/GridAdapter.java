package my15square.project;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.view.ViewGroup;
import android.view.View;
import java.util.ArrayList;

/**
 * @author: Gianni Magliana
 * @description: adapter that is required for this grid to work
 * @date: 11/10/19
 * @bugs: none are known
 */

/**
 * https://developer.android.com/reference/android/widget/BaseAdapter
 */
public class GridAdapter extends BaseAdapter {
    private ArrayList<Button> theButtons=null;
    private int theColWidth;
    private int theColHeight;

    //constructor for adapter
    public GridAdapter(ArrayList<Button> buttons, int colWidth, int colHeight){
        theButtons=buttons;
        theColWidth=colWidth;
        theColHeight=colHeight;
    }

    //overridden parent methods
    @Override
    public int getCount(){
        return theButtons.size();
    }

    @Override
    public Object getItem(int position){
        return (Object) theButtons.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View viewToChange, ViewGroup original){
        Button tile;

        if(viewToChange==null){
            tile=theButtons.get(position);
        } else{
            tile= (Button) viewToChange;
        }
        android.widget.AbsListView.LayoutParams parameters= new android.widget.AbsListView.LayoutParams(theColWidth,theColHeight);
        tile.setLayoutParams(parameters);
        return tile;
    }
}
