package org.app;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.app.method.AbstractMethod;
import org.app.utils.threading.ForkJoinPoolSingleton;
import org.app.utils.wav.WavFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class ComplexMethod {
    private AbstractMethod<WavFile> method;
    private ComplexMethod nextElement;

    public void addMethod(AbstractMethod<WavFile> method) {
        if (this.method == null) {
            this.method = method;
        } else if (nextElement == null) {
            nextElement = new ComplexMethod(method, null);
        } else {
            nextElement.addMethod(method);
        }
    }

    public void addMethods(List<AbstractMethod<WavFile>> methods) {
        for (AbstractMethod<WavFile> method: methods){
            this.addMethod(method);
        }
    }

    public ComplexMethod getNextElement() {
        return nextElement;
    }

    public AbstractMethod<WavFile> getMethod() {
        return method;
    }

    public List<CompletableFuture<List<?>>> execute(List<WavFile> input) {
        ForkJoinPoolSingleton tpe = ForkJoinPoolSingleton.getInstance();
        return input.stream()
                .map(obj -> CompletableFuture.supplyAsync(() -> this.method.run(obj), tpe))
                .map(future -> future.thenApply(list -> {
                    if (this.nextElement != null)
                        return this.nextElement.execute(list);
                    else
                        return list;
                }))
                .collect(Collectors.toList());
    }
}
