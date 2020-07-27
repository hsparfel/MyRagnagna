package com.pouillos.myragnagna.recycler.holder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pouillos.myragnagna.R;
import com.pouillos.myragnagna.entities.ReglePrevisionnelle;
import com.pouillos.myragnagna.recycler.adapter.RecyclerAdapterReglePrevisionnelle;
import com.pouillos.myragnagna.utils.DateUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHolderReglePrevisionnelle extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.regle)
    TextView reglePrevisionnelle;

    private WeakReference<RecyclerAdapterReglePrevisionnelle.Listener> callbackWeakRef;

    public RecyclerViewHolderReglePrevisionnelle(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithReglePrevisionnelle(ReglePrevisionnelle reglePrevisionnelle, RecyclerAdapterReglePrevisionnelle.Listener callback){
        this.reglePrevisionnelle.setText(reglePrevisionnelle.getDateString());
        this.reglePrevisionnelle.setOnClickListener(this);
        this.callbackWeakRef = new WeakReference<RecyclerAdapterReglePrevisionnelle.Listener>(callback);
    }

    @Override
    public void onClick(View view) {
        RecyclerAdapterReglePrevisionnelle.Listener callback = callbackWeakRef.get();
        if (callback != null) callback.onClickReglePrevisionnelleButton(getAdapterPosition());
    }
}
