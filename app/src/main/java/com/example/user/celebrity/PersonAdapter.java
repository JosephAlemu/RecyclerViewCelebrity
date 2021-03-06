package com.example.user.celebrity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 1/20/2018.
 */

public class PersonAdapter extends  RecyclerView.Adapter<PersonAdapter.MyViewHolder>implements ItemTouchHelperAdapter {

    private List<Person> PersonList;
    private Context context;
    DBAdapter myDb;
     OnItemClickListener mItemClickListener;
    private final OnStartDragListener mDragStartListener;


    public PersonAdapter(Context context,List<Person> PersonList,OnStartDragListener dragListner) {
        this.PersonList = PersonList;
        this.context = context;
        mDragStartListener = dragListner;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Person person = PersonList.get(position);
        holder.txtName.setText(person.getName());
        holder.txtTitle.setText(person.getTitle());
        holder.ivPerson.setImageResource(person.getImage());
        Log.d("onBindViewHolder:",">"+position);



        holder.ivPerson.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return PersonList.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public void onItemDismiss(int position) {
        PersonList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //Log.v("", "Log position" + fromPosition + " " + toPosition);
        if (fromPosition < PersonList.size() && toPosition < PersonList.size()) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(PersonList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(PersonList, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
        }
        return true;
    }

    public void updateList(List<Person> list) {
        PersonList = list;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,ItemTouchHelperViewHolder {
        public TextView   txtName, txtTitle;
        public ImageView ivPerson;
        public LikeButton thumb_button;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            ivPerson = (ImageView) view.findViewById(R.id.ivPerson);
            thumb_button = (LikeButton) view.findViewById(R.id.thumb_button);

            view.setOnClickListener(this);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "clicked"+PersonList.get(getAdapterPosition()), Toast.LENGTH_LONG).show();
                }
            });

            thumb_button.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                     int id;  String name; String title; int image;  int like;

                    myDb = new DBAdapter(context);
                    myDb.open();

                       id =  PersonList.get(getAdapterPosition()).getId();
                       name =  PersonList.get(getAdapterPosition()).getName();
                       title = PersonList.get(getAdapterPosition()).getTitle();
                       image = PersonList.get(getAdapterPosition()).getLike();
                        like = PersonList.get(getAdapterPosition()).getLike();

                    Toast.makeText(context, id+":"+name+":"+title+":"+image+":"+like , Toast.LENGTH_LONG).show();
                     boolean n =   myDb.updateRow( id , name, title, image,1);
                    myDb.close();

                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    int id;  String name; String title; int image;  int like;
                    myDb = new DBAdapter(context);
                    myDb.open();
                    id =  PersonList.get(getAdapterPosition()).getId();
                    name =  PersonList.get(getAdapterPosition()).getName();
                    title = PersonList.get(getAdapterPosition()).getTitle();
                    image = PersonList.get(getAdapterPosition()).getLike();
                    like = PersonList.get(getAdapterPosition()).getLike();
                    Log.d("UnLike cliked", "unLiked:"+like);
                    Toast.makeText(context, id+":"+name+":"+title+":"+image+":"+like , Toast.LENGTH_LONG).show();
                    boolean n =   myDb.updateRow( id , name, title, image,0);
                    Log.d("NNNNNNN", "unLiked:"+n);
                    myDb.close();
                }
            });
        }



        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.blue(10));
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(Color.blue(10));
        }



    }
}