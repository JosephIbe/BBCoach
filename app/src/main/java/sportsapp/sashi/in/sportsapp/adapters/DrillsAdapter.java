package sportsapp.sashi.in.sportsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sportsapp.sashi.in.sportsapp.R;
import sportsapp.sashi.in.sportsapp.models.app.Drills;
import sportsapp.sashi.in.sportsapp.viewholders.DrillItemViewHolder;

public class DrillsAdapter extends RecyclerView.Adapter<DrillItemViewHolder> {

    private final Context context;
    private List<Drills> itemsList;

    public DrillsAdapter(Context context, List<Drills> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public DrillItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drill_items_layout, parent, false);
        return new DrillItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrillItemViewHolder viewholder, int position) {
        Drills drills = itemsList.get(position);
        viewholder.drillTitleTV.setText(drills.getDrill_Title());
    }

    @Override
    public int getItemCount() {
        if (itemsList == null) {
            return 0;
        }
        return itemsList.size();
    }
}