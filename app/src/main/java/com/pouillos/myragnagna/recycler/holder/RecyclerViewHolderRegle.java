package com.pouillos.myragnagna.recycler.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.entities.Regle;
import com.pouillos.myragnagna.recycler.adapter.RecyclerAdapterRegle;
import com.pouillos.myragnagna.utils.DateUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHolderRegle extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.regle)
    TextView regle;

    private WeakReference<RecyclerAdapterRegle.Listener> callbackWeakRef;

    public RecyclerViewHolderRegle(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithRegle(Regle regle, RecyclerAdapterRegle.Listener callback){
        this.regle.setText(regle.getDateString() + " - " + regle.getIntervalle() + " jours");
        this.regle.setOnClickListener(this);
        this.callbackWeakRef = new WeakReference<RecyclerAdapterRegle.Listener>(callback);
    }

    @Override
    public void onClick(View view) {
        RecyclerAdapterRegle.Listener callback = callbackWeakRef.get();
        if (callback != null) callback.onClickRegleButton(getAdapterPosition());
    }
}
