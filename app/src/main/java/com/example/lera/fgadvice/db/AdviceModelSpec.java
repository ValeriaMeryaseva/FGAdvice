package com.example.lera.fgadvice.db;

import com.yahoo.squidb.annotations.TableModelSpec;

@TableModelSpec(className = "Advices", tableName = "AdviceTable")
public class AdviceModelSpec {
    private int adviceId;
    private String text;
    private String sound;
}
