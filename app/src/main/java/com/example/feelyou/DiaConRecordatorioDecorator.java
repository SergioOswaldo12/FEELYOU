package com.example.feelyou;

import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

public class DiaConRecordatorioDecorator implements DayViewDecorator {

    private final CalendarDay fecha;

    public DiaConRecordatorioDecorator(CalendarDay fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(fecha);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(8, Color.RED)); // Marca visual
    }
}
