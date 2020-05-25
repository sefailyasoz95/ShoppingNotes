package com.example.shoppingnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CardViewHolder> {
    private Context context;
    private List<Lists> listNames;
    private DatabaseHelper helper;

    public RecyclerViewAdapter(Context context, List<Lists> listNames, DatabaseHelper helper) {
        this.context = context;
        this.listNames = listNames;
        this.helper = helper;
    }

    public RecyclerViewAdapter() {
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView txtListName;
        public CardView cardViewListName;
        public ImageView imageDelete;

        public CardViewHolder(View view){
            super(view);
            txtListName = view.findViewById(R.id.txtListName);
            cardViewListName = view.findViewById(R.id.cardViewListName);
            imageDelete = view.findViewById(R.id.imageDelete);
        }
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_card_tasarim, parent, false);

        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {
        final Lists listName = listNames.get(position);

        holder.txtListName.setText(listName.getListName());
        holder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper = new DatabaseHelper(context);
                new Listsdao().listNameDelete(helper, listName.getList_id());
                listNames = new Listsdao().allLists(helper);
                notifyDataSetChanged();
                Toast.makeText(context, listName.getListName()+" silindi", Toast.LENGTH_SHORT).show();
            }
        });
        holder.cardViewListName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listName.getListName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNames.size();
    }

}
