package com.example.lera.fgadvice.db;

import com.example.lera.fgadvice.model.Advice;
import com.yahoo.squidb.data.SquidCursor;
import com.yahoo.squidb.sql.Delete;
import com.yahoo.squidb.sql.Query;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    public static int saveAdviceToDB(DatabaseDAO db, Advice advice) {
        Advices adviceModel = new Advices();
        int count = db.query(Advices.class, Query.select().from(Advices.TABLE).
                where(Advices.ADVICE_ID.eq(Integer.parseInt(advice.getId())))).getCount();
        if (count > 0) {
            return -1;
        }
        adviceModel.setAdviceId(Integer.parseInt(advice.getId()));
        adviceModel.setText(advice.getText());
        adviceModel.setSound(advice.getSound());
        return db.persist(adviceModel) ? 1 : 0;
    }

    public static List<Advice> getAdvices(DatabaseDAO db) {
        List<Advice> adviceList = new ArrayList<>();
        Query query = Query.select().from(Advices.TABLE);
        SquidCursor<Advices> adviceCursor = db.query(Advices.class, query);
        try {
            Advices adviceModel = new Advices();
            while (adviceCursor.moveToNext()) {
                adviceModel.readPropertiesFromCursor(adviceCursor);
                Advice advice = new Advice();
                advice.setId(adviceModel.getAdviceId().toString());
                advice.setText(adviceModel.getText());
                advice.setSound(adviceModel.getSound());
                adviceList.add(advice);
            }
        } finally {
            adviceCursor.close();
        }
        return adviceList;
    }

    //change to void
    public static int deleteFromDB(DatabaseDAO db, int id) {
        Delete delete = Delete.from(Advices.TABLE).where(Advices.ADVICE_ID.eq(id));
        return db.delete(delete);
    }
}
