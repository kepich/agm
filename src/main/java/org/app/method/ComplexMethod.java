package org.app.method;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.app.utils.threading.ForkJoinPoolSingleton;
import org.app.utils.wav.WavFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class ComplexMethod {
    private AbstractMethod method;
    private ComplexMethod nextElement;

    public void addMethod(AbstractMethod method) {
        if (this.method == null) {
            this.method = method;
        } else if (nextElement == null) {
            nextElement = new ComplexMethod(method, null);
        } else {
            nextElement.addMethod(method);
        }
    }

    public void addMethods(List<AbstractMethod> methods) {
        for (AbstractMethod method: methods){
            this.addMethod(method);
        }
    }

    public AbstractMethod getMethod() {
        return method;
    }

    public List<CompletableFuture<List<?>>> execute(List<WavFile> input, int step) {
        ForkJoinPoolSingleton tpe = ForkJoinPoolSingleton.getInstance();
        return input.stream()
                .map(obj -> CompletableFuture.supplyAsync(() -> this.method.run(obj), tpe))
                .map(future -> future.thenApply(list -> {
                    if (this.nextElement != null)
                        return this.nextElement.execute(list, step + 1);
                    else
                        return list;
                }))
                .collect(Collectors.toList());
    }
}
