package pro.butovanton.countries2.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;

import java.io.File;
import java.util.List;

import pro.butovanton.countries2.R;
import pro.butovanton.countries2.Repo.Model;

class cRecyclerAdapter extends RecyclerView.Adapter<cRecyclerAdapter.cViewHolder> {
    private final LayoutInflater mInflater;
    private List<Model> countries;
    private static Context context;

    public cRecyclerAdapter(Context context, List<Model> countries) {
        this.context = context;
        mInflater = LayoutInflater.from((Context) context);
        this.countries = countries;
    }

    @NonNull
    @Override
    public cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item, parent, false);

        return new cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(cViewHolder holder, int position) {
        holder.setName(countries.get(position).name);
        String patch = countries.get(position).flagPatch;
        File file = new File(patch);
            Uri nuri = Uri.fromFile(file);
            SvgLoader.pluck()
                    .with((Activity)context)
                    .load(nuri, holder.imageView);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }


    public class cViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameT;
        private final ImageView imageView;

        public cViewHolder(View view) {
            super(view);
            nameT = (TextView) view.findViewById(R.id.name);
            imageView = (ImageView) view.findViewById(R.id.imageViewrecicler);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Activity3.class);
                    intent.putExtra("name", countries.get(getAdapterPosition()).name);
                    intent.putExtra("capital", countries.get(getAdapterPosition()).capital);
                    intent.putExtra("currencie", countries.get(getAdapterPosition()).currencies.get(0).name);
                    intent.putExtra("flagpatch", countries.get(getAdapterPosition()).flagPatch);
                    context.startActivity(intent);
                }
            });
        }

        public void setName(String name) {
            nameT.setText(name);
        }

        public ImageView getImageView(String patch) {
            return imageView;
        }
    }

}
