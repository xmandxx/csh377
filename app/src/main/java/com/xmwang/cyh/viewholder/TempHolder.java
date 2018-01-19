package com.xmwang.cyh.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.xmwang.cyh.R;
import com.xmwang.cyh.daijia.EditTempActivity;
import com.xmwang.cyh.daijia.TempListActivity;
import com.xmwang.cyh.model.TempModel;

/**
 * @Description:
 * @autour: CTS
 * @date: 2017/12/26
 */

public class TempHolder extends BaseViewHolder<TempModel> {
    private TextView tv1, tv2, tv3, tv4;
    private LinearLayout ln_bt;

    public TempHolder(ViewGroup parent) {
        super(parent, R.layout.item_edittemp);
        tv1 = $(R.id.item_et1);
        tv2 = $(R.id.item_et2);
        tv3 = $(R.id.item_et3);
        tv4 = $(R.id.item_et4);
        ln_bt = $(R.id.item_bt);
    }

    @Override
    public void setData(final TempModel data) {
        tv1.setText(data.getS1());
        tv2.setText(data.getS2());
        tv3.setText(data.getS3());
        tv4.setText(data.getS4());

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditTempActivity) getContext()).showDialogTime(getDataPosition(), 0);
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditTempActivity) getContext()).showDialogTime(getDataPosition(), 1);
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditTempActivity) getContext()).showDialogMoney(getDataPosition(), 0);
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditTempActivity) getContext()).popup_position = getDataPosition();
                ((EditTempActivity) getContext()).showPopup();
            }
        });
//        tv_title.setText(data.getQuality_type_name());
//        tv_unit.setText(data.getQuality_type_unit());
//
//        if (data.getQuality_value() != null && !data.getQuality_value().equals("")) {
//            et_num.setText(data.getQuality_value());
//        } else {
//            et_num.setText("");
//            str = "请输入" + data.getQuality_type_name();
//            et_num.setHint(str);
//        }
//
//        et_num.setSelection(str.length());
//        QualityActivity.map_et.put(new String[]{getAdapterPosition() + "", data.getQuality_type_id()}, et_num);

//        et1.setText(((TempListActivity) getContext()).ls_et.get(getDataPosition()).get(0).getText());
//        et2.setText(((TempListActivity) getContext()).ls_et.get(getDataPosition()).get(1).getText());
//        et3.setText(((TempListActivity) getContext()).ls_et.get(getDataPosition()).get(2).getText());
//        et4.setText(((TempListActivity) getContext()).ls_et.get(getDataPosition()).get(3).getText());
        ln_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditTempActivity) getContext()).deleteItem(getDataPosition());
            }
        });

//        Log.e("测试",getDataPosition()+"");
//        if (TempListActivity.ls_et.size() > getDataPosition()) {
////            if (TempListActivity.ls_et.get(getDataPosition()).size() > 0) {
//                TempListActivity.ls_et.remove(getDataPosition());
////            }
//        }
//        TempListActivity.ls_et.add(getDataPosition(), new ArrayList<EditText>() {
//            {
//                add(et1);
//                add(et2);
//                add(et3);
//                add(et4);
//            }
//        });

    }

}
