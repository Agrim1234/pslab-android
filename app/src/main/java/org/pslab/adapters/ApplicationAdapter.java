package org.pslab.adapters;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.pslab.items.ApplicationItem;
import org.pslab.R;

import java.util.List;

/**
 * Created by Padmal on 5/7/17.
 */

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.Holder> {

    private Context mContext;
    private List<ApplicationItem> applicationList;
    private final OnItemClickListener listener;


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /**
     * View holder for application list item
     */
    public class Holder extends RecyclerView.ViewHolder {

        TextView header, description;
        ImageView applicationIcon;

        public Holder(View itemView) {
            super(itemView);
            this.header = itemView.findViewById(R.id.heading_card);
            this.description = itemView.findViewById(R.id.description_card);
            this.applicationIcon = itemView.findViewById(R.id.application_icon);
        }

        public void setup(final ApplicationItem applicationItem, final OnItemClickListener listener) {
            header.setText(applicationItem.getApplicationName());
            description.setText(applicationItem.getApplicationDescription());
            applicationIcon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            applicationIcon.setImageResource(applicationItem.getApplicationIcon());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(applicationItem);
                }
            });
        }
    }

    public ApplicationAdapter(Context mContext, List<ApplicationItem> applicationList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.applicationList = applicationList;
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.application_list_item, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setup(applicationList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return applicationList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(ApplicationItem item);
    }

}
