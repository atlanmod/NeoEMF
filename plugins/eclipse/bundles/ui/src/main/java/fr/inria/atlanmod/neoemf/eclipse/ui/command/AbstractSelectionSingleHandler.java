package fr.inria.atlanmod.neoemf.eclipse.ui.command;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;

import java.util.function.Predicate;

/**
 * An {@link AbstractSelectionHandler} which ensures that a selected object is instance of a specified class and matches
 * a predicate.
 *
 * @param <T> the expected type of the selected object
 */
abstract class AbstractSelectionSingleHandler<T> extends AbstractSelectionHandler {

    private final Class<? extends T> expectedType;

    private final Predicate<T> predicate;

    public AbstractSelectionSingleHandler(Class<? extends T> expectedType) {
        this(expectedType, o -> true);
    }

    public AbstractSelectionSingleHandler(Class<? extends T> expectedType, Predicate<T> predicate) {
        this.expectedType = expectedType;
        this.predicate = predicate;
    }

    @Override
    protected void execute(ExecutionEvent event, IStructuredSelection selection) throws ExecutionException {
        final Object selectedObject = selection.getFirstElement();

        if (expectedType.isInstance(selectedObject)) {
            final T selectedItem = expectedType.cast(selectedObject);
            if (predicate.test(selectedItem)) {
                execute(event, selectedItem);
            }
        }
    }

    protected abstract void execute(ExecutionEvent event, T selectedItem) throws ExecutionException;
}
