package org.app.method;

import org.app.utils.wav.WavFile;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class SimpleChangeMethod extends AbstractMethod {
    private double from;
    private double to;
    private double step;

    protected SimpleChangeMethod(JSONObject parameters) {
        super(parameters);
    }

    @Override
    public void initParameters(JSONObject parameters) {
        this.from = (double) parameters.get("from");
        this.to = (double) parameters.get("to");
        this.step = (double) parameters.get("step");
    }

    @Override
    public List<WavFile> run(WavFile element) {
        System.out.println(this.getClass().getName() + " " + element.toString());
        return getValues().stream().map(value -> this.generate(element, value)).collect(Collectors.toList());
    }

    protected abstract WavFile generate(WavFile element, Double value);

    public List<Double> getValues() {
        ArrayList<Double> res = new ArrayList<>();
        ValueIterator valueIterator = getIterator();
        while (valueIterator.hasNext()) {
            res.add(valueIterator.next());
        }
        return res;
    }

    private ValueIterator getIterator() {
        return new ValueIterator();
    }

    private class ValueIterator {

        double tempValue = from;

        public boolean hasNext() {
            return (from < to) ? tempValue <= to : tempValue >= to;
        }

        public Double next() {
            if(this.hasNext()){
                tempValue += step;
                return tempValue - step;
            }
            return null;
        }
    }
}
